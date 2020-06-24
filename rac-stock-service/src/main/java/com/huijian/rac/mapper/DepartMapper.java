package com.huijian.rac.mapper;

import org.apache.ibatis.annotations.Param;

public interface DepartMapper {
    String inquiryDepartNameByCode(@Param("departCode") String departCode, @Param("hospitalID") String hospitalID);
}
