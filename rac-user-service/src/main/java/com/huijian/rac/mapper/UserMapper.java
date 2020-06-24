package com.huijian.rac.mapper;

import com.huijian.rac.bean.Depart;
import com.huijian.rac.bean.Role;
import com.huijian.rac.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User findByUsername(String username);

    List<Role> getRolesByUserId(String id);

    List<User> getUserByPage(Integer start, Integer size, String keywords, String departID);

    Long getCountByKeywords(String keywords);

    int addUser(User user);

    Long selectUserID();

    void insertIntoUserAndRole(Long userID, Long id);

    boolean updateUser(User user);

    void updateUserAndRole(Long userID, Long roleID);

    int deleteById(@Param("ids") String[] ids);

    int deleteUserAndRoleById(@Param("ids") String[] ids);

    List<Role> selectRoles();

    List<Depart> selectDeparts(String departID);

    String getDepartmentNameByID(String departID);

    List<Depart> selectChildDeparts(String departID);
}
