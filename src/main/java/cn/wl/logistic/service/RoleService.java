package cn.wl.logistic.service;

import java.util.List;

import cn.wl.logistic.pojo.Role;
import cn.wl.logistic.pojo.RoleExample;

public interface RoleService {
	int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}
