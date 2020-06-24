package com.huijian.rac.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "rac-stock-service")
public interface FeignTest {
    @RequestMapping(value = "/stock/hello")
    @ResponseBody
    String hello();
}

