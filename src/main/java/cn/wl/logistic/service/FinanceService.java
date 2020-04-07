package cn.wl.logistic.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wl.logistic.pojo.Finance;
import cn.wl.logistic.pojo.FinanceExample;

public interface FinanceService {
	    long countByExample(FinanceExample example);

	    int deleteByExample(FinanceExample example);

	    int deleteByPrimaryKey(Long orderId);

	    int insert(Finance record);

	    int insertSelective(Finance record);

	    List<Finance> selectByExample(FinanceExample example);

	    Finance selectByPrimaryKey(Long orderId);

	    int updateByExampleSelective(@Param("record") Finance record, @Param("example") FinanceExample example);

	    int updateByExample(@Param("record") Finance record, @Param("example") FinanceExample example);

	    int updateByPrimaryKeySelective(Finance record);

	    int updateByPrimaryKey(Finance record);

}
