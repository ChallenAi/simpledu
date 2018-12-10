package com.fengyiai.simpledu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fengyiai.simpledu.mapper")
public class SimpleduApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleduApplication.class, args);
    }
}
