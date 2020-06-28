package com.huijian.rac.controller;

import com.huijian.rac.bean.*;
import com.huijian.rac.service.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.Package;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    LeaveStockListSecondService leaveStockListSecondService;
    @Autowired
    WarehouseSecondService warehouseSecondService;
    @Autowired
    GoodsPackageService goodsPackageService;
    @Autowired
    GoodsPackageDetailService packageDetailService;
    @Autowired
    AmqpTemplate rabbitTemplate;
    @Autowired
    OrderOperatingService orderOperatingService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


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
    public Map<String, Object> getStockStorageByPage(@RequestParam(defaultValue = "1") Integer page,
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
            List<Order> list = orderService.getOrderByPage(page, size, keywords, beginDateScope,hospitalID);
            map.put("Orders", list);
            return map;
        }
        List<Order> list = orderService.getOrderByPage(page, size, keywords, beginDateScope,hospitalID);
        map.put("Orders", list);
        return map;
    }

    /**
     * 添加订单
     * @param order
     * @return
     * @throws ParseException
     */
    @RequestMapping("/insert")
    @ResponseBody
    public RespBean insert(@RequestBody Order order) throws ParseException {
        if (orderService.insert(order) && orderDetailService.insert(order.getList())) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败");
        }
    }

    /**
     * 查询今年的订单总数, 用于自动生成订单号
     * @return
     */
    @RequestMapping("/inquiryOneYear")
    @ResponseBody
    public Map<String, Object> inquiryOneYear() {
        String count = orderService.inquiryOneYear() + "";
        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        return map;
    }

    /**
     * 上次订货数量
     * @param hospitalIDAndGood
     * @return
     */
    @RequestMapping("/inquiryLastOrderQuantity")
    @ResponseBody
    public Map<String, Object> inquiryLastOrderQuantity(@RequestBody HospitalIDAndGood hospitalIDAndGood) {
        Order order = orderService.inquiryLastOrder(hospitalIDAndGood.getHospitalID());
        Integer[] goodsIDs = new Integer[hospitalIDAndGood.getGood().size()];
        for (int i = 0; i < hospitalIDAndGood.getGood().size(); i++) {
            goodsIDs[i] = hospitalIDAndGood.getGood().get(i).getGoodsID();
        }
        List<OrderDetail> orderDetails = orderDetailService.inquriyByOrderNo(goodsIDs);
        /*int[] num = new int[orderDetails.size()];
        for (int i = 0; i < orderDetails.size(); i++) {
            num[i] = orderDetails.get(i).getAmount();
        }*/
        List<Good> list = hospitalIDAndGood.getGood().stream().map(e->{
            for (int i=0;i<orderDetails.size();i++){
                if(e.getGoodsID()==orderDetails.get(i).getGoodsID()){
                    e.setLastOrderQuantity(orderDetails.get(i).getAmount());
                    break;
                }else if(e.getGoodsID()!=orderDetails.get(i).getGoodsID()&&i==orderDetails.size()-1){
                    e.setLastOrderQuantity(0);
                }else{

                }
            }
            return e;
        }).collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("num", list);
        return map;
    }


    /**
     * 通过计算得到建议订货数量
     * @param hospitalIDAndGood
     * @return
     */
    @RequestMapping("/inquiryRecommendedOrderQuantity")
    @ResponseBody
    public Map<String, Object> inquiryRecommendedOrderQuantity(@RequestBody HospitalIDAndGood hospitalIDAndGood) {
        //查询过去12个月中的所有该商品出库记录
        Integer size = hospitalIDAndGood.getGood().size();
        Integer[] sum = new Integer[size];
        Integer[] goodsIDs = new Integer[size];
        Integer[] recommendedOrderQuantity = new Integer[size];
        for (int i = 0; i < hospitalIDAndGood.getGood().size(); i++) {
            goodsIDs[i] = hospitalIDAndGood.getGood().get(i).getGoodsID();
        }
        List<LeaveStockListSecond> leaveStockListSeconds = leaveStockListSecondService.inquiryLastOneYear(
                hospitalIDAndGood.getHospitalID(), goodsIDs);
        //该物品一年中一共消耗了多少
        for (int i = 0; i < size; i++) {
            sum[i] = 0;
            recommendedOrderQuantity[i] = 0;
            for (LeaveStockListSecond leaveStockListSecond : leaveStockListSeconds) {
                if (leaveStockListSecond != null) {
                    if (leaveStockListSecond.getGoodsID() == goodsIDs[i]) {
                        sum[i] += leaveStockListSecond.getQuantity();
                    }
                }else{
                }
            }
            //获取该物品有效期(天)/30得到月份, 月份乘上每个月消耗的量即为建议数量
            recommendedOrderQuantity[i] = (hospitalIDAndGood.getGood().get(i).getExpirationDate() / 30) * (sum[i] / 12);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("sum", recommendedOrderQuantity);
        return map;
    }

    /**
     * 得到当前库存
     */
    @RequestMapping("/inquiryWarehouse")
    @ResponseBody
    public Map<String, Object> inquiryWarehouse(@RequestBody HospitalIDAndGood hospitalIDAndGood){
        List<Good> list = hospitalIDAndGood.getGood();
        List<Integer> quantity = new LinkedList<>();
        for(int i=0;i<list.size();i++){
            quantity.add(warehouseSecondService.inquiryQuantity(list.get(i).getGoodsID(), hospitalIDAndGood.getHospitalID()));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("quantity", quantity);
        return map;
    }

    /**
     * 删除订单
     */
    @RequestMapping("/deleteOrder/{ids}")
    @ResponseBody
    public RespBean deleteOrder(@PathVariable String ids) {
        if (orderService.deleteOrderByOrderNo(ids)) {
            return RespBean.ok("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }

    /**
     * 查询订单明细
     */
    @RequestMapping("/inquiryOrderDetail")
    @ResponseBody
    public Map<String, Object> inquiryOrderDetail(@RequestBody Order order) {
        List<OrderDetail> list = orderDetailService.findByOrderNo(order.getOrderNo());
        for (int i=0; i<list.size(); i++){
            list.get(i).setCurrentInventory(warehouseSecondService.inquiryQuantity(list.get(i).getGoodsID(),order.getHospitalID()));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("OrderDetail", list);
        return map;
    }

    /**
     * 编辑订单
     */
    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(@RequestBody Order order) {
        if(orderService.update(order)){
            return RespBean.ok("更新成功");
        }else {
            return RespBean.error("更新失败");
        }

    }

    /**
     * 查询套餐
     */
    @RequestMapping("/inquiryPackage")
    @ResponseBody
    public Map<String, Object> inquiryGoodsPackage(){
        List<GoodsPackage> list = goodsPackageService.inquiryPackage();
        HashMap<String, Object> map = new HashMap<>();
        map.put("Package", list);
        return map;
    }

    /**
     * 查询套餐明细
     */
    @RequestMapping("/inquiryPackageDetail")
    @ResponseBody
    public Map<String, Object> inquiryGoodsPackageDetail(@RequestBody GoodsPackage pack){
        List<GoodsPackageDetail> list = packageDetailService.inquiryGoodsPackageDetail(pack.getPackageID());
        Map<String, Object> map = new HashMap<>();
        map.put("PackageDetail",list);
        return map;
    }

    /**
     * 提交订单
     */
    @RequestMapping("/uploadOrder")
    @ResponseBody
    public RespBean uploadOrder(@RequestBody Order order) throws ParseException {
        if(orderService.updateOrderState(order)){
           /* rabbitTemplate.convertAndSend("exchange","order",order);*/
            OrderOperate orderOperate = new OrderOperate();
            orderOperate.setOperating("已上传");
            orderOperate.setOrderno(order.getOrderNo());
            orderOperate.setOreratingdate(new Date());
            orderOperate.setOrderstate(order.getOrderState()+1);
            orderOperatingService.insertOrderOperating(orderOperate);
            return RespBean.ok("提交成功");
        }else{
            return RespBean.error("提交失败");
        }
    }

    /**
     * 更新订单状态
     */
    @RequestMapping("/updateOrderState")
    @ResponseBody
    public RespBean updateOrderState(@RequestParam(value = "orderNo")String orderNo,
                                     @RequestParam(value = "orderDate")String orderDate,
                                     @RequestParam(value = "orderState")Integer orderState){
        int i = orderService.updateOrderStateByOrderNo(orderNo,orderDate,orderState);
        if (i==1){
            return RespBean.ok("修改订单状态成功");
        }else {
            return RespBean.error("修改订单状态失败");
        }
    }

    /**
     * 查询订单状态
     */
    @RequestMapping("/inquiryOrderState")
    @ResponseBody
    public String inquiryOrderState(@RequestParam(value = "orderNo") String orderNo){
        Integer orderState = orderService.inquiryOrderState(orderNo);
        String state = orderState+"";
        return state;
    }
}
