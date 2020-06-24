package com.huijian.rac.controller;

import com.huijian.rac.bean.Menu;
import com.huijian.rac.bean.RespBean;
import com.huijian.rac.bean.Role;
import com.huijian.rac.service.DepartService;
import com.huijian.rac.service.MenuService;
import com.huijian.rac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sang on 2017/12/29.
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    /* @Autowired
     MenuRoleService menuRoleService;*/
    @Autowired
    DepartService departService;

    /**
     * 获取所有的角色
     * @return
     */
    @RequestMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * 查询所有的菜单, 并根据角色id查询该角色可访问的菜单
     * @param rid
     * @return
     */
    @RequestMapping("/menuTree/{rid}")
    public Map<String, Object> menuTree(@PathVariable Long rid) {
        Map<String, Object> map = new HashMap<>();
        List<Menu> menus = menuService.menuTree();
        map.put("menus", menus);
        List<Long> selMids = menuService.getMenusByRid(rid);
        map.put("mids", selMids);
        return map;
    }

    /**
     * 根据角色id删除角色
     * @param rid
     * @return
     */
    @RequestMapping("/deleteRole/{rid}")
    public RespBean deleteRole(@PathVariable Long rid) {
        //删除角色之前先得判断该角色是否与用户和菜单存在着关系,如果存在关系,则不能进行删除,必须
        //先删除了相应的关系之后才能删除角色
        Integer time = roleService.isExistUserAndRoleByRid(rid);
        Integer times = roleService.isExistMenuAndRoleByRid(rid);
        if (time>0||times>0) {
            return RespBean.error("存在"+time+"个用户角色关系和"+times+"个角色菜单关系,无法删除,请先清除这些对应关系");
        } else {
            if (roleService.deleteRoleByRid(rid)) {
                return RespBean.ok("删除角色成功");
            } else {
                return RespBean.error("删除角色失败");
            }
        }
    }

    /**
     * 添加角色
     * @param role
     * @param roleZh
     * @return
     */
    @RequestMapping("/addRole")
    public RespBean addRole(@RequestParam("role") String role,
                            @RequestParam("roleZh") String roleZh){
        Role r = new Role();
        r.setROLENAME(role);
        if(roleService.addRole(r)){
            return RespBean.ok("添加角色成功");
        }else{
            return RespBean.error("添加角色失败");
        }
    }

    @RequestMapping("/updateMenuRole")
    public RespBean updateMenuRole(Long rid, Long[] mids) {
        if (roleService.updateMenuRole(rid, mids) == mids.length) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

}
