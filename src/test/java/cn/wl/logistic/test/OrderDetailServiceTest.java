package cn.wl.logistic.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wl.logistic.service.OrderDetailService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class OrderDetailServiceTest {
@Autowired
private OrderDetailService orderDetailService;
	@Test
	public void testDeleteByPrimaryKey() {
	
		orderDetailService.deleteByPrimaryKey(39L);
	}

}
