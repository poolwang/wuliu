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
//		分页
		PageHelper.startPage(pageNum, pageSize);
		PermissionExample example = new PermissionExample();
//		查询
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
//		创建分页对象
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
	
//	删除操作的方法
	
	@RequestMapping("/delete")
	@RequiresPermissions("permission:delete")
	@ResponseBody
	public MessageObject delete(Long permissionId) {
		PermissionExample example = new PermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(permissionId);
		List<Permission> children = permissionService.selectByExample(example );
		if(children.size() >0) {
			MessageObject mo = new MessageObject(0, "当前数据还有子数据，不能删除");
			return mo;
		}
		int row = permissionService.deleteByPrimaryKey(permissionId);

		MessageObject mo = new MessageObject(0, "删除数据失败，请联系管理员");
		if (row == 1) {
			mo = new MessageObject(1, "删除数据成功");
		}
		return mo;
	}
	
	
//	检查权限名称是否已经存在
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
//	新增权限的方法
	
	@RequestMapping("/insert")
	@RequiresPermissions("permission:insert")
	@ResponseBody
	public MessageObject insert(Permission permission) {
		MessageObject mo = null;
		int row = permissionService.insert(permission);
		if(row == 1) {
			mo = new MessageObject(1, "新增权限成功");
		}else {
			mo = new MessageObject(0, "新增权限失败，请联系管理员");
		}
		return mo;
	}
//	编辑用户前用于回显数据的方法
	@RequestMapping("/edit")
	public String edit(Model m, Long permissionId) {
		// 修改操作才有权限id
		if (permissionId != null) {
			Permission permission = permissionService.selectByPrimaryKey(permissionId);
			m.addAttribute("permission", permission);
		}

		PermissionExample example = new PermissionExample();
		// 查询出所有的权限，作为添加权限的父权限使用
		List<Permission> permissions = permissionService.selectByExample(example);

		m.addAttribute("permissions", permissions);

		return "permissionEdit";
	}

//	编辑用户的方法
	
	@RequestMapping("/update")
	@RequiresPermissions("permission:update")
	@ResponseBody
	public MessageObject editPermission(Permission permission) {
		int row = permissionService.updateByPrimaryKeySelective(permission);
		MessageObject mo = null;
		if(row == 1) {
			mo = new MessageObject(1, "修改权限成功");
		}else {
			mo = new MessageObject(0, "修改权限失败，请联系管理员");
		}
		return mo;
	}
//	批量删除
	
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
			mo = new MessageObject(1, "删除成功");
		}else {
			mo = new MessageObject(0, "删除操作失败，请联系管理员");
		}
		return mo;
	}
}
