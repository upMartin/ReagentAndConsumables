package com.huijian.rac.service;

import com.huijian.rac.bean.Depart;
import com.huijian.rac.bean.Role;
import com.huijian.rac.bean.User;
import com.huijian.rac.common.LoginInfo;
import com.huijian.rac.controller.SysUserController;
import com.huijian.rac.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不正确");
        }
        return user;
    }

    public List<User> getUserByPage(Integer page, Integer size, String keywords, String departID) {
        Integer start = (page - 1) * size;
        return userMapper.getUserByPage(start, size, keywords, departID);
    }

    public Long getCountByKeywords(String keywords) {
        return userMapper.getCountByKeywords(keywords);
    }

    public boolean addUser(User user) {
        if (userMapper.addUser(user) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Long selectUserID() {
        return userMapper.selectUserID();
    }

    public void insertIntoUserAndRole(Long userID, Long id) {
        userMapper.insertIntoUserAndRole(userID, id);
    }

    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public void updateUserAndRole(Long userID, Long roleID) {
        userMapper.updateUserAndRole(userID, roleID);
    }

    public boolean deleteById(String ids) {
        String[] split = ids.split(",");
        return userMapper.deleteById(split) == split.length;
    }

    public void deleteUserAndRoleById(String ids) {
        String[] split = ids.split(",");
        userMapper.deleteUserAndRoleById(split);
    }

    public List<Role> selectRoles() {
        return userMapper.selectRoles();
    }

    public List<Depart> selectDeparts(String departID) {
        return userMapper.selectDeparts(departID);
    }

    public String getDepartmentNameByID(String departID) {
        return userMapper.getDepartmentNameByID(departID);
    }

    public List<Depart> selectChildDeparts(String departID) {
        return userMapper.selectChildDeparts(departID);
    }
}
