package cn.wl.logistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wl.logistic.mapper.OrderDetailMapper;
import cn.wl.logistic.pojo.OrderDetail;
import cn.wl.logistic.pojo.OrderDetailExample;
import cn.wl.logistic.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailMapper orderDetailMapper;

	

	@Override
	public int deleteByPrimaryKey(Long orderDetailId) {
		
		return orderDetailMapper.deleteByPrimaryKey(orderDetailId);
	}

	@Override
	public int insert(OrderDetail record) {
		
		return orderDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderDetail record) {
		
		return orderDetailMapper.insertSelective(record);
	}

	@Override
	public List<OrderDetail> selectByExample(OrderDetailExample orderDetailExample) {
		
		return orderDetailMapper.selectByExample(orderDetailExample);
	}

	@Override
	public OrderDetail selectByPrimaryKey(Long orderDetailId) {
		
		return orderDetailMapper.selectByPrimaryKey(orderDetailId);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderDetail record) {
		
		return orderDetailMapper.updateByPrimaryKeySelective(record);
	}
	
	
}
