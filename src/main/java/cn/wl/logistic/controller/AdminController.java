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
//ʧ�ܺ�ִ��
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Model m) {
		String shiroLoginFailure = (String) request.getAttribute("shiroLoginFailure");
		System.out.println("shiroLoginFailure��"+shiroLoginFailure);
		if (shiroLoginFailure !=null) {
			if (UnknownAccountException.class.getName().equals(shiroLoginFailure)) {
				m.addAttribute("erroyMsg", "�ס��˺Ų�����");
			} else if (IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)) {
				m.addAttribute("erroyMsg", "�ס��������");
			}else if("verifyCodeError".equals(shiroLoginFailure)) {
				m.addAttribute("erroyMsg", "�ף���֤�����");
			}
		}
		return "forward:/login.jsp";
	}
	@RequestMapping("/logout")
	public void logout() {
	
	}
	
// ��ҳ
	
	@RequestMapping("/list")
	@RequiresPermissions("admin:list")
	@ResponseBody
	public PageInfo<User> adminList(String keyWord, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
//		��ʼ��ҳ
		PageHelper.startPage(pageNum, pageSize);
//		����UserExample����
		UserExample userExample = new UserExample();
//		 �ж�keyWord�Ȳ�ѯ �����Ƿ�Ϊ��
		if (StringUtils.isNotBlank(keyWord)) {
//			 �����Ϊ�գ������keyWord��ѯ���
			Criteria criteria1 = userExample.createCriteria();
//			 �����û�������ģ����ѯ
			criteria1.andUsernameLike("%" + keyWord + "%");
			Criteria criteria2 = userExample.createCriteria();
//			 ������ʵ��������ģ����ѯ
			criteria2.andRealnameLike("%" + keyWord + "%");
//			 ���߹�ϵ
			userExample.or(criteria2);
		}
//		 ��ѯ���н��
		List<User> users = userService.selectByExample(userExample);
//		 ������ҳ����
		PageInfo<User> pageInfo = new PageInfo<>(users);
		System.out.println(pageInfo);
//		 ���ط�ҳ����
		return pageInfo;
	}

//	ɾ������Ա�ķ���
	
	@RequestMapping("/delete")
	@RequiresPermissions("admin:delete")
	@ResponseBody
	public MessageObject delete(Long userId) {
		MessageObject mo = null;
		int row = userService.deleteByPrimaryKey(userId);
		if (row == 1) {
			mo = new MessageObject(1, "ɾ���ɹ�");
		} else {
			mo = new MessageObject(0, "ɾ��ʧ�ܣ�����ϵ����Ա");
		}
		return mo;
	}

//	����ɾ������Ա�ķ���
	
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
			mo = new MessageObject(1, "ɾ���ɹ�");
		} else {
			mo = new MessageObject(0, "ɾ��ʧ�ܣ�����ϵ����Ա");
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
		// �ѵ�ǰʱ����Ϊ����ʱ��
		user.setCreateDate(new Date());
		// ʹ�������������
		String salt = UUID.randomUUID().toString().substring(0, 4);
		// md5����
		Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 3);
		// ���ü��ܺ�����
		user.setPassword(md5Hash.toString());
		// ������
		user.setSalt(salt);

		int row = userService.insert(user);

		MessageObject mo = new MessageObject(0, "�����û�ʧ�ܣ�����ϵ����Ա");
		if (row == 1) {
			mo = new MessageObject(1, "�����û��ɹ�");
		}
		return mo;
	}
	
	@RequestMapping("/edit")
	// @ResponseBody
//    ��ѯ��ԭ������Ϣ
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
		// ʹ�������������
		String salt = UUID.randomUUID().toString().substring(0, 4);
		// md5����
		Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 3);
		// ���ü��ܺ�����
		user.setPassword(md5Hash.toString());
		// ������
		user.setSalt(salt);
		int row = userService.updateByPrimaryKeySelective(user);

		MessageObject mo = new MessageObject(0, "�޸��û�ʧ�ܣ�����ϵ����Ա");
		if (row == 1) {
			mo = new MessageObject(1, "�޸��û��ɹ�");
		}
		return mo;
	}
	

	
}
