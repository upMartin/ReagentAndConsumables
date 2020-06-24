package com.huijian.rac.mapper;

import com.huijian.rac.bean.WarehouseRecord;
import com.huijian.rac.bean.WarehouseSecondRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface WarehouseSecondRecordMapper {
    int inquiryOneYear(String hospitalID);

    int wareHouse(WarehouseSecondRecord warehouseSecondRecord);

    Integer selectByWarehouseNo(@Param("warehouseNo") String warehouseNo, @Param("hospitalID") String hospitalID);

    List<WarehouseSecondRecord> getWarehouseRecordByPage(Integer start, Integer size, String keywords, Date startDate, Date endDate, String hospitalID);

    int deleteWarehouseSecondRecordById(@Param("ids") Integer[] ids, @Param("hospitalID") String hospitalID);

    int update(WarehouseSecondRecord warehouseSecondRecord);
}
