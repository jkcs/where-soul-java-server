package com.where.soul;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

/**
 * @author lw
 */
@SpringBootApplication
@MapperScan(basePackages = "com.where.soul.*.mapper")
public class WhereSoulJavaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhereSoulJavaServerApplication.class, args);
    }

}
