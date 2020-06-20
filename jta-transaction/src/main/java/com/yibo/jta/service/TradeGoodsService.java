package com.yibo.jta.service;

import com.yibo.jta.domain.ao.TradeGoodsAO;
import com.yibo.jta.domain.entity.TradeGoods;
import com.yibo.jta.mapper.trade.TradeGoodsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: huangyibo
 * @Date: 2020/6/11 0:23
 * @Description:
 */

@Service
public class TradeGoodsService {

    @Autowired
    private TradeGoodsMapper tradeGoodsMapper;

    public List<TradeGoods> findAll(){
        return tradeGoodsMapper.selectAll();
    }

    @Transactional("transactionManager")
    public String addTradeGoods(TradeGoodsAO tradeGoodsAO){
        TradeGoods tradeGoods = new TradeGoods();
        BeanUtils.copyProperties(tradeGoodsAO,tradeGoods);
        tradeGoods.setAddTime(new Date());
        tradeGoodsMapper.insert(tradeGoods);
        return "SUCCESS";
    }
}
