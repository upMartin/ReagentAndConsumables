package com.huijian.rac.service;

import com.huijian.rac.bean.Order;
import com.huijian.rac.bean.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.IntStream;

/*@FeignClient(name = "rac-order-service")
public interface OrderReviewService {

    @RequestMapping("/ReviewOrder/updateOrderStateToLeaveStock")
    @ResponseBody
    public RespBean updateOrderStateToLeaveStock(@RequestParam("orderNo")String orderNo,
                                                 @RequestParam("operating")String operating,
                                                 @RequestParam("orderState") Integer orderState,
                                                 @RequestParam("oreratingdate")String oreratingdate);

}*/
