package cn.wl.logistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wl.logistic.mapper.OrderDetailMapper;
import cn.wl.logistic.mapper.OrderMapper;
import cn.wl.logistic.pojo.Customer;
import cn.wl.logistic.pojo.CustomerExample;
import cn.wl.logistic.pojo.Order;
import cn.wl.logistic.pojo.OrderDetail;
import cn.wl.logistic.pojo.OrderDetailExample;
import cn.wl.logistic.pojo.OrderExample;
import cn.wl.logistic.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@Override
	public int deleteByPrimaryKey(Long orderId) {

		return orderMapper.deleteByPrimaryKey(orderId);
	}

	@Override
	public int insert(Order record) {
		int row = orderMapper.insert(record);
		if (row==1) {
			List<OrderDetail> orderDetails = record.getOrderDetails();
			for (OrderDetail orderDetail : orderDetails) {
				orderDetail.setOrderId(record.getOrderId());
				orderDetailMapper.insert(orderDetail);
			}
		}
		return row;
	}

	@Override
	public int insertSelective(Order record) {
		
		return orderMapper.insertSelective(record);
	}

	@Override
	public List<Order> selectByExample(OrderExample example) {
		
		return orderMapper.selectByExample(example);
	}

	@Override
	public Order selectByPrimaryKey(Long orderId) {
		
		return orderMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public int updateByPrimaryKeySelective(Order record) {
		
		return orderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<OrderDetail> selectByExample(OrderDetailExample example) {
		
		return orderDetailMapper.selectByExample(example);
	}

}
