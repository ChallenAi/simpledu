package com.fengyiai.simpledu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 配置mapper接口位置
@MapperScan("com.fengyiai.simpledu.mapper")
public class SimpleduApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleduApplication.class, args);
    }
}
