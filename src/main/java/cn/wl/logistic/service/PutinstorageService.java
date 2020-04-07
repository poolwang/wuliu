package cn.wl.logistic.service;

import java.util.List;

import cn.wl.logistic.pojo.Putinstorage;
import cn.wl.logistic.pojo.PutinstorageExample;

public interface PutinstorageService {
	long countByExample(PutinstorageExample example);

    int insert(Putinstorage record);

    int insertSelective(Putinstorage record);

    List<Putinstorage> selectByExample(PutinstorageExample example);
}
