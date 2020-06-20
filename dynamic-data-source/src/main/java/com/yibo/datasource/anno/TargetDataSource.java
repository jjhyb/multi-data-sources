package com.yibo.datasource.anno;

import java.lang.annotation.*;

/**
 * @author: huangyibo
 * @Date: 2020/6/10 22:27
 * @Description: 作用于类、接口或者方法上
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
