package com.huijian.rac.mapper;

import com.huijian.rac.bean.LeaveStockListSecond;
import com.huijian.rac.bean.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaveStockListSecondMapper {
    List<LeaveStockListSecond> inquiryLastOneYear(@Param("hospitalID") String hospitalID,
                                                  @Param("goodsIDs") Integer[] goodsIDs);
}
