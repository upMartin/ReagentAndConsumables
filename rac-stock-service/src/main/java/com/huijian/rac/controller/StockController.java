package com.huijian.rac.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huijian.rac.bean.Material;
import com.huijian.rac.bean.MaterialList;
import com.huijian.rac.bean.Reagent;
import com.huijian.rac.bean.RespBean;
import com.huijian.rac.service.MaterialService;
import com.huijian.rac.service.ReagentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stock")
public class StockController {
    @Autowired
    ReagentService reagentService;
    @Autowired
    MaterialService materialService;

    @RequestMapping("/inquiry")
    @ResponseBody
    public Map<String, Object> getStockByPage(@RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "10") Integer size,
                                              @RequestParam(defaultValue = "") String keywords,
                                              Long politicId, Long nationId, Long posId,
                                              Long jobLevelId, String engageForm,
                                              Long departmentId, String beginDateScope){
        Map<String, Object> map  = new HashMap<>();
        List<Reagent> list = reagentService.getReagentByPage(page, size, keywords);

        map.put("emps", list);
        return map;
    }

    @RequestMapping("/getUser")
    @Cacheable(value="reagent",keyGenerator="keyGenerator")
    @ResponseBody
    public Reagent getUser() {
        Reagent reagent = new Reagent(1,"鲍镕");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return reagent;
    }

    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public RespBean delete(@PathVariable String ids){
        if (reagentService.deleteById(ids)){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }

    }

    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(){
        return null;
    }

    @RequestMapping("/warehouse")
    @ResponseBody
    public RespBean wareHouse(@RequestBody JSONArray array) throws Exception {
        List<Material> list = JSONObject.parseArray(array.toJSONString(), Material.class);
        Material material = list.get(0);
        if(materialService.wareHouse(list)){
            return RespBean.ok("添加成功");
        }else{
            return RespBean.error("添加失败");
        }
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello ，this is first messge";
    }
}
