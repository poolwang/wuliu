package cn.wl.logistic.mapper;

import cn.wl.logistic.pojo.Offer;
import cn.wl.logistic.pojo.OfferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OfferMapper {
    long countByExample(OfferExample example);

    int deleteByExample(OfferExample example);

    int insert(Offer record);

    int insertSelective(Offer record);

    List<Offer> selectByExample(OfferExample example);

    int updateByExampleSelective(@Param("record") Offer record, @Param("example") OfferExample example);

    int updateByExample(@Param("record") Offer record, @Param("example") OfferExample example);
}