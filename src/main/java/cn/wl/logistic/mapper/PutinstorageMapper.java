package cn.wl.logistic.mapper;

import cn.wl.logistic.pojo.Putinstorage;
import cn.wl.logistic.pojo.PutinstorageExample;
import java.util.List;

public interface PutinstorageMapper {
    long countByExample(PutinstorageExample example);

    int insert(Putinstorage record);

    int insertSelective(Putinstorage record);

    List<Putinstorage> selectByExample(PutinstorageExample example);
}