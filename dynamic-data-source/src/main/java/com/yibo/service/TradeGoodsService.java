package com.yibo.service;

import com.yibo.center.domain.entity.TradeGoods;
import com.yibo.center.domain.vo.TradeGoodsAO;
import com.yibo.datasource.anno.TargetDataSource;
import com.yibo.mapper.TradeGoodsMapper;
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

    @TargetDataSource(name = "ds2")
    @Transactional
    public List<TradeGoods> findAll(){
        return tradeGoodsMapper.selectAll();
    }

    @TargetDataSource(name = "ds2")
    @Transactional
    public String addTradeGoods(TradeGoodsAO tradeGoodsAO){
        TradeGoods tradeGoods = new TradeGoods();
        BeanUtils.copyProperties(tradeGoodsAO,tradeGoods);
        tradeGoods.setAddTime(new Date());
        tradeGoodsMapper.insert(tradeGoods);
        return "SUCCESS";
    }
}
