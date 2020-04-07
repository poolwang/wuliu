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
//	跳转到新增角色的页面

	@RequestMapping("/roleEdit")
	public String roleEdit() {
		return "roleEdit";
	}
	
	@RequestMapping("/list")
	@RequiresPermissions("role:list")
	@ResponseBody
	public PageInfo<Role> list(String keyWord,@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="10") Integer pageSize){
//		分页
		PageHelper.startPage(pageNum, pageSize);
		RoleExample example = new RoleExample();
//		如果查询内容不为空
		if(StringUtils.isNotBlank(keyWord)) {
//			进行查询
			Criteria criteria1 = example.createCriteria();
			criteria1.andRolenameLike("%"+keyWord+"%");
			Criteria criteria2 = example.createCriteria();
			criteria2.andRemarkLike("%"+keyWord+"%");
			example.or(criteria2);
		}
		List<Role> roles = roleService.selectByExample(example);
//		创建分页对象
		PageInfo<Role> info = new PageInfo<>(roles);
		return info;
	}
//	删除角色的操作
	
	@RequestMapping("/delete")
	@RequiresPermissions("role:delete")
	@ResponseBody
	public MessageObject delete(Long roleId) {
		System.out.println(roleId);
		MessageObject msg;
		int row = roleService.deleteByPrimaryKey(roleId);
		if(row == 1) {
			msg = new MessageObject(1, "删除成功");
		}else {
			msg = new MessageObject(0, "删除失败，请联系管理员！");
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
		 * 返回true 说明角色号不存在，可用 false：存在不可用
		 */
		if (roles.size() == 1) {
			return false;
		}
		return true;
	}
//	新增角色的方法
	
	@RequestMapping("/insert")
	@RequiresPermissions("role:insert")
	@ResponseBody
	public MessageObject insert(Role role) {
		int row = roleService.insert(role);
		MessageObject msg = null;
		if(row == 1) {
			msg = new MessageObject(1, "新增角色成功");
		}else {
			msg = new MessageObject(0, "新增角色失败，请联系管理员");
		}
		return msg;
	}
	
	
//	编辑角色回显数据的方法
	@RequestMapping("/edit")
	public String edit(Model m,Long roleId) {
		if (roleId != null) {
			Role role = roleService.selectByPrimaryKey(roleId);
			m.addAttribute("role", role);
		}

		RoleExample example = new RoleExample();
		// 查询出所有的权限，作为添加权限的父权限使用
		List<Role> roles = roleService.selectByExample(example);

		m.addAttribute("roles", roles);

		return "roleEdit";
	}
	
//	编辑角色
	
	@RequestMapping("/update")
	@RequiresPermissions("role:update")
	@ResponseBody
	public MessageObject editRole(Role role) {
		MessageObject msg = null;
		int row = roleService.updateByPrimaryKeySelective(role);
		if(row == 1) {
			msg = new MessageObject(1, "角色信息修改成功");
		}else {
			msg = new MessageObject(0, "角色信息修改失败，请联系管理员");
		}
		return msg;
	}
//	批量删除
	
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
			msg = new MessageObject(1, "删除成功");
		}else {
			msg = new MessageObject(0, "删除失败,请联系管理员");
		}
		return msg;
	}

}
