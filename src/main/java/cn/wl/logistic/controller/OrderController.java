package cn.wl.logistic.controller;

import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.wl.logistic.pojo.BasicData;
import cn.wl.logistic.mo.MessageObject;
import cn.wl.logistic.pojo.Customer;
import cn.wl.logistic.pojo.CustomerExample;
import cn.wl.logistic.pojo.Order;
import cn.wl.logistic.pojo.OrderDetail;
import cn.wl.logistic.pojo.OrderDetailExample;
import cn.wl.logistic.pojo.OrderExample;
import cn.wl.logistic.pojo.User;
import cn.wl.logistic.pojo.OrderExample.Criteria;
import cn.wl.logistic.service.BasicDataService;
import cn.wl.logistic.service.CustomerService;
import cn.wl.logistic.service.OrderDetailService;
import cn.wl.logistic.service.OrderService;
import cn.wl.logistic.service.UserService;
import cn.wl.logistic.utils.Constant;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private UserService userService;
	@Autowired
	private BasicDataService basicDataService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderDetailService orderDetailService;

	@RequestMapping("/orderPage")
	public String orderPage() {
		return "orderPage";
	}

	// ��������
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<Order> list(String keyword, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		// ��ʼ��ҳ
		PageHelper.startPage(pageNum, pageSize);

		OrderExample example = new OrderExample();
        
		//�����¼����ҵ��Ա����ôֻ��ʾ��ҵ��Ա��Ӧ�Ŀͻ�
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user.getRole().getRolename().equals(Constant.ROLE_SALESMAN)) {
			Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(user.getUserId());
		}
		
		if (StringUtils.isNotBlank(keyword)) {

			// �����ջ������ƣ�
			Criteria criteria1 = example.createCriteria();
			criteria1.andShippingNameLike("%" + keyword + "%");

			// �ջ��˵�ַ
			Criteria criteria2 = example.createCriteria();
			criteria2.andShippingAddressLike("%" + keyword + "%");
			// ���߹�ϵ
			example.or(criteria2);

		}

		List<Order> orders = orderService.selectByExample(example);
		
		// ������ҳ����
		PageInfo<Order> pageInfo = new PageInfo<>(orders);

		return pageInfo;
	}

	// �ӱ�չ������
	@RequestMapping("/detail")
	@ResponseBody
	public List<OrderDetail> detail(Long orderId) {

		OrderDetailExample example = new OrderDetailExample();
		// ָ������id
		cn.wl.logistic.pojo.OrderDetailExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<OrderDetail> orderDetails = orderService.selectByExample(example);

		return orderDetails;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public MessageObject delete(Long orderId) {
		MessageObject mo = new MessageObject(0, "ɾ��ʧ�ܣ�");
		int row = orderService.deleteByPrimaryKey(orderId);
		//���ҳ�orderId��Ӧ�����ж�������
		OrderDetailExample example=new OrderDetailExample();
		cn.wl.logistic.pojo.OrderDetailExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<OrderDetail> orderDetails = orderDetailService.selectByExample(example);
		//ɾ�����ж�����ϸ
		if (orderDetails.size()>0) {
			for (int i = 0; i < orderDetails.size(); i++) {
				Long orderDetailId = orderDetails.get(i).getOrderDetailId();
				orderDetailService.deleteByPrimaryKey(orderDetailId);
			}
		}
		
		if (row == 1) {
			mo = new MessageObject(0, "ɾ���ɹ���");
		}
		return mo;
	}

	@RequestMapping("edit")
	public String edit(Model m,Long orderId) {
		 List<User> users = userService.selectByRoleName(Constant.ROLE_SALESMAN);
		 m.addAttribute("users", users);
		 CustomerExample example=new CustomerExample();
		List<Customer> customers = customerService.selectByExample(example);
		m.addAttribute("customers", customers);
		List<BasicData> intervals = basicDataService.selectByParentName(Constant.BASIC_COMMON_INTERVAL);
		m.addAttribute("intervals", intervals);
		List<BasicData> payments = basicDataService.selectByParentName(Constant.BASIC_PAYMENT_TYPE);
		m.addAttribute("payments", payments);
    	List<BasicData> fetchTypes = basicDataService.selectByParentName(Constant.BASIC_FETCH_TYPE);
		m.addAttribute("fetchTypes", fetchTypes);
    	List<BasicData> freights = basicDataService.selectByParentName(Constant.BASIC_FREIGHT_TYPE);
    	m.addAttribute("freights", freights);
    	List<BasicData> units = basicDataService.selectByParentName(Constant.BASIC_UNIT);
    	m.addAttribute("units", units);
		
    	if (orderId !=null) {
    		Order order = orderService.selectByPrimaryKey(orderId);
    		m.addAttribute("order", order); 		
    		OrderDetailExample orderDetailExample=new  OrderDetailExample(); 
    		cn.wl.logistic.pojo.OrderDetailExample.Criteria criteria = orderDetailExample.createCriteria();
    		criteria.andOrderIdEqualTo(orderId); 
    		List<OrderDetail> orderDetails = orderDetailService.selectByExample(orderDetailExample);
    		m.addAttribute("orderDetails", orderDetails);
    	}
		 
        return "orderEdit";
	}
	
	@RequestMapping("insertEdit")
	public String insertEdit(Model m) {
		 List<User> users = userService.selectByRoleName(Constant.ROLE_SALESMAN);
		 m.addAttribute("users", users);
		 CustomerExample example=new CustomerExample();
		List<Customer> customers = customerService.selectByExample(example);
		m.addAttribute("customers", customers);
		List<BasicData> intervals = basicDataService.selectByParentName(Constant.BASIC_COMMON_INTERVAL);
		m.addAttribute("intervals", intervals);
		List<BasicData> payments = basicDataService.selectByParentName(Constant.BASIC_PAYMENT_TYPE);
		m.addAttribute("payments", payments);
    	List<BasicData> fetchTypes = basicDataService.selectByParentName(Constant.BASIC_FETCH_TYPE);
		m.addAttribute("fetchTypes", fetchTypes);
    	List<BasicData> freights = basicDataService.selectByParentName(Constant.BASIC_FREIGHT_TYPE);
    	m.addAttribute("freights", freights);
    	List<BasicData> units = basicDataService.selectByParentName(Constant.BASIC_UNIT);
    	m.addAttribute("units", units);
		 
        return "orderInsert";
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public MessageObject insert(@RequestBody Order order) {
		int row = orderService.insert(order);
		
		MessageObject mo =new MessageObject(0, "����ʧ��");
		if (row==1) {
			mo=new MessageObject(1,"�����ɹ�");
		}
		return mo;
	}
	
	  @RequestMapping("/update")
	   @ResponseBody public MessageObject update(@RequestBody Order order) { 
		  int row= orderService.updateByPrimaryKeySelective(order);
	  
	  List<OrderDetail> orderDetails = order.getOrderDetails();
	 if(orderDetails.size() !=0) { 
		 for (int i = 0; i < orderDetails.size(); i++) {
	  OrderDetail orderDetail = orderDetails.get(i);
	  orderDetailService.updateByPrimaryKeySelective(orderDetail); 
	     } 
     }
	  
	  MessageObject mo =new MessageObject(0, "�޸�ʧ��"); if (row==1) { mo=new
	  MessageObject(1,"�޸ĳɹ�"); } return mo; }
	 
}
