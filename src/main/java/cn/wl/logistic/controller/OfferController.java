package cn.wl.logistic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.wl.logistic.mo.MessageObject;
import cn.wl.logistic.pojo.Offer;
import cn.wl.logistic.pojo.OfferExample;
import cn.wl.logistic.pojo.BasicData;
import cn.wl.logistic.pojo.Customer;
import cn.wl.logistic.pojo.CustomerExample;
import cn.wl.logistic.pojo.OrderDetail;
import cn.wl.logistic.pojo.OrderDetailExample;
import cn.wl.logistic.pojo.Putinstorage;
import cn.wl.logistic.pojo.PutinstorageExample;
import cn.wl.logistic.pojo.User;
import cn.wl.logistic.service.BasicDataService;
import cn.wl.logistic.service.CustomerService;
import cn.wl.logistic.service.OfferService;
import cn.wl.logistic.service.OrderDetailService;
import cn.wl.logistic.service.PutinstorageService;
import cn.wl.logistic.service.UserService;
import cn.wl.logistic.utils.Constant;
import cn.wl.logistic.pojo.Order;
import cn.wl.logistic.pojo.OrderExample;
import cn.wl.logistic.pojo.OrderExample.Criteria;
import cn.wl.logistic.service.OrderService;

@Controller
@RequestMapping("/offer")
public class OfferController {
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BasicDataService basicDataService;
	
	@Autowired
	private PutinstorageService putinstorageService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@RequestMapping("/offerPage")
	public String offerPage() {
        return "offerList";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<Order> list(String keyWord,@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="10") Integer pageSize){

		PageHelper.startPage(pageNum, pageSize);
		OrderExample orderExample = new OrderExample();
		Criteria criteria = orderExample.createCriteria();
		criteria.andOrderStatusEqualTo(2);
		List<Order> orders = orderService.selectByExample(orderExample);
		PageInfo<Order> info = new PageInfo<>(orders);
		return info;
	}
	
	@RequestMapping("/returnMsg")
	public String returnMsg(Long orderId,Model m) {
//		根据orderId查询订单表，查询出订单详情
		Order order = orderService.selectByPrimaryKey(orderId);
//		共享数据
		m.addAttribute("order", order);
//		获取userId查询业务员真实姓名
		Long userId = order.getUserId();
		User user = userService.selectByPrimaryKey(userId);
//		共享数据
		m.addAttribute("user", user);
//		获取customerId,然后查询客户表，获取客户对象
		Long customerId = order.getCustomerId();
		CustomerExample customerExample = new CustomerExample();
		cn.wl.logistic.pojo.CustomerExample.Criteria criteria = customerExample.createCriteria();
		criteria.andCustomerIdEqualTo(customerId);
		List<Customer> customers = customerService.selectByExample(customerExample);
//		共享数据
		m.addAttribute("customer", customers.get(0));
//		查询到达国家
		Long intervalId = order.getIntervalId();
		BasicData interval = basicDataService.selectByPrimaryKey(intervalId);
//		查询付款方式
		Long paymentMethodId = order.getPaymentMethodId();
		BasicData paymentMethod = basicDataService.selectByPrimaryKey(paymentMethodId);
//		查询货运方式
		Long freightMethodId = order.getFreightMethodId();
		BasicData freightMethod = basicDataService.selectByPrimaryKey(freightMethodId);
//		查询取件方式
		Long takeMethodId = order.getTakeMethodId();
		BasicData takeMethod = basicDataService.selectByPrimaryKey(takeMethodId);
//		共享数据
		m.addAttribute("interval", interval);
		m.addAttribute("paymentMethod", paymentMethod);
		m.addAttribute("freightMethod", freightMethod);
		m.addAttribute("takeMethod", takeMethod);
//		根据orderId查询入库信息
		PutinstorageExample putinstorageExample = new PutinstorageExample();
		cn.wl.logistic.pojo.PutinstorageExample.Criteria criteria2 = putinstorageExample.createCriteria();
		criteria2.andOrderIdEqualTo(orderId);
		List<Putinstorage> putinstorages = putinstorageService.selectByExample(putinstorageExample);
//		共享数据
		m.addAttribute("putinstorage", putinstorages.get(0));
//		查询出所有的仓库
		List<BasicData> wareHouses = basicDataService.selectByParentName(Constant.BASIC_WAREHOUSE);
//		共享数据
		m.addAttribute("wareHouses", wareHouses);
//		获取当前认证的身份信息
//		User principal = (User) SecurityUtils.getSubject().getPrincipal();
//		共享数据
//		m.addAttribute("principal", principal);
//		查询订单明细
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		cn.wl.logistic.pojo.OrderDetailExample.Criteria criteria3 = orderDetailExample.createCriteria();
		criteria3.andOrderIdEqualTo(orderId);		
		List<OrderDetail> orderDetails = orderDetailService.selectByExample(orderDetailExample);
//		获得单位
		for (int i = 0; i < orderDetails.size(); i++) {
			Long goodsUnit = orderDetails.get(i).getGoodsUnit();
			BasicData basicData = basicDataService.selectByPrimaryKey(goodsUnit);
			orderDetails.get(i).setGoodsUnitName(basicData.getBaseName());
		}
//		共享数据
		m.addAttribute("orderDetails", orderDetails);
		return "offerPage";
	}
	
//	TODO 需要check体重费率等
	
	@RequestMapping("/insert")
	@ResponseBody
	public MessageObject edit(@RequestBody Offer offer,Model m) {
		MessageObject msg = null;
		List<OrderDetail> orderDetails = offer.getOrderDetails();
		Long orderId = offer.getOrderId();
		int row = 0;
		for (int i = 0; i < orderDetails.size(); i++) {
			Double offerValumn = orderDetails.get(i).getOfferValumn();
			Double offerWeight = orderDetails.get(i).getOfferWeight();
			Long orderDetailId = orderDetails.get(i).getOrderDetailId();
			offer.setOrderDetailId(orderDetailId);
			offer.setOfferValumn(offerValumn);
//			只有空运，报价重量按每方不得低于200kg计算，如体积为1方，重量为180kg，
//			则报价重量为200kg；如体积为1方，重量为220kg，则报价重量任为220kg
			if(offerWeight != null ) {
				if(offerWeight/offerValumn < 200 ) {
//					每方低于200kg的情况，设置报价重量200kg
					OfferExample example = new OfferExample();
					cn.wl.logistic.pojo.OfferExample.Criteria criteria3 = example.createCriteria();
					criteria3.andOrderDetailIdEqualTo(orderDetailId);
					offer.setOfferWeight(offerValumn * 200);
//					更新
					offerService.updateByExampleSelective(offer, example);
				}else {
					offer.setOfferWeight(offerWeight);
				}
			}
			
			row = offerService.insert(offer);
		}
		if(row == 1) {
			msg = new MessageObject(1, "业务报价完成");
		}else if(row == 0){
			msg = new MessageObject(0, "业务报价失败，请联系管理员！");
		}
//		修改订单状态
		Order order = orderService.selectByPrimaryKey(orderId);
		order.setOrderStatus(3);
		orderService.updateByPrimaryKeySelective(order);
		return msg;
	}
	
}
