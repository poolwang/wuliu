package cn.wl.logistic.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wl.logistic.service.BasicDataService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class BasicDataServiceTest {

	@Autowired
	private BasicDataService basicDataService;
	@Test
	public void testSelectByParentName() {
	
		basicDataService.selectByParentName("区间管理");
	}

}
