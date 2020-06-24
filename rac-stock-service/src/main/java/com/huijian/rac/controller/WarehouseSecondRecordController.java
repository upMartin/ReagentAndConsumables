package com.huijian.rac.controller;

import com.huijian.rac.bean.*;
import com.huijian.rac.service.WarehouseSecondRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/WarehouseSecondRecord")
public class WarehouseSecondRecordController {
    @Autowired
    WarehouseSecondRecordService warehouseSecondRecordService;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/inquiryOneYear")
    @ResponseBody
    public Map<String,Object> inquiryOneYear(String hospitalID){
        String count = warehouseSecondRecordService.inquiryOneYear(hospitalID)+"";
        Map<String, Object> map  = new HashMap<>();
        map.put("count",count);
        return map;
    }

    /**
     * 入库
     * @param warehouseSecondRecord
     * @return
     * @throws Exception
     */
    @RequestMapping("/warehouse")
    @ResponseBody
    public RespBean wareHouse(@RequestBody WarehouseSecondRecord warehouseSecondRecord) throws Exception {
        Date time = simpleDateFormat.parse(warehouseSecondRecord.getWarehouseDate());
        warehouseSecondRecord.setWarehouseDate(simpleDateFormat.format(time));
        if(warehouseSecondRecordService.wareHouse(warehouseSecondRecord)){
            return RespBean.ok("添加成功");
        }else{
            return RespBean.error("添加失败");
        }
    }

    //查询所有入库记录
    @RequestMapping("/inquiry")
    @ResponseBody
    public Map<String, Object> getStockStorageByPage(@RequestParam(defaultValue = "1") Integer page,
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
            List<WarehouseSecondRecord> list = warehouseSecondRecordService.getWarehouseRecordByPage(page, size, keywords, beginDateScope, hospitalID);
            map.put("WarehouseSecondRecords", list);
            return map;
        }
        List<WarehouseSecondRecord> list = warehouseSecondRecordService.getWarehouseRecordByPage(page, size, keywords, beginDateScope, hospitalID);
        map.put("WarehouseSecondRecords", list);
        return map;
    }


    @RequestMapping("/deleteWarehouseSecondRecord/{ids}")
    @ResponseBody
    public RespBean deleteWarehouseSecondRecord(@PathVariable String ids){
        if (warehouseSecondRecordService.deleteWarehouseSecondRecordById(ids)){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }

    @RequestMapping("/inquirySecondGoodsRecord")
    @ResponseBody
    public Map<String,Object> inquiryGoodsRecordByID(@RequestBody WarehouseSecondRecord warehouseSecondRecord){
        List<SecondGoodsRecord> list = warehouseSecondRecordService.inquirySecondGoodsRecordByID(warehouseSecondRecord.getID(), warehouseSecondRecord.getHospitalID());
        Map<String, Object> map  = new HashMap<>();
        map.put("SecondGoodsRecord", list);
        return map;
    }

    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public RespBean delete(@PathVariable String ids){
        if (warehouseSecondRecordService.deleteSecondGoodsRecordByGid(ids)){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }

    //更新记录
    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(@RequestBody WarehouseSecondRecord warehouseSecondRecord) throws ParseException {
        if(warehouseSecondRecordService.update(warehouseSecondRecord)){
            return RespBean.ok("修改成功");
        }else {
            return RespBean.error("修改失败");
        }
    }
}
