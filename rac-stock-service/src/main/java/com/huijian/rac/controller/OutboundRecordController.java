package com.huijian.rac.controller;

import com.huijian.rac.bean.*;
import com.huijian.rac.mapper.LeaveStockListDetailMapper;
import com.huijian.rac.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 出库
 */
@Controller
@RequestMapping("/OutboundRecord")
public class OutboundRecordController {
    @Autowired
    private OutboundRecordService outboundRecordService;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    OrderStateService orderStateService;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    LeaveStockListDetailMapper leaveStockListDetailMapper;
    /*@Autowired
    OrderReviewService orderReviewService;*/
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
    public Map<String, Object> getStockStorageByPage(@RequestParam(defaultValue = "1") Integer page,
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
            List<OutboundRecord> list = outboundRecordService.getOutboundRecordByPage(page, size, keywords, beginDateScope);
            map.put("OutboundRecords", list);
            return map;
        }
        List<OutboundRecord> list = outboundRecordService.getOutboundRecordByPage(page, size, keywords, beginDateScope);
        map.put("OutboundRecords", list);
        return map;
    }

    /**
     * 查询出库明细
     * @param outboundRecord
     * @return
     */
    @RequestMapping("/inquiryLeaveStockListDetail")
    @ResponseBody
    public Map<String,Object> inquiryLeaveStockListDetail(@RequestBody OutboundRecord outboundRecord){
        List<LeaveStockListDetail> list = outboundRecordService.inquiryLeaveStockListDetail(outboundRecord.getLeaveStockNo());
        Map<String, Object> map  = new HashMap<>();
        map.put("GoodsRecord", list);
        return map;
    }

    /**
     * 查询一年中的出库单数量
     * @return
     */
    @RequestMapping("/inquiryOneYear")
    @ResponseBody
    public Map<String,Object> inquiryOneYear(){
        String count = outboundRecordService.inquiryOneYear()+"";
        Map<String, Object> map  = new HashMap<>();
        map.put("count",count);
        return map;
    }

    /**
     * 添加
     */
    @RequestMapping("/warehouse")
    @ResponseBody
    public RespBean wareHouse(@RequestBody OutboundRecord outboundRecord) throws Exception {
        Date time = simpleDateFormat.parse(outboundRecord.getLeaveStock());
        outboundRecord.setLeaveStock(simpleDateFormat.format(time));
        if(outboundRecordService.wareHouse(outboundRecord)){
            return RespBean.ok("添加成功");
        }else{
            return RespBean.error("添加失败");
        }
    }

    /**
     * 更新记录
     * @param outboundRecord
     * @return
     * @throws ParseException
     */
    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(@RequestBody OutboundRecord outboundRecord) throws ParseException {
        if(outboundRecordService.update(outboundRecord)){
            return RespBean.ok("修改成功");
        }else {
            return RespBean.error("修改失败");
        }
    }


    @RequestMapping("/inquiryWarehouse")
    @ResponseBody
    public Map<String,Object> inquiryWarehouse(@RequestBody GoodName goodName){
        int count = outboundRecordService.inquiryWarehouse(goodName.getName());
        Map<String, Object> map  = new HashMap<>();
        map.put("warehouse",count);
        return map;
    }

    /**
     * 出库
     * @param outboundRecord
     * @return
     */
    @RequestMapping("/leaveStock")
    @ResponseBody
    public RespBean leaveStock(@RequestBody OutboundRecord outboundRecord){
        List<LeaveStockListDetail> list = outboundRecordService.inquiryLeaveStockListDetail(outboundRecord.getLeaveStockNo());
        Boolean ifSucceed = outboundRecordService.leaveStock(list);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderDate = simpleDateFormat.format(new Date());
        if(ifSucceed){
            //更新订单状态
            orderStateService.updateOrderState(outboundRecord.getOrderNo(), orderDate, 3);
            //更新出库信息
            outboundRecord.setLeaveStock(orderDate);
            outboundRecordService.update(outboundRecord);
            //往订单操作库中添加一条记录
            orderStateService.updateOrderStateToLeaveStock(outboundRecord.getOrderNo(),"已出库",3, orderDate);
            return RespBean.ok("出库成功");
        }else {
            return RespBean.error("出库失败,库存不足");
        }
    }

    /**
     * 撤销出库
     * @param outboundRecord
     * @return
     */
    @RequestMapping("/cancelLeaveStock")
    @ResponseBody
    public RespBean cancelLeaveStock(@RequestBody OutboundRecord outboundRecord){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderDate = simpleDateFormat.format(new Date());
        warehouseService.cancelLeaveStock(leaveStockListDetailMapper.inquiryLeaveStockListDetail(outboundRecord.getLeaveStockNo()));
        return orderStateService.updateOrderState(outboundRecord.getOrderNo(), orderDate, 2);
    }

    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public RespBean delete(@PathVariable String ids){
        if (outboundRecordService.deleteleaveStockListDetailByGoodsIDAndNo(ids)){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }
}
