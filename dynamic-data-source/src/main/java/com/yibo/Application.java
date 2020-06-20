package com.yibo;

import com.yibo.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: huangyibo
 * @Date: 2020/6/10 23:34
 * @Description:
 */

@Import({DynamicDataSourceRegister.class})
@MapperScan("com.yibo.mapper")//扫描Mapper接口
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
