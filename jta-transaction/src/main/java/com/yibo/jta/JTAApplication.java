package com.yibo.jta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: huangyibo
 * @Date: 2020/6/20 22:10
 * @Description:
 */

@SpringBootApplication
//@MapperScan("com.yibo.jta.mapper")
public class JTAApplication {

    public static void main(String[] args) {
        SpringApplication.run(JTAApplication.class,args);
    }
}
