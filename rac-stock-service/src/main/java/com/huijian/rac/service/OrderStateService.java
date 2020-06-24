package com.huijian.rac.service;

import com.huijian.rac.bean.HospitalIDAndGood;
import com.huijian.rac.bean.Order;
import com.huijian.rac.bean.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "rac-order-service")
public interface OrderStateService {

    @RequestMapping("/Order/updateOrderState")
    @ResponseBody
    RespBean updateOrderState(@RequestParam(value = "orderNo")String orderNo,
                                     @RequestParam(value = "orderDate")String orderDate,
                                     @RequestParam(value = "orderState")Integer orderState);

    @RequestMapping("/ReviewOrder/updateOrderStateToLeaveStock")
    @ResponseBody
    RespBean updateOrderStateToLeaveStock(@RequestParam("orderNo")String orderNo,
                                                 @RequestParam("operating")String operating,
                                                 @RequestParam("orderState") Integer orderState,
                                                 @RequestParam("oreratingdate")String oreratingdate);

    @RequestMapping("/Order/inquiryOrderState")
    @ResponseBody
    String inquiryOrderState(@RequestParam(value = "orderNo")String orderNo);

    @RequestMapping
    @ResponseBody
    Map<String, Object> inquiryOrderDetail(@RequestBody Order order);

    @RequestMapping("/Order/inquiryLastOrderQuantity")
    @ResponseBody
    Map<String, Object> inquiryLastOrderQuantity(@RequestBody HospitalIDAndGood hospitalIDAndGood);

    @RequestMapping("/Order/inquiryRecommendedOrderQuantity")
    @ResponseBody
    Map<String, Object> inquiryRecommendedOrderQuantity(@RequestBody HospitalIDAndGood hospitalIDAndGood);
}
