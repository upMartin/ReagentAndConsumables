package com.huijian.rac.controller;

import com.huijian.rac.bean.*;
import com.huijian.rac.service.DiscardDetailService;
import com.huijian.rac.service.DiscardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Discard")
public class DiscardController {
    @Autowired
    DiscardService discardService;
    @Autowired
    DiscardDetailService discardDetailService;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/inquiryOneDay")
    public Map<String,Object> inquiryOneDay(String hospitalID){
        String count = discardService.inquiryOneDay(hospitalID)+"";
        Map<String, Object> map  = new HashMap<>();
        map.put("count",count);
        return map;
    }

    @RequestMapping("/inquiry")
    public Map<String, Object> getDiscardsByPage(@RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer size,
                                                     @RequestParam(defaultValue = "") String keywords,
                                                     @RequestParam("beginDateScope")String[] beginDateScope) throws ParseException {

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
            List<Discard> list = discardService.getDiscardsByPage(page, size, keywords, beginDateScope);
            map.put("Discards", list);
            return map;
        }
        List<Discard> list = discardService.getDiscardsByPage(page, size, keywords, beginDateScope);
        map.put("Discards", list);
        return map;
    }

    @RequestMapping("/addDiscard")
    public RespBean addDiscard(@RequestBody Discard discard) throws Exception {
        String time = simpleDateFormat.format(discard.getDiscardDate());
        discard.setDiscardDate(simpleDateFormat.parse(time));
        if(discardService.addDiscard(discard)&&discardService.addDiscardDetail(discard)){
            return RespBean.ok("添加成功");
        }else{
            return RespBean.error("添加失败");
        }
    }

    @RequestMapping("/inquiryDiscardDetail")
    public Map<String,Object> inquiryDiscardDetail(@RequestBody Discard discard){
        List<DiscardDetail> list = discardDetailService.inquiryByID(discard.getDiscardNo());
        Map<String, Object> map  = new HashMap<>();
        map.put("DiscardDetail", list);
        return map;
    }

}
