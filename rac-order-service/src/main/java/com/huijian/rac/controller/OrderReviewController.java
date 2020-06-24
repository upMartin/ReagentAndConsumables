package com.huijian.rac.controller;

import com.alibaba.fastjson.JSONObject;
import com.huijian.rac.bean.*;
import com.huijian.rac.service.HospitalService;
import com.huijian.rac.service.OrderOperatingService;
import com.huijian.rac.service.OrderReviewService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/ReviewOrder")
@RabbitListener(queues = "order")
public class OrderReviewController {
    @Autowired
    OrderReviewService orderReviewService;
    @Autowired
    HospitalService hospitalService;
    @Autowired
    WebSocket webSocket;
    @Autowired
    OrderOperatingService orderOperatingService;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Order order = new Order();

    @RequestMapping("/inquiry")
    @ResponseBody
    public Map<String, Object> inquiryOrder(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestParam(defaultValue = "") String keywords,
                                            @RequestParam("beginDateScope") String[] beginDateScope) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        //获取当月
        if (beginDateScope.length == 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            String startTime = simpleDateFormat.format(calendar.getTime());
            String[] date = new String[2];
            date[0] = startTime;
            Date now = new Date();
            String endTime = simpleDateFormat.format(now);
            String[] end = endTime.split(" ");
            date[1] = end[0] + " 23:59:59";
            beginDateScope = date;
            List<Order> list = orderReviewService.getOrderByPage(page, size, keywords, beginDateScope);
            for (Order order : list) {
                order.setHospitalName(hospitalService.inquiryHospitalNameByID(order.getHospitalID()));
            }
            map.put("Orders", list);
            return map;
        }
        List<Order> list = orderReviewService.getOrderByPage(page, size, keywords, beginDateScope);
        for (Order order : list) {
            order.setHospitalName(hospitalService.inquiryHospitalNameByID(order.getHospitalID()));
        }
        map.put("Orders", list);
        Arrays.asList();
        return map;
    }

    @RequestMapping("/updateOrderState")
    @ResponseBody
    public RespBean updateOrderState(@RequestBody Order order) {
        if (orderReviewService.updateOrderState(order.getOrderNo())) {
            OrderOperate orderOperate = new OrderOperate();
            orderOperate.setOperating("已审核");
            orderOperate.setOrderno(order.getOrderNo());
            orderOperate.setOreratingdate(new Date());
            orderOperate.setOrderstate(order.getOrderState() + 1);
            orderOperatingService.insertOrderOperating(orderOperate);
            return RespBean.ok("审核成功");
        } else {
            return RespBean.error("出错了,审核失败");
        }
    }

    @RequestMapping("/updateOrderStateToLeaveStock")
    @ResponseBody
    public RespBean updateOrderStateToLeaveStock(@RequestParam("orderNo") String orderNo,
                                                 @RequestParam("operating") String operating,
                                                 @RequestParam("orderState") Integer orderState,
                                                 @RequestParam("oreratingdate") String operatingdate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderOperate orderOperate = new OrderOperate();
        orderOperate.setOperating(operating);
        orderOperate.setOrderno(orderNo);
        orderOperate.setOreratingdate(simpleDateFormat.parse(operatingdate));
        orderOperate.setOrderstate(orderState);
        if (orderOperatingService.insertOrderOperating(orderOperate) == 1) {
            return RespBean.ok("出库成功");
        } else {
            return RespBean.error("出错了,出库失败");
        }
    }

    @RabbitHandler
    public void getMessage(Order o) throws Exception {
        order = o;
        webSocket.sendAll(JSONObject.toJSONString("refresh"));
    }
}
