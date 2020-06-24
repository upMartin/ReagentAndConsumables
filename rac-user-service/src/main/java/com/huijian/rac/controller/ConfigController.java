package com.huijian.rac.controller;

import com.huijian.rac.bean.Menu;
import com.huijian.rac.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/sysmenu")
    public List<Menu> sysMenu(){
        return menuService.getMenusByUserId();
    }
}
