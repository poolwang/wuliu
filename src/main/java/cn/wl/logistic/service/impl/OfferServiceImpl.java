package cn.wl.logistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wl.logistic.mapper.OfferMapper;
import cn.wl.logistic.pojo.Offer;
import cn.wl.logistic.pojo.OfferExample;
import cn.wl.logistic.service.OfferService;
@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferMapper offerMapper;
	public long countByExample(OfferExample example) {
		
		return offerMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(OfferExample example) {
		
		return offerMapper.deleteByExample(example);
	}

	@Override
	public int insert(Offer record) {
		
		return offerMapper.insert(record);
	}

	@Override
	public int insertSelective(Offer record) {
		
		return offerMapper.insertSelective(record);
	}

	@Override
	public List<Offer> selectByExample(OfferExample example) {
		
		return offerMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(Offer record, OfferExample example) {
		
		return offerMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Offer record, OfferExample example) {
		
		return offerMapper.updateByExample(record, example);
	}

}
