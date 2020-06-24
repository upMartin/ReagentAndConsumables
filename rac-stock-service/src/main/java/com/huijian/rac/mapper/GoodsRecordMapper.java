package com.huijian.rac.mapper;

import com.huijian.rac.bean.GoodsRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsRecordMapper {
    int update(GoodsRecord goodsRecord);

    int deleteGoodsRecordByGoodsId(@Param(value = "ids") Integer[] ids, @Param("warehouseRecordID")int warehouseRecordID);

    int ifExistGoodsRecordByGid(Integer id);

    int wareHouse(GoodsRecord goodsRecord);

    List<GoodsRecord> inquiryGoodsRecordByID(Integer id);

    int deleteGoodsRecordByWid(@Param(value = "ids")Integer[] ids);
}
