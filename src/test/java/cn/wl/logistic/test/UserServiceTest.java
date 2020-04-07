package cn.wl.logistic.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wl.logistic.pojo.User;
import cn.wl.logistic.pojo.UserExample;
import cn.wl.logistic.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserServiceTest {
     @Autowired
     private UserService userService;
	@Test
	public void testSelectByExample() {
		UserExample example=new UserExample();
		List<User> list = userService.selectByExample(example);
		System.out.println("users:"+list);
	}

}
