package com.huijian.rac.controller;

import com.huijian.rac.bean.*;
import com.huijian.rac.service.ReturnGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/ReturnGoods")
public class ReturnGoodsController {
    @Autowired
    ReturnGoodsService returnGoodsService;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @RequestMapping("/inquiryOneDay")
    public Map<String,Object> inquiryOneDay(String hospitalID){
        String count = returnGoodsService.inquiryOneDay(hospitalID)+"";
        Map<String, Object> map  = new HashMap<>();
        map.put("count",count);
        return map;
    }

    @RequestMapping("/add")
    public RespBean addDiscard(@RequestBody ReturnGoods returnGoods) throws Exception {
        String time = simpleDateFormat.format(returnGoods.getReturnGoodsDate());
        returnGoods.setReturnGoodsDate(simpleDateFormat.parse(time));
        if(returnGoodsService.addReturnGoods(returnGoods)&&returnGoodsService.addReturnGoodsDetail(returnGoods)){
            return RespBean.ok("添加成功");
        }else{
            return RespBean.error("添加失败");
        }
    }

    @RequestMapping("/inquiry")
    public Map<String, Object> getReturnGoodsByPage(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size,
                                                 @RequestParam(defaultValue = "") String keywords,
                                                 @RequestParam("beginDateScope")String[] beginDateScope,
                                                    String hospitalID) throws ParseException {

        Map<String, Object> map  = new HashMap<>();
        if (beginDateScope.length==0){
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
            date[1] = end[0]+" 23:59:59";
            beginDateScope = date;
            List<ReturnGoods> list = returnGoodsService.getReturnGoodsByPage(page, size, keywords, beginDateScope,hospitalID);
            map.put("ReturnGoodsList", list);
            return map;
        }
        List<ReturnGoods> list = returnGoodsService.getReturnGoodsByPage(page, size, keywords, beginDateScope,hospitalID);
        map.put("ReturnGoodsList", list);
        return map;
    }

    @RequestMapping("/inquiryReturnGoodsDetail")
    public Map<String,Object> inquiryDiscardDetail(@RequestBody ReturnGoods returnGoods){
        List<ReturnGoodsDetail> list = returnGoodsService.inquiryByID(returnGoods.getReturnGoodsNo());
        Map<String, Object> map  = new HashMap<>();
        map.put("ReturnGoodsDetailList", list);
        return map;
    }
}
