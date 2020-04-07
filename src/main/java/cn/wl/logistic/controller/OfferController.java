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
//		����orderId��ѯ��������ѯ����������
		Order order = orderService.selectByPrimaryKey(orderId);
//		��������
		m.addAttribute("order", order);
//		��ȡuserId��ѯҵ��Ա��ʵ����
		Long userId = order.getUserId();
		User user = userService.selectByPrimaryKey(userId);
//		��������
		m.addAttribute("user", user);
//		��ȡcustomerId,Ȼ���ѯ�ͻ�����ȡ�ͻ�����
		Long customerId = order.getCustomerId();
		CustomerExample customerExample = new CustomerExample();
		cn.wl.logistic.pojo.CustomerExample.Criteria criteria = customerExample.createCriteria();
		criteria.andCustomerIdEqualTo(customerId);
		List<Customer> customers = customerService.selectByExample(customerExample);
//		��������
		m.addAttribute("customer", customers.get(0));
//		��ѯ�������
		Long intervalId = order.getIntervalId();
		BasicData interval = basicDataService.selectByPrimaryKey(intervalId);
//		��ѯ���ʽ
		Long paymentMethodId = order.getPaymentMethodId();
		BasicData paymentMethod = basicDataService.selectByPrimaryKey(paymentMethodId);
//		��ѯ���˷�ʽ
		Long freightMethodId = order.getFreightMethodId();
		BasicData freightMethod = basicDataService.selectByPrimaryKey(freightMethodId);
//		��ѯȡ����ʽ
		Long takeMethodId = order.getTakeMethodId();
		BasicData takeMethod = basicDataService.selectByPrimaryKey(takeMethodId);
//		��������
		m.addAttribute("interval", interval);
		m.addAttribute("paymentMethod", paymentMethod);
		m.addAttribute("freightMethod", freightMethod);
		m.addAttribute("takeMethod", takeMethod);
//		����orderId��ѯ�����Ϣ
		PutinstorageExample putinstorageExample = new PutinstorageExample();
		cn.wl.logistic.pojo.PutinstorageExample.Criteria criteria2 = putinstorageExample.createCriteria();
		criteria2.andOrderIdEqualTo(orderId);
		List<Putinstorage> putinstorages = putinstorageService.selectByExample(putinstorageExample);
//		��������
		m.addAttribute("putinstorage", putinstorages.get(0));
//		��ѯ�����еĲֿ�
		List<BasicData> wareHouses = basicDataService.selectByParentName(Constant.BASIC_WAREHOUSE);
//		��������
		m.addAttribute("wareHouses", wareHouses);
//		��ȡ��ǰ��֤�������Ϣ
//		User principal = (User) SecurityUtils.getSubject().getPrincipal();
//		��������
//		m.addAttribute("principal", principal);
//		��ѯ������ϸ
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		cn.wl.logistic.pojo.OrderDetailExample.Criteria criteria3 = orderDetailExample.createCriteria();
		criteria3.andOrderIdEqualTo(orderId);		
		List<OrderDetail> orderDetails = orderDetailService.selectByExample(orderDetailExample);
//		��õ�λ
		for (int i = 0; i < orderDetails.size(); i++) {
			Long goodsUnit = orderDetails.get(i).getGoodsUnit();
			BasicData basicData = basicDataService.selectByPrimaryKey(goodsUnit);
			orderDetails.get(i).setGoodsUnitName(basicData.getBaseName());
		}
//		��������
		m.addAttribute("orderDetails", orderDetails);
		return "offerPage";
	}
	
//	TODO ��Ҫcheck���ط��ʵ�
	
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
//			ֻ�п��ˣ�����������ÿ�����õ���200kg���㣬�����Ϊ1��������Ϊ180kg��
//			�򱨼�����Ϊ200kg�������Ϊ1��������Ϊ220kg���򱨼�������Ϊ220kg
			if(offerWeight != null ) {
				if(offerWeight/offerValumn < 200 ) {
//					ÿ������200kg����������ñ�������200kg
					OfferExample example = new OfferExample();
					cn.wl.logistic.pojo.OfferExample.Criteria criteria3 = example.createCriteria();
					criteria3.andOrderDetailIdEqualTo(orderDetailId);
					offer.setOfferWeight(offerValumn * 200);
//					����
					offerService.updateByExampleSelective(offer, example);
				}else {
					offer.setOfferWeight(offerWeight);
				}
			}
			
			row = offerService.insert(offer);
		}
		if(row == 1) {
			msg = new MessageObject(1, "ҵ�񱨼����");
		}else if(row == 0){
			msg = new MessageObject(0, "ҵ�񱨼�ʧ�ܣ�����ϵ����Ա��");
		}
//		�޸Ķ���״̬
		Order order = orderService.selectByPrimaryKey(orderId);
		order.setOrderStatus(3);
		orderService.updateByPrimaryKeySelective(order);
		return msg;
	}
	
}
