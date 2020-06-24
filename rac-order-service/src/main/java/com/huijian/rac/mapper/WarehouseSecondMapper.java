package com.huijian.rac.mapper;

import com.huijian.rac.bean.WarehouseSecond;

import java.util.List;

public interface WarehouseSecondMapper {
    List<WarehouseSecond> inquiryByGoodsID(Integer goodsID, String hospitalID);
}
