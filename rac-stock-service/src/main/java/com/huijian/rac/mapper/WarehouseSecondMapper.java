package com.huijian.rac.mapper;

import com.huijian.rac.bean.SecondLeaveStockListDetail;
import com.huijian.rac.bean.Warehouse;
import com.huijian.rac.bean.WarehouseSecond;
import org.apache.ibatis.annotations.Param;

public interface WarehouseSecondMapper {
    int selectByGoodsID(int goodsID);

    void warehouse(WarehouseSecond warehouse);

    void update(@Param("goodsID") int goodsID, @Param("amount") String amount, @Param("hospitalID") String hospitalID);

    int inquiryQuantityByGoodsID(Integer goodsID, String hospitalID);

    int findWarehouse(@Param("goodsID") Integer goodsID, @Param("hospitalID") String hospitalID);

    int leaveStock(SecondLeaveStockListDetail secondLeaveStockListDetail);
}
