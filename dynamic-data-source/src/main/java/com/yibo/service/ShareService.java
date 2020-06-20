package com.yibo.service;

import com.yibo.center.domain.entity.Share;
import com.yibo.datasource.anno.TargetDataSource;
import com.yibo.mapper.ShareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: huangyibo
 * @Date: 2020/6/10 23:47
 * @Description:
 */

@Service
public class ShareService {

    @Autowired
    private ShareMapper shareMapper;

    @TargetDataSource(name = "ds1")
    @Transactional
    public List<Share> findAll(){
        return shareMapper.selectAll();
    }
}
