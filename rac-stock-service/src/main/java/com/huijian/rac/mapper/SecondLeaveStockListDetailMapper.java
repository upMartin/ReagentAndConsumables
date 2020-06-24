package com.huijian.rac.mapper;

import com.huijian.rac.bean.LeaveStockListDetail;
import com.huijian.rac.bean.OutboundSecondRecord;
import com.huijian.rac.bean.SecondLeaveStockListDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecondLeaveStockListDetailMapper {
    int deleteGoodsRecordByGid(@Param("ids")Integer[] ids,
                               @Param("hospitalID") String hospitalID,
                               @Param("leaveStockNo") int leaveStockNo);

    List<SecondLeaveStockListDetail> inquirySecondLeaveStockListDetail(OutboundSecondRecord outboundSecondRecord);

    int wareHouse(SecondLeaveStockListDetail secondLeaveStockListDetail);

    int update(SecondLeaveStockListDetail secondLeaveStockListDetail);

    int deletesecondLeaveStockListDetail(@Param("leaveStockNo") int leaveStockNo,
                                         @Param("hospitalID") String hospitalID);
}
