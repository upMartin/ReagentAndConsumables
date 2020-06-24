package com.huijian.rac.mapper;

import com.huijian.rac.bean.LeaveStockListDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaveStockListDetailMapper {
    List<LeaveStockListDetail> inquiryLeaveStockListDetail(Integer leaveStockNo);

    int wareHouse(LeaveStockListDetail leaveStockListDetail);

    int update(LeaveStockListDetail leaveStockListDetail);

    int deleteLeaveStockListDetailByGoodsIDAndNo(@Param("ids") Integer[] ids, @Param("leaveStockNo") String leaveStockNo);
}
