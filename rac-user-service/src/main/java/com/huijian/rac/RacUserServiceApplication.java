package com.huijian.rac;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/*import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;*/

@SpringBootApplication
@MapperScan(basePackages = {"com.huijian.rac.mapper"})
@EnableDiscoveryClient
@EnableFeignClients
public class RacUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RacUserServiceApplication.class, args);
    }

}
