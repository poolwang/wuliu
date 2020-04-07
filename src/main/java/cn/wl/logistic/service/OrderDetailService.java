package cn.wl.logistic.service;

import java.util.List;

import cn.wl.logistic.pojo.OrderDetail;
import cn.wl.logistic.pojo.OrderDetailExample;

public interface OrderDetailService {
	

	int deleteByPrimaryKey(Long orderDetailId);

	int insert(OrderDetail record);

	int insertSelective(OrderDetail record);

	List<OrderDetail> selectByExample(OrderDetailExample orderDetailExample);

	OrderDetail selectByPrimaryKey(Long orderDetailId);

	int updateByPrimaryKeySelective(OrderDetail record);
}
