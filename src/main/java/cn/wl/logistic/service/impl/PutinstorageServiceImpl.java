package cn.wl.logistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wl.logistic.mapper.PutinstorageMapper;
import cn.wl.logistic.pojo.Putinstorage;
import cn.wl.logistic.pojo.PutinstorageExample;
import cn.wl.logistic.service.PutinstorageService;

@Service
public class PutinstorageServiceImpl implements PutinstorageService {
	@Autowired
	private PutinstorageMapper putinstorageMapper;

	@Override
	public long countByExample(PutinstorageExample example) {

		return putinstorageMapper.countByExample(example);
	}

	@Override
	public int insert(Putinstorage record) {

		return putinstorageMapper.insert(record);
	}

	@Override
	public int insertSelective(Putinstorage record) {

		return putinstorageMapper.insertSelective(record);
	}

	@Override
	public List<Putinstorage> selectByExample(PutinstorageExample example) {

		return putinstorageMapper.selectByExample(example);
	}

}
