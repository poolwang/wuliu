package cn.wl.logistic.controller;

import java.text.DecimalFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.wl.logistic.pojo.BasicData;
import cn.wl.logistic.pojo.Customer;
import cn.wl.logistic.pojo.CustomerExample;
import cn.wl.logistic.pojo.Finance;
import cn.wl.logistic.pojo.Offer;
import cn.wl.logistic.pojo.OfferExample;
import cn.wl.logistic.pojo.Order;
import cn.wl.logistic.pojo.OrderDetail;
import cn.wl.logistic.pojo.OrderDetailExample;
import cn.wl.logistic.pojo.OrderExample;
import cn.wl.logistic.pojo.OrderExample.Criteria;
import cn.wl.logistic.pojo.Putinstorage;
import cn.wl.logistic.pojo.PutinstorageExample;
import cn.wl.logistic.pojo.User;
import cn.wl.logistic.service.BasicDataService;
import cn.wl.logistic.service.CustomerService;
import cn.wl.logistic.service.FinanceService;
import cn.wl.logistic.service.OfferService;
import cn.wl.logistic.service.OrderDetailService;
import cn.wl.logistic.service.OrderService;
import cn.wl.logistic.service.PutinstorageService;
import cn.wl.logistic.service.UserService;
import cn.wl.logistic.utils.Constant;

