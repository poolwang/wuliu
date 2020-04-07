package cn.wl.logistic.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wl.logistic.pojo.Customer;
import cn.wl.logistic.pojo.CustomerExample;
import cn.wl.logistic.service.CustomerService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class CustomerServiceTest {
@Autowired
private CustomerService customerService;

	@Test
	public void testSelectByExample() {
		CustomerExample example=new CustomerExample();
		List<Customer> customers = customerService.selectByExample(example);
	}

}
