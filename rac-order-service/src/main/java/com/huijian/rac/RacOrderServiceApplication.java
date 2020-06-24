package com.huijian.rac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.huijian.rac.mapper"})
public class RacOrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RacOrderServiceApplication.class, args);
    }

}