@Controller
@RequestMapping("/finance")
public class FinanceController {
	
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
	
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private FinanceService financeService;
	
	
	@RequestMapping("/financePage")
	public String financePage() {
		return "financeList";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<Order> list(String keyWord,@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="10") Integer pageSize){
//		分页
		PageHelper.startPage(pageNum, pageSize);
//		筛选
		OrderExample orderExample = new OrderExample();
		Criteria criteria = orderExample.createCriteria();
//		查询出进行了核对入库等待财务审核的订单
		criteria.andOrderStatusEqualTo(3);
		List<Order> orders = orderService.selectByExample(orderExample);
//		创建分页对象
		PageInfo<Order> info = new PageInfo<>(orders);
//		返回分页对象
		return info;
	}
	
//	回显数据的方法
	@RequestMapping("/returnMsg")
	public String returnMsg(Long orderId,Model m) {
		
//		double保留两位小数
		DecimalFormat df = new DecimalFormat("#.00");
//		根据orderId查询订单的基本信息
		Order order = orderService.selectByPrimaryKey(orderId);
//		共享数据
		m.addAttribute("order", order);
		
		Long userId = order.getUserId();
		User user = userService.selectByPrimaryKey(userId);
		m.addAttribute("user", user);
		
		Long customerId = order.getCustomerId();
		CustomerExample customerExample = new CustomerExample();
		cn.wl.logistic.pojo.CustomerExample.Criteria criteria = customerExample.createCriteria();
		criteria.andCustomerIdEqualTo(customerId);
		List<Customer> customers = customerService.selectByExample(customerExample);
		m.addAttribute("customer", customers.get(0));
		
		Long intervalId = order.getIntervalId();
		BasicData interval = basicDataService.selectByPrimaryKey(intervalId);
		m.addAttribute("interval", interval);
//		查询货运方式
		Long paymentMethodId = order.getPaymentMethodId();
		BasicData paymentMethod = basicDataService.selectByPrimaryKey(paymentMethodId);
		m.addAttribute("paymentMethod",paymentMethod);
//		查询货运方式
		Long freightMethodId = order.getFreightMethodId();
		BasicData freightMethod = basicDataService.selectByPrimaryKey(freightMethodId);
		m.addAttribute("freightMethod", freightMethod);
		
		
		Long takeMethodId = order.getTakeMethodId();
		BasicData takeMethod = basicDataService.selectByPrimaryKey(takeMethodId);
		m.addAttribute("takeMethod", takeMethod);
		
		User principal = (User) SecurityUtils.getSubject().getPrincipal();
		m.addAttribute("principal", principal);
		
		PutinstorageExample putinstorageExample = new PutinstorageExample();
		cn.wl.logistic.pojo.PutinstorageExample.Criteria criteria2 = putinstorageExample.createCriteria();
		criteria2.andOrderIdEqualTo(orderId);
		List<Putinstorage> putinstorages = putinstorageService.selectByExample(putinstorageExample);
		Integer warehouseId = putinstorages.get(0).getWarehouseId();
		BasicData wareHouse = basicDataService.selectByPrimaryKey(warehouseId.longValue());
		m.addAttribute("wareHouse", wareHouse);
		
		Finance finance = new Finance();
		
//		总体积
		Double totalVolumn = 0.0;
//		总重量
		Double totalWeight = 0.0;
//		总价值
		Double totalValue = 0.0;
		Double offerVolumnUnitPrice = 0.0;
		Double offerWeightUnitPrice = 0.0;
//		体积费用=报价体积*报价体积费率
		Double volumnExpense = 0.0;
//		重量费用
		Double weightExpense = 0.0;
//		关税
		Double rateExpense = 0.0;
//		税率
		int taxRate = 0; 
//		总费用
		Double totalExpense = 0.0;
//		取件费用
		Double offerTakeExpense = 0.0;
		
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		cn.wl.logistic.pojo.OrderDetailExample.Criteria criteria3 = orderDetailExample.createCriteria();
		criteria3.andOrderIdEqualTo(orderId);
		List<OrderDetail> orderDetails = orderDetailService.selectByExample(orderDetailExample);
		for (int i = 0; i < orderDetails.size(); i++) {
//			查询单位
			Long goodsUnit = orderDetails.get(i).getGoodsUnit();
			BasicData basicData = basicDataService.selectByPrimaryKey(goodsUnit);
			orderDetails.get(i).setGoodsUnitName(basicData.getBaseName());
//			根据orderDetailId查询业务报价表
			OfferExample offerExample = new OfferExample();
			cn.wl.logistic.pojo.OfferExample.Criteria criteria4 = offerExample.createCriteria();
			criteria4.andOrderDetailIdEqualTo(orderDetails.get(i).getOrderDetailId());
			List<Offer> offers = offerService.selectByExample(offerExample);
			Double offerValumn = offers.get(0).getOfferValumn();
			Double offerWeight = offers.get(0).getOfferWeight();
//			获取业务报价的体积费率，重量费率，取件费用
			offerVolumnUnitPrice = offers.get(0).getOfferVolumnUnitPrice();
			offerWeightUnitPrice = offers.get(0).getOfferWeightUnitPrice();
			offerTakeExpense = offers.get(0).getOfferTakeExpense();
			
//			共享数据
			m.addAttribute("offerVolumnUnitPrice", offerVolumnUnitPrice);
			m.addAttribute("offerWeightUnitPrice",offerWeightUnitPrice);
			m.addAttribute("offerTakeExpense", offerTakeExpense);
			
			orderDetails.get(i).setOfferValumn(offerValumn);
			orderDetails.get(i).setOfferWeight(offerWeight);
//			获取总价
			Long goodsTotal = orderDetails.get(i).getGoodsTotal();
//			计算总体积，总重量，总价值
			totalVolumn += offerValumn;
			if(offerWeight != null) {
				totalWeight += offerWeight;
			}
			totalValue += goodsTotal;
		}
		m.addAttribute("orderDetails", orderDetails);
//		计算体积费用
		volumnExpense = totalVolumn*offerVolumnUnitPrice;
//		计算重量费用
		weightExpense = totalWeight*offerWeightUnitPrice;
		
//		如果货运方式为海运，并且到达国家为新加坡、澳大利亚才需要收取关税，其它国家默认关税费率为0%
		if(paymentMethod.getBaseName().equals(cn.wl.logistic.utils.Constant.BASIC_SEA)) {
			if(freightMethod.getBaseName().equals(Constant.BASIC_SINGAPORE) || freightMethod.getBaseName().equals(Constant.BASIC_AUSTRALIAN)) {
//				关税=货物总价值*7%
				rateExpense = totalValue * 0.07;
				taxRate = 7;
			}
		}
		totalExpense = volumnExpense + weightExpense + rateExpense + offerTakeExpense; 
		
		finance.setOrderId(orderId);
		finance.setTotalVolumn(totalVolumn);
		finance.setTotalWeight(totalWeight);
		finance.setTotalValue(totalValue);
		finance.setOtherExpense(0.0);
		finance.setVolumnExpense(Double.parseDouble(df.format(volumnExpense)));
		finance.setWeightExpense(weightExpense);
		finance.setRateExpense(rateExpense);
		finance.setTaxRate(taxRate);
		finance.setTotalExpense(totalExpense);
		
		Finance finance2 = financeService.selectByPrimaryKey(orderId);
		if(finance2 == null) {
			financeService.insert(finance);
		}
		
		m.addAttribute("finance", finance);
		
		return "financePage";
	}

}
