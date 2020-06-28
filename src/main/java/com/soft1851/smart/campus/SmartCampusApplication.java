package com.soft1851.smart.campus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author
 */
@SpringBootApplication
@EnableJpaAuditing//自动更新时间
@MapperScan("com.soft1851.smart.campus.errends.mapper")
@MapperScan("com.soft1851.smart.campus.mapper")
public class SmartCampusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartCampusApplication.class, args);
    }
}
