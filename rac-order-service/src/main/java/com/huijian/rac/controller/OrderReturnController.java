package com.huijian.rac.controller;

import com.huijian.rac.bean.*;
import com.huijian.rac.service.OrderReturnService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/OrderReturn")
public class OrderReturnController {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    OrderReturnService orderReturnService;
    /**
     * 查询本月中的所有订单记录
     * @param page
     * @param size
     * @param keywords
     * @param beginDateScope
     * @return
     * @throws ParseException
     */
    @RequestMapping("/inquiry")
    @ResponseBody
    public Map<String, Object> getReturnGoodsByPage(@RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer size,
                                                     String keywords,
                                                     String[] beginDateScope, String hospitalID) throws ParseException {

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
            List<ReturnGoods> list = orderReturnService.getReturnGoodsByPage(page, size, keywords, beginDateScope,hospitalID);
            map.put("ReturnGoodsList", list);
            return map;
        }
        List<ReturnGoods> list = orderReturnService.getReturnGoodsByPage(page, size, keywords, beginDateScope,hospitalID);
        map.put("ReturnGoodsList", list);
        return map;
    }

    @RequestMapping("/inquiryReturnGoodsDetail")
    @ResponseBody
    public Map<String,Object> inquiryDiscardDetail(@RequestBody ReturnGoods returnGoods){
        List<ReturnGoodsDetail> list = orderReturnService.inquiryByID(returnGoods.getReturnGoodsNo());
        Map<String, Object> map  = new HashMap<>();
        map.put("ReturnGoodsDetailList", list);
        return map;
    }

    @RequestMapping("/updateReturnGoodsState")
    @ResponseBody
    public RespBean updateReturnGoodsState(@RequestBody ReturnGoods returnGoods) {
        if (orderReturnService.updateReturnGoodsState(returnGoods)==1) {
            return RespBean.ok("审核成功");
        } else {
            return RespBean.error("出错了,审核失败");
        }
    }
}
