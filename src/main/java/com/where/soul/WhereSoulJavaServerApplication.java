package com.where.soul;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lw
 */
// @Slf4j
@SpringBootApplication
@MapperScan(basePackages = "com.where.soul.*.mapper")
public class WhereSoulJavaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhereSoulJavaServerApplication.class, args);
    }

}
