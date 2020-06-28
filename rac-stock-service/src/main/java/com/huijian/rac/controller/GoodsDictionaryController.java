package com.huijian.rac.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huijian.rac.bean.*;
import com.huijian.rac.service.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/GoodsDictionary")
@RabbitListener(queues = "goodsDictionary")
public class GoodsDictionaryController {
    private static String message = "";
    @Autowired
    UnitService unitService;
    @Autowired
    GoodsDictionaryService goodsDictionaryService;
    @Autowired
    DeliveryUnitService deliveryUnitService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    AmqpTemplate rabbitTemplate;
    @Autowired
    GoodsSupplierService goodsSupplierService;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    WarehouseSecondService warehouseSecondService;
    @Autowired
    OrderStateService orderStateService;

    /**
     * 根据传过来的供应单位名称查询出供应单位ID,
     * 再查询出该供应单位所能提供的物品
     */
    @PostMapping("/inquiry")
    @ResponseBody
    /*@Cacheable(value="AllGoods+",keyGenerator="keyGenerator")*/
    public Map<String, Object> getAllGoods() {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        String msg = message;
        Map<String, Object> map = new HashMap<>();
        /*String key = good.getGoodsName();*/
        String key = "";
        if (key == "") {
            key = "AllGoods";
        }
        if (redisTemplate.hasKey(key) && message == "") {
            map.put("GoodsDictionary", redisTemplate.opsForValue().get(key));
            System.out.println("查询缓存");
            return map;
        } else {
            List<Good> list = goodsDictionaryService.getAllGoods();
            for (Good g : list) {
                String name = goodsSupplierService.inquirySupplierNameByID(g.getSupplierID());
                g.setCompany(name);
            }
            operations.set(key, list, 60 * 60, TimeUnit.SECONDS);
            map.put("GoodsDictionary", list);
            System.out.println("查询数据库");
            message = "";
            return map;
        }
    }

    @RabbitHandler
    public void getMessage(String msg) {
        message = msg;
    }

    /**
     * 获取当前数据库时间
     *
     * @return
     * @throws ParseException
     */
    @RequestMapping("/getWarehouseDate")
    @ResponseBody
    public Map<String, Object> getWarehouseDate() throws ParseException {
        Map<String, Object> map = new HashMap<>();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = simpleDateFormat.parse(simpleDateFormat.format(date));
        map.put("WarehouseDate", date);
        return map;
    }

    @RequestMapping("/update")
    @ResponseBody
    public RespBean updateGoodsDictionary(Good good) {
        //goodsDictionaryService.updateGoodsDictionary(good)
        int i = 1;
        if (i <= 1) {
            i += 1;
            rabbitTemplate.convertAndSend("exchange", "goodsDictionary", "物资字典表发生了改变!");
            return RespBean.ok("修改成功");
        } else {
            return RespBean.error("修改失败");
        }

    }

    /**
     * 一级库查询物资字典表
     * 一级库查询库存
     *
     * @param goodsName
     * @return
     */
    @PostMapping("/inquiryByWord")
    @ResponseBody
    public Map<String, Object> inquiryByWord(String goodsName) {
        Map<String, Object> map = new HashMap<>();
        List<Good> list = goodsDictionaryService.inquiryByWord(goodsName);
        list = list.stream().map(e -> {
            String name = goodsSupplierService.inquirySupplierNameByID(e.getSupplierID());
            int quantity = 0;
            e.setCompany(name);
            try {
                quantity = warehouseService.inquiryQuantityByGoodsID(e.getGoodsID());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.setWarehouse(quantity);
            return e;
        }).collect(Collectors.toList());
        map.put("GoodsDictionary", list);
        return map;
    }

    @PostMapping("/inquiryByIDAndName")
    @ResponseBody
    public Map<String, Object> inquiryByIDAndName(Integer goodsID, String goodsName) {
        Map<String, Object> map = new HashMap<>();
        List<Good> list = goodsDictionaryService.inquiryByIDAndName(goodsID, goodsName);
        list = list.stream().map(e -> {
            String name = goodsSupplierService.inquirySupplierNameByID(e.getSupplierID());
            int quantity = 0;
            e.setCompany(name);
            try {
                quantity = warehouseService.inquiryQuantityByGoodsID(e.getGoodsID());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.setWarehouse(quantity);
            return e;
        }).collect(Collectors.toList());
        map.put("GoodsDictionary", list);
        return map;
    }

    /**
     * 二级库查询物资字典表
     * 二级库查询库存
     *
     * @param goodsName
     * @return
     */
    @PostMapping("/inquirySecondByWord")
    @ResponseBody
    public Map<String, Object> inquirySecondByWord(String goodsName, String hospitalID) {
        Map<String, Object> map = new HashMap<>();
        List<Good> list = goodsDictionaryService.inquiryByWord(goodsName);
        list = list.stream().map(e -> {
            String name = goodsSupplierService.inquirySupplierNameByID(e.getSupplierID());
            int quantity = 0;
            e.setCompany(name);
            try {
                quantity = warehouseSecondService.inquiryQuantityByGoodsID(e.getGoodsID(), hospitalID);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.setWarehouse(quantity);
            return e;
        }).collect(Collectors.toList());
        map.put("GoodsDictionary", list);
        return map;
    }

    /**
     * 二级库查询字典表, 不打开字典表页面,
     * 直接根据名称搜索并填充文本框
     * 动态查询建议订货数量, 上次订货数量
     * @param goodsName
     * @param hospitalID
     * @return
     */
    @PostMapping("/inquiryDetail")
    @ResponseBody
    public Map<String, Object> inquiryDetail(String goodsName, String hospitalID) {
        Map<String, Object> map = new HashMap<>();
        List<Good> list = goodsDictionaryService.inquiryByWord(goodsName);
        list = list.stream().map(e -> {
            String name = goodsSupplierService.inquirySupplierNameByID(e.getSupplierID());
            int quantity = 0;
            e.setCompany(name);
            try {
                quantity = warehouseSecondService.inquiryQuantityByGoodsID(e.getGoodsID(), hospitalID);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.setWarehouse(quantity);
            return e;
        }).collect(Collectors.toList());
        HospitalIDAndGood hospitalIDAndGood = new HospitalIDAndGood(hospitalID,list);
        List<Good> list1 = (List<Good>) ((orderStateService.inquiryLastOrderQuantity(hospitalIDAndGood)).get("num"));
        List<Integer> recommendedOrderQuantity = (List<Integer>) ((orderStateService.inquiryRecommendedOrderQuantity(hospitalIDAndGood)).get("sum"));
        ObjectMapper mapper = new ObjectMapper();
        for (int i=0;i<list.size();i++){
            Good good = mapper.convertValue(list1.get(i),Good.class);
            list.get(i).setLastOrderQuantity(good.getLastOrderQuantity());
            /*(list.get(i)).setLastOrderQuantity(good.getLastOrderQuantity());*/
            (list.get(i)).setRecommendedOrderQuantity(recommendedOrderQuantity.get(i));
        }
        map.put("GoodsDictionary", list);
        return map;
    }
}
