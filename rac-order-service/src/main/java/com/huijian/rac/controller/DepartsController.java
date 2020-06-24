package com.huijian.rac.controller;

import com.huijian.rac.bean.Depart;
import com.huijian.rac.service.DepartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Departs")
public class DepartsController {
    @Autowired
    DepartsService departsService;

    @RequestMapping("/quiryDeparts")
    @ResponseBody
    public Map<String, Object> quiryDeparts(String hospitalID){
        List<Depart> departs = departsService.quiryDepartsByHospitalID(hospitalID);
        Map<String,Object> map = new HashMap<>();
        map.put("departs",departs);
        return map;
    }

}
