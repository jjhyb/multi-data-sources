package com.yibo.datasource.aspect;

import com.yibo.datasource.DynamicDataSourceContextHolder;
import com.yibo.datasource.anno.TargetDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: huangyibo
 * @Date: 2020/6/10 22:28
 * @Description: 动态数据源通知
 */
@Aspect
//保证该AOP在@Transactional之前执行
@Order(-1)
@Component
@Slf4j
public class DynamicDataSourceAspect {

    /**
     * @Description 在方法执行之前执行  @annotation(ds) 会拦截有ds这个注解的方法即有 TargetDataSource这个注解的
     * @param @param point
     * @param @param ds
     * @param @throws Throwable 参数
     * @return void 返回类型
     * @throws
     */
    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds)
            throws Throwable {
        String dsId = ds.name();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            log.error("数据源[{}]不存在，使用默认数据源 > {}", ds.name(), point.getSignature());
        }
        else {
            log.debug("Use DataSource : {} > {}", ds.name(),point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(ds.name());
        }
    }

    /**
     * @Description 在方法执行之后执行  @annotation(ds) 会拦截有ds这个注解的方法即有 TargetDataSource这个注解的
     * @param @param point
     * @param @param ds 参数
     * @return void 返回类型
     * @throws
     */
    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        log.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
