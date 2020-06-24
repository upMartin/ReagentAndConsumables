package com.huijian.rac.mapper;

import com.huijian.rac.bean.GoodsRecord;
import com.huijian.rac.bean.SecondGoodsRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecondGoodsRecordMapper {
    int wareHouse(SecondGoodsRecord goodsRecord);

    List<SecondGoodsRecord> inquirySecondGoodsRecordByID(@Param("warehouseSecondRecordID") Integer warehouseSecondRecordID,
                                                         @Param("hospitalID") String hospitalID);

    int deleteSecondGoodsRecordByWid(@Param("ids") Integer[] ids, @Param("hospitalID") String hospitalID);

    int deleteGoodsRecordByGid(@Param("ids")Integer[] ids, @Param("hospitalID") String hospitalID, @Param("warehouseSecondRecordID")int warehouseSecondRecordID);

    int update(SecondGoodsRecord secondGoodsRecord);
}
