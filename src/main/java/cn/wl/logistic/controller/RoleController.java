package cn.wl.logistic.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.wl.logistic.pojo.Permission;
import cn.wl.logistic.pojo.PermissionExample;
import cn.wl.logistic.mo.MessageObject;
import cn.wl.logistic.pojo.Role;
import cn.wl.logistic.pojo.RoleExample;
import cn.wl.logistic.pojo.Role;
import cn.wl.logistic.pojo.RoleExample;
import cn.wl.logistic.pojo.RoleExample.Criteria;
import cn.wl.logistic.service.PermissionService;
import cn.wl.logistic.service.RoleService;
import cn.wl.logistic.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/rolePage")
	@RequiresPermissions("role:rolePage")
	public String rolePage() {
		
		return "rolePage";
	}
//	��ת��������ɫ��ҳ��

	@RequestMapping("/roleEdit")
	public String roleEdit() {
		return "roleEdit";
	}
	
	@RequestMapping("/list")
	@RequiresPermissions("role:list")
	@ResponseBody
	public PageInfo<Role> list(String keyWord,@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="10") Integer pageSize){
//		��ҳ
		PageHelper.startPage(pageNum, pageSize);
		RoleExample example = new RoleExample();
//		�����ѯ���ݲ�Ϊ��
		if(StringUtils.isNotBlank(keyWord)) {
//			���в�ѯ
			Criteria criteria1 = example.createCriteria();
			criteria1.andRolenameLike("%"+keyWord+"%");
			Criteria criteria2 = example.createCriteria();
			criteria2.andRemarkLike("%"+keyWord+"%");
			example.or(criteria2);
		}
		List<Role> roles = roleService.selectByExample(example);
//		������ҳ����
		PageInfo<Role> info = new PageInfo<>(roles);
		return info;
	}
//	ɾ����ɫ�Ĳ���
	
	@RequestMapping("/delete")
	@RequiresPermissions("role:delete")
	@ResponseBody
	public MessageObject delete(Long roleId) {
		System.out.println(roleId);
		MessageObject msg;
		int row = roleService.deleteByPrimaryKey(roleId);
		if(row == 1) {
			msg = new MessageObject(1, "ɾ���ɹ�");
		}else {
			msg = new MessageObject(0, "ɾ��ʧ�ܣ�����ϵ����Ա��");
		}
		return msg;
	}
	
	@RequestMapping("/checkRolename")
	@ResponseBody
	public boolean checkRolename(String rolename) {

		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andRolenameEqualTo(rolename);
		List<Role> roles = roleService.selectByExample(example);
		/*
		 * ����true ˵����ɫ�Ų����ڣ����� false�����ڲ�����
		 */
		if (roles.size() == 1) {
			return false;
		}
		return true;
	}
//	������ɫ�ķ���
	
	@RequestMapping("/insert")
	@RequiresPermissions("role:insert")
	@ResponseBody
	public MessageObject insert(Role role) {
		int row = roleService.insert(role);
		MessageObject msg = null;
		if(row == 1) {
			msg = new MessageObject(1, "������ɫ�ɹ�");
		}else {
			msg = new MessageObject(0, "������ɫʧ�ܣ�����ϵ����Ա");
		}
		return msg;
	}
	
	
//	�༭��ɫ�������ݵķ���
	@RequestMapping("/edit")
	public String edit(Model m,Long roleId) {
		if (roleId != null) {
			Role role = roleService.selectByPrimaryKey(roleId);
			m.addAttribute("role", role);
		}

		RoleExample example = new RoleExample();
		// ��ѯ�����е�Ȩ�ޣ���Ϊ���Ȩ�޵ĸ�Ȩ��ʹ��
		List<Role> roles = roleService.selectByExample(example);

		m.addAttribute("roles", roles);

		return "roleEdit";
	}
	
//	�༭��ɫ
	
	@RequestMapping("/update")
	@RequiresPermissions("role:update")
	@ResponseBody
	public MessageObject editRole(Role role) {
		MessageObject msg = null;
		int row = roleService.updateByPrimaryKeySelective(role);
		if(row == 1) {
			msg = new MessageObject(1, "��ɫ��Ϣ�޸ĳɹ�");
		}else {
			msg = new MessageObject(0, "��ɫ��Ϣ�޸�ʧ�ܣ�����ϵ����Ա");
		}
		return msg;
	}
//	����ɾ��
	
	@RequestMapping("/deleteAll")
	@ResponseBody
	public MessageObject deleteAll(@RequestParam("ids[]")Long[]ids) {
		MessageObject msg = null;
		int rows = 0;
		for (int i = 0; i < ids.length; i++) {
			int result = roleService.deleteByPrimaryKey(ids[i]);
			rows += result;
		}
		if(rows >= 1) {
			msg = new MessageObject(1, "ɾ���ɹ�");
		}else {
			msg = new MessageObject(0, "ɾ��ʧ��,����ϵ����Ա");
		}
		return msg;
	}

}
