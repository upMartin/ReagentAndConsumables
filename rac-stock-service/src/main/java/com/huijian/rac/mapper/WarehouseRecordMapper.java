package com.huijian.rac.mapper;

import com.huijian.rac.bean.WarehouseRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface WarehouseRecordMapper {

    List<WarehouseRecord> getWarehouseRecordByPage(@Param("start") Integer start,
                                                   @Param("size") Integer size,
                                                   @Param("keywords") String keywords,
                                                   @Param("startDate") Date startDate,
                                                   @Param("endDate") Date endDate);

    int update(WarehouseRecord warehouseRecord);

    int wareHouse(WarehouseRecord warehouseRecord);

    Integer selectByWarehouseNo(@Param(value = "warehouseNo") String warehouseNo);

    int inquiryOneYear();

    int deleteWarehouseRecordById(@Param(value = "ids")Integer[] ids);

    int inquiryCount(String keywords, Date startDate, Date endDate);
}
