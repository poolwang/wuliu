package cn.wl.logistic.mapper;

import cn.wl.logistic.pojo.User;
import cn.wl.logistic.pojo.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectByRoleName(@Param("rolename") String rolename);
    
}