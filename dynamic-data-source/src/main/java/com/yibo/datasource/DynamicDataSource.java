package com.yibo.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author: huangyibo
 * @Date: 2020/6/10 22:29
 * @Description: 继承Spring AbstractRoutingDataSource实现路由切换
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
