package com.huijian.rac.mapper;

import com.huijian.rac.bean.Depart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartMapper {
    List<Depart> getDepartsByPageSize(Integer start, Integer size, String keywords, String departID);

    int addDepart(Depart depart);

    int updateDepart(Depart depart);

    int delete(@Param("ids") String[] ids);
}
