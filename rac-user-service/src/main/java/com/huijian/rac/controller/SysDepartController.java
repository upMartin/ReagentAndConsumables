package com.huijian.rac.controller;

import com.huijian.rac.bean.Depart;
import com.huijian.rac.bean.RespBean;
import com.huijian.rac.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/sysDepart")
@Controller
public class SysDepartController {
    @Autowired
    DepartService departService;

    @RequestMapping("/inquiry")
    @ResponseBody
    public Map<String,Object> getDeparts(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size,
                                         @RequestParam(defaultValue = "") String keywords,
                                         @RequestParam(value = "departID") String departID){
        Map<String, Object> map = new HashMap<>();
        List<Depart> list = departService.getDeparts(page,size,keywords,departID);
        map.put("departs", list);
        return map;
    }

    @RequestMapping("/addDepart")
    @ResponseBody
    public RespBean addDepart(Depart depart){
        if(departService.addDepart(depart)){
            return RespBean.ok("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }

    @RequestMapping("/updateDepart")
    @ResponseBody
    public RespBean updateDepart(Depart depart){
        if(departService.updateDepart(depart)){
            return RespBean.ok("修改成功");
        }else {
            return RespBean.error("修改失败");
        }
    }

    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public RespBean delete(@PathVariable String ids){
        if(departService.delete(ids)){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }
}
