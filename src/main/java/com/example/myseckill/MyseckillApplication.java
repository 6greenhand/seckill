package com.example.myseckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.myseckill.mapper")
public class MyseckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyseckillApplication.class, args);
    }

}
