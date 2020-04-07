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
		// ��ʼ��ҳ
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
			// ���տͻ����ƣ�
			Criteria criteria1 = example.createCriteria();
			criteria1.andCustomerNameLike("%" + keyword + "%");

			// ����
			Criteria criteria2 = example.createCriteria();
			criteria2.andEmailLike("%" + keyword + "%");
			// ���߹�ϵ
			example.or(criteria2);
		}

		List<Customer> customers = customerService.selectByExample(example);
		// ������ҳ����
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
			mo = new MessageObject(1, "ɾ���ɹ�");
		}else {
			mo = new MessageObject(0, "ɾ��ʧ�ܣ�����ϵ����Ա��");
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
			mo = new MessageObject(1, "ɾ���ɹ�");
		}else {
			mo = new MessageObject(0, "ɾ������ʧ�ܣ�����ϵ����Ա");
		}
		return mo;
	}
	
	@RequestMapping("/edit")
	public String edit(Model m, Long customerId) {
		// �޸Ĳ������пͻ�id
		if (customerId != null) {
			Customer customer = customerService.selectByPrimaryKey(customerId);
			m.addAttribute("customer", customer);
		}
         
		//��ѯ�����е�ҵ��Ա
		List<User> users = userService.selectByRoleName(Constant.ROLE_SALESMAN);
		m.addAttribute("users", users);
		//��ѯ�����е�����
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
			mo = new MessageObject(1, "���ӳɹ�");
		}else {
			mo = new MessageObject(0, "����ʧ�ܣ�����ϵ����Ա��");
		}
		return mo;
	}
	
	@RequestMapping("/update")
	@RequiresPermissions("customer:update")
	@ResponseBody
	public MessageObject update(Customer customer) {

		int row = customerService.updateByPrimaryKeySelective(customer);

		MessageObject mo = new MessageObject(0, "�޸Ŀͻ�ʧ�ܣ�����ϵ����Ա");
		if (row == 1) {
			mo = new MessageObject(1, "�޸Ŀͻ��ɹ�");
		}
		return mo;
	}
}
