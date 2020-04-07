package cn.wl.logistic.controller;

import java.util.Date;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.wl.logistic.mo.MessageObject;
import cn.wl.logistic.pojo.Role;
import cn.wl.logistic.pojo.RoleExample;
import cn.wl.logistic.pojo.User;
import cn.wl.logistic.pojo.UserExample;
import cn.wl.logistic.pojo.UserExample.Criteria;
import cn.wl.logistic.service.RoleService;
import cn.wl.logistic.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/adminPage")
	@RequiresPermissions("admin:adminPage")
	public String adminPage() {
		return "adminPage";
	}
//失败后执行
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Model m) {
		String shiroLoginFailure = (String) request.getAttribute("shiroLoginFailure");
		System.out.println("shiroLoginFailure："+shiroLoginFailure);
		if (shiroLoginFailure !=null) {
			if (UnknownAccountException.class.getName().equals(shiroLoginFailure)) {
				m.addAttribute("erroyMsg", "亲。账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)) {
				m.addAttribute("erroyMsg", "亲。密码错误");
			}else if("verifyCodeError".equals(shiroLoginFailure)) {
				m.addAttribute("erroyMsg", "亲，验证码错误");
			}
		}
		return "forward:/login.jsp";
	}
	@RequestMapping("/logout")
	public void logout() {
	
	}
	
// 分页
	
	@RequestMapping("/list")
	@RequiresPermissions("admin:list")
	@ResponseBody
	public PageInfo<User> adminList(String keyWord, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
//		开始分页
		PageHelper.startPage(pageNum, pageSize);
//		创建UserExample对象
		UserExample userExample = new UserExample();
//		 判断keyWord既查询 内容是否为空
		if (StringUtils.isNotBlank(keyWord)) {
//			 如果不为空，则根据keyWord查询结果
			Criteria criteria1 = userExample.createCriteria();
//			 根据用户名进行模糊查询
			criteria1.andUsernameLike("%" + keyWord + "%");
			Criteria criteria2 = userExample.createCriteria();
//			 根据真实姓名进行模糊查询
			criteria2.andRealnameLike("%" + keyWord + "%");
//			 或者关系
			userExample.or(criteria2);
		}
//		 查询所有结果
		List<User> users = userService.selectByExample(userExample);
//		 创建分页对象
		PageInfo<User> pageInfo = new PageInfo<>(users);
		System.out.println(pageInfo);
//		 返回分页对象
		return pageInfo;
	}

//	删除管理员的方法
	
	@RequestMapping("/delete")
	@RequiresPermissions("admin:delete")
	@ResponseBody
	public MessageObject delete(Long userId) {
		MessageObject mo = null;
		int row = userService.deleteByPrimaryKey(userId);
		if (row == 1) {
			mo = new MessageObject(1, "删除成功");
		} else {
			mo = new MessageObject(0, "删除失败，请联系管理员");
		}
		return mo;
	}

//	批量删除管理员的方法
	
	@RequestMapping("/deleteAll")
	@ResponseBody
	public MessageObject deleteAll(@RequestParam(value = "ids[]") Long[] ids) {
		
		MessageObject mo = null;
		int row = 0;
		for (int i = 0; i < ids.length; i++) {
			int result = userService.deleteByPrimaryKey(ids[i]);
			if (result == 1) {
				row += 1;
			}
		}
		if (row >= 1) {
			mo = new MessageObject(1, "删除成功");
		} else {
			mo = new MessageObject(0, "删除失败，请联系管理员");
		}
		return mo;
	}



	@RequestMapping("/checkUsername")
	@ResponseBody
	public boolean checkUserName(String username) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> users = userService.selectByExample(example);
		if (users.size() == 1) {
			return false;
		} else {
			return true;
		}
	}
	
	@RequestMapping("/insert")
	@RequiresPermissions("admin:insert")
	@ResponseBody
	public MessageObject insert(User user) {
		// 把当前时间设为创建时间
		user.setCreateDate(new Date());
		// 使用随机数生成盐
		String salt = UUID.randomUUID().toString().substring(0, 4);
		// md5加密
		Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 3);
		// 设置加密后密码
		user.setPassword(md5Hash.toString());
		// 设置盐
		user.setSalt(salt);

		int row = userService.insert(user);

		MessageObject mo = new MessageObject(0, "新增用户失败，请联系管理员");
		if (row == 1) {
			mo = new MessageObject(1, "新增用户成功");
		}
		return mo;
	}
	
	@RequestMapping("/edit")
	// @ResponseBody
//    查询出原本的信息
	public String edit(Long userId, Model m) {
		User user = userService.selectByPrimaryKey(userId);
		m.addAttribute("user", user);
		RoleExample example = new RoleExample();
		List<Role> roles = roleService.selectByExample(example);
		m.addAttribute("roles", roles);
		return "adminEdit";
	}
	
	@RequestMapping("/update")
	@RequiresPermissions("admin:update")
	@ResponseBody
	public MessageObject update(User user) {
		// 使用随机数生成盐
		String salt = UUID.randomUUID().toString().substring(0, 4);
		// md5加密
		Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 3);
		// 设置加密后密码
		user.setPassword(md5Hash.toString());
		// 设置盐
		user.setSalt(salt);
		int row = userService.updateByPrimaryKeySelective(user);

		MessageObject mo = new MessageObject(0, "修改用户失败，请联系管理员");
		if (row == 1) {
			mo = new MessageObject(1, "修改用户成功");
		}
		return mo;
	}
	

	
}
