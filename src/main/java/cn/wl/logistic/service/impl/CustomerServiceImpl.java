package cn.wl.logistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wl.logistic.mapper.CustomerMapper;
import cn.wl.logistic.pojo.Customer;
import cn.wl.logistic.pojo.CustomerExample;
import cn.wl.logistic.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private CustomerMapper customerMapper;
	@Override
	public List<Customer> selectByExample(CustomerExample example) {
		
		return customerMapper.selectByExample(example);
	}
	@Override
	public int deleteByPrimaryKey(Long customerId) {
		
		return customerMapper.deleteByPrimaryKey(customerId);
	}
	@Override
	public Customer selectByPrimaryKey(Long customerId) {
		
		return customerMapper.selectByPrimaryKey(customerId);
	}
	@Override
	public int insert(Customer record) {
		
		return customerMapper.insert(record);
	}
	@Override
	public int updateByPrimaryKeySelective(Customer record) {
		
		return customerMapper.updateByPrimaryKeySelective(record);
	}

}
