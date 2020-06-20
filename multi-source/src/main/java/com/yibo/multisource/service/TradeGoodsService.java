package com.yibo.multisource.service;

import com.yibo.multisource.domain.entity.trade.TradeGoods;
import com.yibo.multisource.domain.entity.vo.TradeGoodsAO;
import com.yibo.multisource.mapper.trade.TradeGoodsMapper;
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

    @Transactional
    public List<TradeGoods> findAll(){
        return tradeGoodsMapper.selectAll();
    }

    @Transactional("db2TransactionManager")
    public String addTradeGoods(TradeGoodsAO tradeGoodsAO){
        TradeGoods tradeGoods = new TradeGoods();
        BeanUtils.copyProperties(tradeGoodsAO,tradeGoods);
        tradeGoods.setAddTime(new Date());
        tradeGoodsMapper.insert(tradeGoods);
        return "SUCCESS";
    }
}
