package cn.wl.logistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wl.logistic.mapper.FinanceMapper;
import cn.wl.logistic.pojo.Finance;
import cn.wl.logistic.pojo.FinanceExample;
import cn.wl.logistic.service.FinanceService;

@Service
public class FinanceServiceImpl implements FinanceService{
	
	@Autowired
	private FinanceMapper  financeMapper;
	

	@Override
	public long countByExample(FinanceExample example) {
		return financeMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(FinanceExample example) {
		return financeMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long orderId) {
		return financeMapper.deleteByPrimaryKey(orderId);
	}

	@Override
	public int insert(Finance record) {
		return financeMapper.insert(record);
	}

	@Override
	public int insertSelective(Finance record) {
		return financeMapper.insertSelective(record);
	}

	@Override
	public List<Finance> selectByExample(FinanceExample example) {
		return financeMapper.selectByExample(example);
	}

	@Override
	public Finance selectByPrimaryKey(Long orderId) {
		return financeMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public int updateByExampleSelective(Finance record, FinanceExample example) {
		return financeMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Finance record, FinanceExample example) {
		return financeMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Finance record) {
		return financeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Finance record) {
		return financeMapper.updateByPrimaryKey(record);
	}

}
