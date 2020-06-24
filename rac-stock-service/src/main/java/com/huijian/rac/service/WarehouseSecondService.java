package com.huijian.rac.service;

import com.huijian.rac.mapper.WarehouseSecondMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseSecondService {
    @Autowired
    WarehouseSecondMapper warehouseSecondMapper;

    public int inquiryQuantityByGoodsID(Integer goodsID, String hospitalID) {
        return warehouseSecondMapper.inquiryQuantityByGoodsID(goodsID, hospitalID);
    }
}
