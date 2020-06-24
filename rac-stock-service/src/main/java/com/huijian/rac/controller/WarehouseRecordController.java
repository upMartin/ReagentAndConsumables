package com.huijian.rac.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.huijian.rac.bean.GoodsRecord;
import com.huijian.rac.bean.RespBean;
import com.huijian.rac.bean.WarehouseRecord;
import com.huijian.rac.service.WarehouseRecordService;
import com.huijian.rac.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 入库
 */
@Controller
@RequestMapping("/WarehouseRecord")
public class WarehouseRecordController {
    @Autowired
    WarehouseRecordService warehouseRecordService;
    @Autowired
    WarehouseService warehouseService;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //查询所有入库记录
    @RequestMapping("/inquiry")
    @ResponseBody
    public Map<String, Object> getStockStorageByPage(@RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer size,
                                                     @RequestParam(defaultValue = "") String keywords,
                                                     @RequestParam("beginDateScope")String[] beginDateScope) throws ParseException {

        Map<String, Object> map  = new HashMap<>();
        int count = 0;
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
            List<WarehouseRecord> list = warehouseRecordService.getWarehouseRecordByPage(page, size, keywords, beginDateScope);
            map.put("WarehouseRecords", list);
            count = warehouseRecordService.inquiryCount(keywords, beginDateScope);
            map.put("count", count);
            return map;
        }
        List<WarehouseRecord> list = warehouseRecordService.getWarehouseRecordByPage(page, size, keywords, beginDateScope);
        map.put("WarehouseRecords", list);
        count = warehouseRecordService.inquiryCount(keywords, beginDateScope);
        map.put("count", count);
        return map;
    }

    //更新记录
    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(@RequestBody WarehouseRecord warehouseRecord) throws ParseException {
        if(warehouseRecordService.update(warehouseRecord)){
            return RespBean.ok("修改成功");
        }else {
            return RespBean.error("修改失败");
        }
    }

    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public RespBean delete(@PathVariable String ids){
        if (warehouseRecordService.deleteGoodsRecordByGid(ids)){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }

    @RequestMapping("/deleteWarehouseRecord/{ids}")
    @ResponseBody
    public RespBean deleteWarehouseRecord(@PathVariable String ids){
        if (warehouseRecordService.deleteWarehouseRecordById(ids)){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }

    @RequestMapping("/warehouse")
    @ResponseBody
    public RespBean wareHouse(@RequestBody WarehouseRecord warehouseRecord) throws Exception {
        Date time = simpleDateFormat.parse(warehouseRecord.getWarehouseDate());
        warehouseRecord.setWarehouseDate(simpleDateFormat.format(time));
        if(warehouseRecordService.wareHouse(warehouseRecord)){
            return RespBean.ok("添加成功");
        }else{
            return RespBean.error("添加失败");
        }
    }

    @RequestMapping("/inquiryGoodsRecord")
    @ResponseBody
    public Map<String,Object> inquiryGoodsRecordByID(@RequestBody WarehouseRecord warehouseRecord){
        List<GoodsRecord> list = warehouseRecordService.inquiryGoodsRecordByID(warehouseRecord.getID());
        list = list.stream().map(e -> {
            int quantity = 0;
            try {
                quantity = warehouseService.inquiryQuantityByGoodsID(e.getGoodsID());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.setWarehouse(quantity);
            return e;
        }).collect(Collectors.toList());
        Map<String, Object> map  = new HashMap<>();
        map.put("GoodsRecord", list);
        return map;
    }

    @RequestMapping("/inquiryOneYear")
    @ResponseBody
    public Map<String,Object> inquiryOneYear(){
        String count = warehouseRecordService.inquiryOneYear()+"";
        Map<String, Object> map  = new HashMap<>();
        map.put("count",count);
        return map;
    }
}
