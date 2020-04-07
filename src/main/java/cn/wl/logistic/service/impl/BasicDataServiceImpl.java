package cn.wl.logistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wl.logistic.mapper.BasicDataMapper;
import cn.wl.logistic.pojo.BasicData;
import cn.wl.logistic.pojo.BasicDataExample;
import cn.wl.logistic.service.BasicDataService;
@Service
public class BasicDataServiceImpl implements BasicDataService {
	
	@Autowired
	private BasicDataMapper basicDataMapper;

	@Override
	public long countByExample(BasicDataExample example) {
		return basicDataMapper.countByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long baseId) {
		return basicDataMapper.deleteByPrimaryKey(baseId);
	}

	@Override
	public int insert(BasicData record) {
		return basicDataMapper.insert(record);
	}

	@Override
	public int insertSelective(BasicData record) {
		return basicDataMapper.insertSelective(record);
	}

	@Override
	public List<BasicData> selectByExample(BasicDataExample example) {
		return basicDataMapper.selectByExample(example);
	}

	@Override
	public BasicData selectByPrimaryKey(Long baseId) {
		return basicDataMapper.selectByPrimaryKey(baseId);
	}

	@Override
	public int updateByPrimaryKeySelective(BasicData record) {
		return basicDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BasicData record) {
		return basicDataMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<BasicData> selectByParentName(String parentName) {
		
		return basicDataMapper.selectByParentName(parentName);
	}

	

}
