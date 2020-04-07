package cn.wl.logistic.service;

import java.util.List;

import cn.wl.logistic.pojo.Customer;
import cn.wl.logistic.pojo.CustomerExample;

public interface CustomerService {

	List<Customer> selectByExample(CustomerExample example);
	int deleteByPrimaryKey(Long customerId);
	Customer selectByPrimaryKey(Long customerId);
	int insert(Customer record);
	int updateByPrimaryKeySelective(Customer record);
}
