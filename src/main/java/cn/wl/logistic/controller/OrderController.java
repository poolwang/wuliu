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

	// 父表数据
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<Order> list(String keyword, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		// 开始分页
		PageHelper.startPage(pageNum, pageSize);

		OrderExample example = new OrderExample();
        
		//如果登录的是业务员，那么只显示该业务员对应的客户
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user.getRole().getRolename().equals(Constant.ROLE_SALESMAN)) {
			Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(user.getUserId());
		}
		
		if (StringUtils.isNotBlank(keyword)) {

			// 按照收货人名称，
			Criteria criteria1 = example.createCriteria();
			criteria1.andShippingNameLike("%" + keyword + "%");

			// 收货人地址
			Criteria criteria2 = example.createCriteria();
			criteria2.andShippingAddressLike("%" + keyword + "%");
			// 或者关系
			example.or(criteria2);

		}

		List<Order> orders = orderService.selectByExample(example);
		
		// 创建分页对象
		PageInfo<Order> pageInfo = new PageInfo<>(orders);

		return pageInfo;
	}

	// 子表展开数据
	@RequestMapping("/detail")
	@ResponseBody
	public List<OrderDetail> detail(Long orderId) {

		OrderDetailExample example = new OrderDetailExample();
		// 指定订单id
		cn.wl.logistic.pojo.OrderDetailExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<OrderDetail> orderDetails = orderService.selectByExample(example);

		return orderDetails;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public MessageObject delete(Long orderId) {
		MessageObject mo = new MessageObject(0, "删除失败！");
		int row = orderService.deleteByPrimaryKey(orderId);
		//查找出orderId对应的所有订单详情
		OrderDetailExample example=new OrderDetailExample();
		cn.wl.logistic.pojo.OrderDetailExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<OrderDetail> orderDetails = orderDetailService.selectByExample(example);
		//删除所有订单明细
		if (orderDetails.size()>0) {
			for (int i = 0; i < orderDetails.size(); i++) {
				Long orderDetailId = orderDetails.get(i).getOrderDetailId();
				orderDetailService.deleteByPrimaryKey(orderDetailId);
			}
		}
		
		if (row == 1) {
			mo = new MessageObject(0, "删除成功！");
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
		
		MessageObject mo =new MessageObject(0, "新增失败");
		if (row==1) {
			mo=new MessageObject(1,"新增成功");
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
	  
	  MessageObject mo =new MessageObject(0, "修改失败"); if (row==1) { mo=new
	  MessageObject(1,"修改成功"); } return mo; }
	 
}
