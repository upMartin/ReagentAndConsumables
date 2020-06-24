package com.huijian.rac.service;

import com.huijian.rac.bean.Role;
import com.huijian.rac.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public boolean deleteRoleByRid(Long rid) {
        if(roleMapper.deleteRoleByRid(rid)>0){
            return true;
        }else {
            return false;
        }
    }

    public Integer isExistUserAndRoleByRid(Long rid) {
        return roleMapper.isExistUserAndRoleByRid(rid);
    }

    public Integer isExistMenuAndRoleByRid(Long rid) {
        return roleMapper.isExistMenuAndRoleByRid(rid);
    }

    public boolean addRole(Role role){
        if(roleMapper.addRole(role)>0){
            return true;
        }else{
            return false;
        }
    }

    public int updateMenuRole(Long rid, Long[] mids) {
        roleMapper.deleteMenuByRid(rid);
        if (mids.length == 0) {
            return 0;
        }
        return roleMapper.addMenu(rid, mids);
    }
}
