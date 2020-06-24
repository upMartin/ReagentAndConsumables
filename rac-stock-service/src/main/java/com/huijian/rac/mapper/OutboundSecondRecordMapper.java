package com.huijian.rac.mapper;

import com.huijian.rac.bean.OutboundSecondRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OutboundSecondRecordMapper {

    List<OutboundSecondRecord> getOutboundSecondRecordByPage(
            @Param("start") Integer start,
            @Param("size") Integer size,
            @Param("keywords") String keywords,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("hospitalID")String hospitalID);

    int inquiryOneYear(String hospitalID);

    int wareHouse(OutboundSecondRecord outboundSecondRecord);

    int update(OutboundSecondRecord outboundSecondRecord);

    int deleteOutboundSecondRecord(@Param("leaveStockNo") int leaveStockNo,
                                   @Param("hospitalID") String hospitalID);

    int updateLeaveStockState(@Param("leaveStockDate") String leaveStockState,
                              @Param("hospitalID") String hospitalID,
                              @Param("leaveStockNo") Integer leaveStockNo);
}
