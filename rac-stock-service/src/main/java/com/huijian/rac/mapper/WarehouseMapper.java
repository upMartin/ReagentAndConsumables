package com.huijian.rac.mapper;

import com.huijian.rac.bean.LeaveStockListDetail;
import com.huijian.rac.bean.Warehouse;
import org.apache.ibatis.annotations.Param;

public interface WarehouseMapper {
    int findWarehouseByGoodsID(int goodsID);

    int leaveStock(LeaveStockListDetail leaveStockListDetail);

    void cancelLeaveStock(@Param("goodsID") int goodsID, @Param("amount") String amount);

    void warehouse(Warehouse warehouse);

    int selectByGoodsID(int goodsID);

    void update(@Param("goodsID") int goodsID, @Param("amount") String amount);

    int inquiryQuantityByGoodsID(Integer goodsID);
}
