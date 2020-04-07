package cn.wl.logistic.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.wl.logistic.mo.MessageObject;
import cn.wl.logistic.pojo.Permission;
import cn.wl.logistic.pojo.PermissionExample;
import cn.wl.logistic.pojo.Permission;
import cn.wl.logistic.pojo.PermissionExample;
import cn.wl.logistic.pojo.PermissionExample.Criteria;
import cn.wl.logistic.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/permissionPage")
	@RequiresPermissions("permission:permissionPage")
	public String permissionPage() {
		
		return "permissionPage";
	}
	
	
	@RequestMapping("/list")
	@RequiresPermissions("permission:list")
	@ResponseBody
	public PageInfo<Permission> permissionList(String keyWord,@RequestParam(defaultValue="1")Integer pageNum,@RequestParam(defaultValue="10")Integer pageSize) {
//		��ҳ
		PageHelper.startPage(pageNum, pageSize);
		PermissionExample example = new PermissionExample();
//		��ѯ
		if(StringUtils.isNotBlank(keyWord)) {
			Criteria criteria1 = example.createCriteria();
			criteria1.andNameLike("%"+keyWord+"%");
			Criteria criteria2 = example.createCriteria();
			criteria2.andUrlLike("%"+keyWord+"%");
			Criteria criteria3 = example.createCriteria();
			criteria3.andExpressionLike("%"+keyWord+"%");
			example.or(criteria3);
		}
		
		List<Permission> permissions = permissionService.selectByExample(example);
//		������ҳ����
		PageInfo<Permission> info = new PageInfo<>(permissions);
		
		return info;
	}
	
	@RequestMapping("/getAllPermission")
	@ResponseBody
	public List<Permission> getAllpermission() {

		PermissionExample example = new PermissionExample();
		List<Permission> permissions = permissionService.selectByExample(example);

		return permissions;

	}
	
//	ɾ�������ķ���
	
	@RequestMapping("/delete")
	@RequiresPermissions("permission:delete")
	@ResponseBody
	public MessageObject delete(Long permissionId) {
		PermissionExample example = new PermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(permissionId);
		List<Permission> children = permissionService.selectByExample(example );
		if(children.size() >0) {
			MessageObject mo = new MessageObject(0, "��ǰ���ݻ��������ݣ�����ɾ��");
			return mo;
		}
		int row = permissionService.deleteByPrimaryKey(permissionId);

		MessageObject mo = new MessageObject(0, "ɾ������ʧ�ܣ�����ϵ����Ա");
		if (row == 1) {
			mo = new MessageObject(1, "ɾ�����ݳɹ�");
		}
		return mo;
	}
	
	
//	���Ȩ�������Ƿ��Ѿ�����
	@RequestMapping("/checkPermissionname")
	@ResponseBody
	public boolean checkPermissionname(String name) {
		PermissionExample example = new PermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<Permission> permissions = permissionService.selectByExample(example);

		if (permissions.size() == 1) {
			return false;
		}
		return true;
	}
//	����Ȩ�޵ķ���
	
	@RequestMapping("/insert")
	@RequiresPermissions("permission:insert")
	@ResponseBody
	public MessageObject insert(Permission permission) {
		MessageObject mo = null;
		int row = permissionService.insert(permission);
		if(row == 1) {
			mo = new MessageObject(1, "����Ȩ�޳ɹ�");
		}else {
			mo = new MessageObject(0, "����Ȩ��ʧ�ܣ�����ϵ����Ա");
		}
		return mo;
	}
//	�༭�û�ǰ���ڻ������ݵķ���
	@RequestMapping("/edit")
	public String edit(Model m, Long permissionId) {
		// �޸Ĳ�������Ȩ��id
		if (permissionId != null) {
			Permission permission = permissionService.selectByPrimaryKey(permissionId);
			m.addAttribute("permission", permission);
		}

		PermissionExample example = new PermissionExample();
		// ��ѯ�����е�Ȩ�ޣ���Ϊ���Ȩ�޵ĸ�Ȩ��ʹ��
		List<Permission> permissions = permissionService.selectByExample(example);

		m.addAttribute("permissions", permissions);

		return "permissionEdit";
	}

//	�༭�û��ķ���
	
	@RequestMapping("/update")
	@RequiresPermissions("permission:update")
	@ResponseBody
	public MessageObject editPermission(Permission permission) {
		int row = permissionService.updateByPrimaryKeySelective(permission);
		MessageObject mo = null;
		if(row == 1) {
			mo = new MessageObject(1, "�޸�Ȩ�޳ɹ�");
		}else {
			mo = new MessageObject(0, "�޸�Ȩ��ʧ�ܣ�����ϵ����Ա");
		}
		return mo;
	}
//	����ɾ��
	
	@RequestMapping("/deleteAll")
	@ResponseBody
	public MessageObject deleteAll(@RequestParam("ids[]")Long[] ids) {
		int row = 0;
		for (int i = 0; i < ids.length; i++) {
			int result = permissionService.deleteByPrimaryKey(ids[i]);
			if(result == 1) {
				row += 1;
			}
		}
		MessageObject mo = null;
		if(row >= 1) {
			mo = new MessageObject(1, "ɾ���ɹ�");
		}else {
			mo = new MessageObject(0, "ɾ������ʧ�ܣ�����ϵ����Ա");
		}
		return mo;
	}
}
