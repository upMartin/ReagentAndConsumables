package com.huijian.rac.mapper;

import com.huijian.rac.bean.OutboundRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OutboundRecordMapper {
    List<OutboundRecord> getOutboundRecordByPage(@Param("start") Integer start,
                                                 @Param("size") Integer size,
                                                 @Param("keywords") String keywords,
                                                 @Param("startDate") Date startDate,
                                                 @Param("endDate") Date endDate);

    int inquiryOneYear();

    int wareHouse(OutboundRecord outboundRecord);

    int update(OutboundRecord outboundRecord);


}
