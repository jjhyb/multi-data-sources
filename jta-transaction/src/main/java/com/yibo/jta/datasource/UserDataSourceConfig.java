package com.yibo.jta.datasource;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.jta.JtaTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

/**
 * @author: huangyibo
 * @Date: 2020/6/20 22:24
 * @Description:
 */

@Configuration
@MapperScan(basePackages = "com.yibo.jta.mapper.user", sqlSessionTemplateRef = "db1SqlSessionTemplate")
public class UserDataSourceConfig {

    @Value("${spring.datasource.db1.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.db1.username}")
    private String username;

    @Value("${spring.datasource.db1.password}")
    private String password;

    @Value("${spring.datasource.db1.driver-class-name}")
    private String driverClassName;


    /**
     * 生成数据源.  @Primary 注解声明为默认数据源
     */
    @Bean(name = "db1DataSource")
    @Primary
    public DataSource userDataSource() {
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(jdbcUrl);
        druidXADataSource.setUsername(username);
        druidXADataSource.setPassword(password);
        druidXADataSource.setDriverClassName(driverClassName);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(druidXADataSource);
        // 必须为数据源指定唯一标识
        atomikosDataSourceBean.setUniqueResourceName("db1DataSource");
        atomikosDataSourceBean.setPoolSize(5);
        atomikosDataSourceBean.setTestQuery("SELECT 1");
        return atomikosDataSourceBean;
    }

    /**
     * 创建 SqlSessionFactory
     */
    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.yibo.jta.mapper.user");//指定基包
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/user/*.xml"));
        return bean.getObject();
    }

    /**
     * 使用这个来做总事务 后面的数据源就不用设置事务了
     */
    @Bean(name = "transactionManager")
    @Primary
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

    @Bean(name = "db1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
