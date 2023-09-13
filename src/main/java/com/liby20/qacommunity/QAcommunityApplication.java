package com.liby20.qacommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liby20.qacommunity.mapper")
public class QAcommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(QAcommunityApplication.class, args);
    }

}
