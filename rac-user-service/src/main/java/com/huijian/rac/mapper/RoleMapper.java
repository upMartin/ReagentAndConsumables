package com.huijian.rac.mapper;

import com.huijian.rac.bean.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface RoleMapper {

    List<Role> getAllRoles();

    int deleteRoleByRid(Long rid);

    Integer isExistUserAndRoleByRid(Long rid);

    Integer isExistMenuAndRoleByRid(Long rid);

    int addRole(Role role);

    void deleteMenuByRid(Long rid);

    int addMenu(@Param("rid") Long rid, @Param("mids") Long[] mids);
}
