package cn.wl.logistic.service;

import java.util.List;

import cn.wl.logistic.pojo.User;
import cn.wl.logistic.pojo.UserExample;

public interface UserService {
	long countByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectByRoleName(String rolename);
	
	

}
