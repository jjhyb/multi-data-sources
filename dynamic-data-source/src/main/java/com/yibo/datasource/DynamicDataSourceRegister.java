package com.yibo.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: huangyibo
 * @Date: 2020/6/10 22:10
 * @Description: 注册动态数据源
 *  初始化数据源和提供了执行动态切换数据源的工具类
 *  EnvironmentAware（获取配置文件配置的属性值）
 */

@Slf4j
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    //指定默认数据源(springboot2.0默认数据源是hikari如何想使用其他数据源可以自己配置)
    private static final String DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";

    //默认数据源
    private DataSource defaultDataSource;

    //用户自定义数据源
    private Map<String, DataSource> customDataSources  = new HashMap<>();

    /**
     * 加载多数据源配置
     * @param env
     */
    @Override
    public void setEnvironment(Environment env) {
        initDefaultDataSource(env);
        initCustomDataSources(env);
    }



    /**
     * 初始化主数据源
     * @param env
     */
    private void initDefaultDataSource(Environment env) {
        // 读取主数据源
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("driver", env.getProperty("spring.datasource.hikari.driver-class-name"));
        dsMap.put("url", env.getProperty("spring.datasource.url"));
        dsMap.put("username", env.getProperty("spring.datasource.hikari.username"));
        dsMap.put("password", env.getProperty("spring.datasource.hikari.password"));
        defaultDataSource = buildDataSource(dsMap);
    }


    /**
     * 初始化更多数据源
     * @param env
     */
    private void initCustomDataSources(Environment env) {
        // 读取配置文件获取更多数据源
        String dsPrefixs = env.getProperty("custom.datasource.names");
        for (String dsPrefix : dsPrefixs.split(",")) {
            // 多个数据源
            Map<String, Object> dsMap = new HashMap<>();
            dsMap.put("driver", env.getProperty("custom.datasource." + dsPrefix + ".driver-class-name"));
            dsMap.put("url", env.getProperty("custom.datasource." + dsPrefix + ".url"));
            dsMap.put("username", env.getProperty("custom.datasource." + dsPrefix + ".username"));
            dsMap.put("password", env.getProperty("custom.datasource." + dsPrefix + ".password"));
            DataSource ds = buildDataSource(dsMap);
            customDataSources.put(dsPrefix, ds);
        }
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 将主数据源添加到更多数据源中
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        // 添加更多数据源
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition); // 注册到Spring容器中

        log.info("Dynamic DataSource Registry");
    }

    /**
     * 创建DataSource
     * @param dsMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            Object type = dsMap.get("type");
            if (type == null)
                type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource

            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>)Class.forName((String)type);
            log.info("dsMap:{}",dsMap);
            System.out.println(dsMap);
            String driverClassName = dsMap.get("driver").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();
            // 自定义DataSource配置
            DataSourceBuilder factory = DataSourceBuilder.create()
                    .driverClassName(driverClassName)
                    .url(url)
                    .username(username)
                    .password(password)
                    .type(dataSourceType);
            return factory.build();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
