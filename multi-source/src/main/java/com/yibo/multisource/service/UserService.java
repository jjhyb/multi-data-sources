package com.yibo.multisource.service;

import com.yibo.multisource.domain.entity.user.User;
import com.yibo.multisource.domain.entity.vo.UserAO;
import com.yibo.multisource.mapper.user.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<User> findAll(){
        return userMapper.selectAll();
    }

    @Transactional
    public User findById(Integer id){
        User user = new User();
        user.setId(id);
        return userMapper.selectOne(user);
    }

    @Transactional("db1TransactionManager")
    public String addUser(UserAO userAO){
        User user = new User();
        BeanUtils.copyProperties(userAO,user);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
        return "SUCCESS";
    }
}
