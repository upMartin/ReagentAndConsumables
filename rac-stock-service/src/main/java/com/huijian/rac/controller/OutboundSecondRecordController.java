package com.huijian.rac.controller;

import com.huijian.rac.bean.*;
import com.huijian.rac.service.DepartService;
import com.huijian.rac.service.OutboundSecondRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/OutboundSecondRecord")
public class OutboundSecondRecordController {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    OutboundSecondRecordService outboundSecondRecordService;
    @Autowired
    DepartService departService;

    /**
     * 查询所有出库记录
     * @param page
     * @param size
     * @param keywords
     * @param beginDateScope
     * @return
     * @throws ParseException
     */
    @RequestMapping("/inquiry")
    @ResponseBody
    public Map<String, Object> getOutboundSecondRecordsByPage(@RequestParam(defaultValue = "1") Integer page,
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
            List<OutboundSecondRecord> list = outboundSecondRecordService.getOutboundSecondRecordByPage(page, size, keywords, beginDateScope,hospitalID);
            list = list.stream().map(e-> {
                String name = departService.inquiryDepartNameByCode(e.getDepartCode(),e.getHospitalID());
                e.setDepartName(name);
                return e;
            }).collect(Collectors.toList());
            map.put("OutboundSecondRecords", list);
            return map;
        }
        List<OutboundSecondRecord> list = outboundSecondRecordService.getOutboundSecondRecordByPage(page, size, keywords, beginDateScope,hospitalID);
        list = list.stream().map(e-> {
            String name = departService.inquiryDepartNameByCode(e.getDepartCode(),e.getHospitalID());
            e.setDepartName(name);
            return e;
        }).collect(Collectors.toList());
        map.put("OutboundSecondRecords", list);
        return map;
    }

    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public RespBean delete(@PathVariable String ids){
        if (outboundSecondRecordService.deleteSecondleaveStockListDetail(ids)){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }

    @RequestMapping("/deleteOutboundSecondRecord/{ids}")
    @ResponseBody
    public RespBean deleteOutboundSecondRecord(@PathVariable String ids){
        if (outboundSecondRecordService.deleteOutboundSecondRecord(ids)){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }

    @RequestMapping("/inquirySecondLeaveStockListDetail")
    @ResponseBody
    public Map<String,Object> inquirySecondLeaveStockListDetail(@RequestBody OutboundSecondRecord outboundSecondRecord){
        List<SecondLeaveStockListDetail> list = outboundSecondRecordService.inquirySecondLeaveStockListDetail(outboundSecondRecord);
        Map<String, Object> map  = new HashMap<>();
        map.put("GoodsRecord", list);
        return map;
    }

    @RequestMapping("/inquiryOneYear")
    @ResponseBody
    public Map<String,Object> inquiryOneYear(String hospitalID){
        String count = outboundSecondRecordService.inquiryOneYear(hospitalID)+"";
        Map<String, Object> map  = new HashMap<>();
        map.put("count",count);
        return map;
    }

    /**
     * 添加
     */
    @RequestMapping("/warehouse")
    @ResponseBody
    public RespBean wareHouse(@RequestBody OutboundSecondRecord outboundSecondRecord) throws Exception {
        Date time = simpleDateFormat.parse(outboundSecondRecord.getLeaveStockDate());
        outboundSecondRecord.setLeaveStockDate(simpleDateFormat.format(time));
        if(outboundSecondRecordService.wareHouse(outboundSecondRecord)){
            return RespBean.ok("添加成功");
        }else{
            return RespBean.error("添加失败");
        }
    }

    @RequestMapping("/inquiryWarehouse")
    @ResponseBody
    public Map<String,Object> inquiryWarehouse(int goodsID, String hospitalID){
        int count = outboundSecondRecordService.inquiryWarehouse(goodsID, hospitalID);
        Map<String, Object> map  = new HashMap<>();
        map.put("warehouse",count);
        return map;
    }

    /**
     * 更新记录
     * @param outboundSecondRecord
     * @return
     * @throws ParseException
     */
    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(@RequestBody OutboundSecondRecord outboundSecondRecord) throws ParseException {
        if(outboundSecondRecordService.update(outboundSecondRecord)){
            return RespBean.ok("修改成功");
        }else {
            return RespBean.error("修改失败");
        }
    }

    /**
     * 出库
     */
    @RequestMapping("/leaveStock")
    @ResponseBody
    public RespBean leaveStock(@RequestBody OutboundSecondRecord outboundSecondRecord){
        List<SecondLeaveStockListDetail> list = outboundSecondRecordService.inquirySecondLeaveStockListDetail(outboundSecondRecord);
        Boolean ifSucceed = outboundSecondRecordService.leaveStock(list);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderDate = simpleDateFormat.format(new Date());
        if(ifSucceed){
            //更新出库信息
            outboundSecondRecord.setLeaveStockDate(orderDate);
            boolean ifUpdate = outboundSecondRecordService.update(outboundSecondRecord);
            String leaveStockState = "1";
            int updateSum = outboundSecondRecordService.updateLeaveStockState(leaveStockState,outboundSecondRecord.getHospitalID(), outboundSecondRecord.getLeaveStockNo());
            if(ifUpdate && updateSum==1){
                return RespBean.ok("出库成功");
            }else{
                return RespBean.error("出库失败,库存不足");
            }
        }else {
            return RespBean.error("出库失败,库存不足");
        }
    }


}
