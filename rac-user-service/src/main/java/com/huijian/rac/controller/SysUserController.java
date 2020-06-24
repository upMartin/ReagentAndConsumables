package com.huijian.rac.controller;

import com.huijian.rac.bean.Depart;
import com.huijian.rac.bean.RespBean;
import com.huijian.rac.bean.Role;
import com.huijian.rac.bean.User;
import com.huijian.rac.common.UserUtils;

import com.huijian.rac.feign.FeignTest;
import com.huijian.rac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    UserService userService;
    @Autowired
    FeignTest feignTest;
    /**
     * 分页查询所有用户
     * @param page
     * @param size
     * @param keywords
     * @return
     */
    @RequestMapping("/inquiry")
    @ResponseBody
    public Map<String, Object> getUserByPage(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size,
                                             @RequestParam(defaultValue = "") String keywords,
                                             @RequestParam(value = "departID") String departID) {
        Map<String, Object> map = new HashMap<>();
        List<User> list = userService.getUserByPage(page, size, keywords,departID);
        Long count = userService.getCountByKeywords(keywords);
        map.put("users", list);
        map.put("count", count);
        return map;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    /*@RequestMapping("/addUser")
    @ResponseBody
    public RespBean addUser(User user) {
        setUsersDepartmentName(user);
        if (userService.addUser(user)) {
            String id = user.getRoleID();
            Long roleID = Long.parseLong(id);
            Long userID = userService.selectUserID();
            userService.insertIntoUserAndRole(userID, roleID);
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败");
        }
    }*/

    /*public void setUsersDepartmentName(User user) {
        *//*String password = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        user.setPassword(encode);*//*
        if (user.getDepartCode()!=null){
            String name = userService.getDepartmentNameByID(user.getDepartCode());
            user.setD(name);
        }
    }*/

    /**
     * 修改用户信息
     * @param user
     * @return
     *//*
    @RequestMapping("/updateUser")
    @ResponseBody
    public RespBean updateUser(User user) {
        setUsersDepartmentName(user);
        if(userService.updateUser(user)){
            String id = user.getRoleID();
            Long roleID = Long.parseLong(id);
            Long userID = user.getId();
            userService.updateUserAndRole(userID, roleID);
            return RespBean.ok("修改成功");
        }else{
            return RespBean.ok("修改失败");
        }
    }*/

    /**
     * 删除用户信息
     * @param ids
     * @return
     */
    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public RespBean deleteUser(@PathVariable String ids){
        if (userService.deleteById(ids)){
            userService.deleteUserAndRoleById(ids);
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }

   /* *//**
     * 查询所有的角色
     * @return
     *//*
    @RequestMapping("/selectRoles")
    @ResponseBody
    public Map<String, Object> selectRoles(){
        List<Role> list = userService.selectRoles();
        List<Depart> departs = userService.selectDeparts(UserUtils.getCurrentUser().getDepartID());
        List<Depart> childDeparts = userService.selectChildDeparts(UserUtils.getCurrentUser().getDepartID());
        for (Depart d : departs){
            childDeparts.add(d);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("roles",list);
        map.put("departments",childDeparts);
        return map;
    }*/

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return feignTest.hello();
    }
}
