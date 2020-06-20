package com.yibo.jta.service;

import com.yibo.jta.domain.ao.UserAO;
import com.yibo.jta.domain.entity.TradeGoods;
import com.yibo.jta.domain.entity.User;
import com.yibo.jta.mapper.trade.TradeGoodsMapper;
import com.yibo.jta.mapper.user.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: huangyibo
 * @Date: 2020/6/10 23:46
 * @Description:
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TradeGoodsMapper tradeGoodsMapper;

    public List<User> findAll(){
        return userMapper.selectAll();
    }

    public User findById(Integer id){
        User user = new User();
        user.setId(id);
        return userMapper.selectOne(user);
    }

    @Transactional("transactionManager")
    public String addUser(UserAO userAO){
        User user = new User();
        BeanUtils.copyProperties(userAO,user);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
        TradeGoods tradeGoods = new TradeGoods();
        tradeGoods.setGoodsName("sharding jdbc");
        tradeGoods.setGoodsNumber(100);
        tradeGoods.setGoodsPrice(new BigDecimal(50));
        tradeGoods.setGoodsDesc("string desc");
        tradeGoods.setAddTime(new Date());
        tradeGoodsMapper.insert(tradeGoods);
        return "SUCCESS";
    }
}
