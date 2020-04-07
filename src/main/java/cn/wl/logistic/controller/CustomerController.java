package cn.wl.logistic.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.wl.logistic.mo.MessageObject;
import cn.wl.logistic.pojo.BasicData;
import cn.wl.logistic.pojo.Customer;
import cn.wl.logistic.pojo.CustomerExample;
import cn.wl.logistic.pojo.Permission;
import cn.wl.logistic.pojo.PermissionExample;
import cn.wl.logistic.pojo.User;
import cn.wl.logistic.service.BasicDataService;
import cn.wl.logistic.service.CustomerService;
import cn.wl.logistic.service.UserService;
import cn.wl.logistic.pojo.CustomerExample.Criteria;
import cn.wl.logistic.utils.Constant;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	@Autowired
	private BasicDataService basicDataService;
    
	@RequestMapping("customerPage")
	@RequiresPermissions("customer:customerPage")
	public String customerPage() {
		return "customerPage";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<Customer> list(String keyword, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		// 开始分页
		PageHelper.startPage(pageNum, pageSize);

		CustomerExample example = new CustomerExample();
		
		//
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if(user.getRole().getRolename().equals(Constant.ROLE_SALESMAN)) {
			Criteria createCriteria = example.createCriteria();
			createCriteria.andUserIdEqualTo(user.getUserId());
		}
		
		if (StringUtils.isNotBlank(keyword)) {
			// 按照客户名称，
			Criteria criteria1 = example.createCriteria();
			criteria1.andCustomerNameLike("%" + keyword + "%");

			// 邮箱
			Criteria criteria2 = example.createCriteria();
			criteria2.andEmailLike("%" + keyword + "%");
			// 或者关系
			example.or(criteria2);
		}

		List<Customer> customers = customerService.selectByExample(example);
		// 创建分页对象
		PageInfo<Customer> pageInfo = new PageInfo<>(customers);

		return pageInfo;
	}
	@RequestMapping("/delete")
	@RequiresPermissions("customer:delete")
	@ResponseBody
	public MessageObject delete(Long customerId) {
		System.out.println(customerId);
		MessageObject mo;
		int row = customerService.deleteByPrimaryKey(customerId);
		if(row == 1) {
			mo = new MessageObject(1, "删除成功");
		}else {
			mo = new MessageObject(0, "删除失败，请联系管理员！");
		}
		return mo;
	}
	
	@RequestMapping("/deleteAll")
	@ResponseBody
	public MessageObject deleteAll(@RequestParam("ids[]")Long[] ids) {
		int row = 0;
		for (int i = 0; i < ids.length; i++) {
			int result = customerService.deleteByPrimaryKey(ids[i]);
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
	
	@RequestMapping("/edit")
	public String edit(Model m, Long customerId) {
		// 修改操作才有客户id
		if (customerId != null) {
			Customer customer = customerService.selectByPrimaryKey(customerId);
			m.addAttribute("customer", customer);
		}
         
		//查询出所有的业务员
		List<User> users = userService.selectByRoleName(Constant.ROLE_SALESMAN);
		m.addAttribute("users", users);
		//查询出所有的区间
         List<BasicData> bsDatas = basicDataService.selectByParentName(Constant.BASIC_COMMON_INTERVAL);
		  m.addAttribute("bsDatas", bsDatas);
         return "customerEdit";
	}
	
	@RequestMapping("/insert")
	@RequiresPermissions("customer:insert")
	@ResponseBody
	public MessageObject insert(Customer record) {
		MessageObject mo;
		int row = customerService.insert(record);
		if(row == 1) {
			mo = new MessageObject(1, "增加成功");
		}else {
			mo = new MessageObject(0, "增加失败，请联系管理员！");
		}
		return mo;
	}
	
	@RequestMapping("/update")
	@RequiresPermissions("customer:update")
	@ResponseBody
	public MessageObject update(Customer customer) {

		int row = customerService.updateByPrimaryKeySelective(customer);

		MessageObject mo = new MessageObject(0, "修改客户失败，请联系管理员");
		if (row == 1) {
			mo = new MessageObject(1, "修改客户成功");
		}
		return mo;
	}
}
