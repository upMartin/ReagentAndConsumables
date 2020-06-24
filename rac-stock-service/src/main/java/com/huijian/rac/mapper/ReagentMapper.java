package com.huijian.rac.mapper;


import com.huijian.rac.bean.Reagent;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReagentMapper {
    List<Reagent> getReagentByPage(@Param("start") Integer start,
                                   @Param("size") Integer size,
                                   @Param("keywords") String keywords);

    int deleteById(@Param("ids") String[] ids);
}
