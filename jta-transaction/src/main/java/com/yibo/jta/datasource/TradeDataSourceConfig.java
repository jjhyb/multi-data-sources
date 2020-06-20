package com.yibo.jta.datasource;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @author: huangyibo
 * @Date: 2020/6/20 22:25
 * @Description:
 */

@Configuration
@MapperScan(basePackages = "com.yibo.jta.mapper.trade", sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class TradeDataSourceConfig {

    @Value("${spring.datasource.db2.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.db2.username}")
    private String username;

    @Value("${spring.datasource.db2.password}")
    private String password;

    @Value("${spring.datasource.db2.driver-class-name}")
    private String driverClassName;


    @Bean(name = "db2DataSource")
    public DataSource tradeDataSource() {
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(jdbcUrl);
        druidXADataSource.setUsername(username);
        druidXADataSource.setPassword(password);
        druidXADataSource.setDriverClassName(driverClassName);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(druidXADataSource);
        // 必须为数据源指定唯一标识
        atomikosDataSourceBean.setUniqueResourceName("db2DataSource");
        atomikosDataSourceBean.setPoolSize(5);
        atomikosDataSourceBean.setTestQuery("SELECT 1");
        return atomikosDataSourceBean;
    }


    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.yibo.jta.mapper.trade");//指定基包
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/trade/*.xml"));
        return bean.getObject();
    }

    /*@Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
