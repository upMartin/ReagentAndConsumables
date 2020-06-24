package com.huijian.rac.controller;

import com.huijian.rac.bean.DeliveryUnit;
import com.huijian.rac.service.DeliveryUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/DeliveryUnit/")
public class DeliveryUnitController {
    @Autowired
    DeliveryUnitService deliveryUnitService;

    @RequestMapping("/inquiry")
    @ResponseBody
    public Map<String,Object> getAllDeliveryUnit(){
        List<DeliveryUnit> list = deliveryUnitService.getAllDeliveryUnit();
        Map<String, Object> map = new HashMap<>();
        map.put("DeliveryUnits", list);
        return map;
    }
}
