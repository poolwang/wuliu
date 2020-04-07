package cn.wl.logistic.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wl.logistic.pojo.User;
import cn.wl.logistic.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserServiceTest2 {
 @Autowired
 private UserService service;
	@Test
	public void testUpdateByPrimaryKey() {
		User record=new User(1L, "admin", "laowang", "admin", "2ec0", null, null, 1L);
		service.updateByPrimaryKeySelective(record);
	}

}
