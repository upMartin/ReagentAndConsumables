package com.huijian.rac.controller;

import com.huijian.rac.bean.Order;
import com.huijian.rac.bean.OrderDetail;
import com.huijian.rac.bean.OrderOperate;
import com.huijian.rac.service.OrderOperatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/OrderSchedule")
public class OrderOperatingController {
    @Autowired
    OrderOperatingService orderOperatingService;

    @RequestMapping("/inquiryOrderSchedule")
    @ResponseBody
    public Map<String, Object> inquiryOrderSchedule(@RequestBody Order order){
        List<OrderOperate> list = orderOperatingService.inquiryOrderSchedule(order.getOrderNo());
        Map<String, Object> map = new HashMap<>();
        map.put("OrderSchedule", list);
        return map;
    }
}
