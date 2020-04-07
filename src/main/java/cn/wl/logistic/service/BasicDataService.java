package cn.wl.logistic.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wl.logistic.pojo.BasicData;
import cn.wl.logistic.pojo.BasicDataExample;

public interface BasicDataService {
	long countByExample(BasicDataExample example);

	int deleteByPrimaryKey(Long baseId);

	int insert(BasicData record);

	int insertSelective(BasicData record);

	List<BasicData> selectByExample(BasicDataExample example);

	BasicData selectByPrimaryKey(Long baseId);

	int updateByPrimaryKeySelective(BasicData record);

	int updateByPrimaryKey(BasicData record);
	
	 List<BasicData> selectByParentName(@Param("parentName")String parentName);

}
