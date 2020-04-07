package cn.wl.logistic.controller;

import java.util.List;


import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.wl.logistic.pojo.OrderExample;
import cn.wl.logistic.mo.MessageObject;
import cn.wl.logistic.pojo.Putinstorage;
import cn.wl.logistic.pojo.BasicData;
import cn.wl.logistic.pojo.Customer;
import cn.wl.logistic.pojo.CustomerExample;
import cn.wl.logistic.pojo.CustomerExample.Criteria;
import cn.wl.logistic.pojo.Order;
import cn.wl.logistic.pojo.OrderDetail;
import cn.wl.logistic.pojo.OrderDetailExample;
import cn.wl.logistic.pojo.User;
import cn.wl.logistic.service.BasicDataService;
import cn.wl.logistic.service.CustomerService;
import cn.wl.logistic.service.OrderDetailService;
import cn.wl.logistic.service.OrderService;
import cn.wl.logistic.service.PutinstorageService;
import cn.wl.logistic.service.UserService;
import cn.wl.logistic.utils.Constant;

@Controller
@RequestMapping("/putinstorage")
public class PutinstorageController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BasicDataService basicDataService;
	
	@Autowired
	private PutinstorageService putinstorageService;
	
	@RequestMapping("/putinstoragePage")
	public String putinstoragePage() {
		return "putinstoragePage";
	}
	@RequestMapping("/returnMsg")
	public String returnMsg(Model m,Long orderId) {
		
		User principal = (User) SecurityUtils.getSubject().getPrincipal();
		m.addAttribute("principal", principal);
		//查询订单
		Order order = orderService.selectByPrimaryKey(orderId);
		m.addAttribute("order", order);
		//查询出所有仓库
		List<BasicData> wareHouses = basicDataService.selectByParentName(Constant.BASIC_WAREHOUSE);
		m.addAttribute("wareHouses", wareHouses);
		//获取该业务员
		User user = userService.selectByPrimaryKey(order.getUserId());
		m.addAttribute("user",user);
		
		//根据order获取客户id，再根据客户id查出客户所有信息
		Long customerId = order.getCustomerId();
		CustomerExample example=new CustomerExample();
		Criteria criteria = example.createCriteria();
		criteria.andCustomerIdEqualTo(customerId);
		List<Customer> customers = customerService.selectByExample(example);
		m.addAttribute("customer", customers.get(0));
		
		//根据该订单下的各个基础数据id查询出所有基础数据
		Long intervalId = order.getIntervalId();
		BasicData interval = basicDataService.selectByPrimaryKey(intervalId);
		m.addAttribute("interval", interval);
		Long paymentMethodId = order.getPaymentMethodId();
		BasicData paymentMethod = basicDataService.selectByPrimaryKey(paymentMethodId);
		m.addAttribute("paymentMethod", paymentMethod);
		Long freightMethodId = order.getFreightMethodId();
		BasicData freightMethod = basicDataService.selectByPrimaryKey(freightMethodId);
		m.addAttribute("freightMethod",freightMethod);
		Long takeMethodId = order.getTakeMethodId();
		BasicData takeMethod = basicDataService.selectByPrimaryKey(takeMethodId);
		m.addAttribute("takeMethod", takeMethod);
		
		//查出订单明细
		OrderDetailExample orderDetailExample=new OrderDetailExample();
		cn.wl.logistic.pojo.OrderDetailExample.Criteria criteria2 = orderDetailExample.createCriteria();
		criteria2.andOrderIdEqualTo(orderId);
		List<OrderDetail> orderDetails = orderDetailService.selectByExample(orderDetailExample);
		for (int i = 0; i < orderDetails.size(); i++) {
			Long goodsUnit = orderDetails.get(i).getGoodsUnit();
			BasicData basicData = basicDataService.selectByPrimaryKey(goodsUnit);
			String baseName = basicData.getBaseName();
			orderDetails.get(i).setGoodsUnitName(baseName);
		}
		m.addAttribute("orderDetails",orderDetails);
		return "putinstoragePage";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public MessageObject edit(@RequestBody Putinstorage p) {
		//获取提交核对入库的订单id
		Long orderId = p.getOrderId();
		Order order = orderService.selectByPrimaryKey(orderId);
		//获取货运方式
		Long freightMethodId = order.getFreightMethodId();
		BasicData basicData = basicDataService.selectByPrimaryKey(freightMethodId);
		String freightMethod = basicData.getBaseName();
		int row = putinstorageService.insert(p);
		MessageObject msg = null;
		List<OrderDetail> orderDetails = p.getOrderDetails();
		System.out.println("开始的1212121212121211212121111111111111111111111111111111111");
		for (int i = 0; i < orderDetails.size(); i++) {
			OrderDetail orderDetail = orderDetails.get(i);
			System.out.println("中间的1212121212121211212121111111111111111111111111111111111");
           //如果货运方式是海运或者是陆运，则设置核对重量为0
			if(freightMethod.equals(Constant.BASIC_SKY) || freightMethod.equals(Constant.BASIC_LAND)) {
				orderDetails.get(i).setWeight(0.0);
				System.out.println("海运、陆运的重量是:"+orderDetails.get(i).getWeight());
			}
			orderDetailService.updateByPrimaryKeySelective(orderDetail);
		}
		if(row == 1) {
			msg = new MessageObject(1, "入库成功");
		}else if(row == 0) {
			msg = new MessageObject(0, "入库失败，请联系管理员");
		}
		System.out.println("最后的1212121212121211212121111111111111111111111111111111111");
		//修改订单状态
		order.setOrderStatus(2);
		orderService.updateByPrimaryKeySelective(order);
		return msg;
	}
	
	@RequestMapping("/orderPage")
	public String orderPage() {
		
		return "putinstorageList";
	}
	//查询未入库的订单
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<Order> list(String keyWord,@RequestParam(defaultValue="1")Integer pageNum,@RequestParam(defaultValue="10")Integer pageSize){
       //分页
		PageHelper.startPage(pageNum, pageSize);
		OrderExample orderExample=new OrderExample();
		cn.wl.logistic.pojo.OrderExample.Criteria criteria = orderExample.createCriteria();
		criteria.andOrderStatusEqualTo(1);
		List<Order> orders = orderService.selectByExample(orderExample);
        //创建分页对象
		PageInfo<Order> info = new PageInfo<>(orders);
		return info;
	}
}
