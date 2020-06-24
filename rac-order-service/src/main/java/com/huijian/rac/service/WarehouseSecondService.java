package com.huijian.rac.service;

import com.huijian.rac.bean.WarehouseSecond;
import com.huijian.rac.mapper.WarehouseSecondMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseSecondService {
    @Autowired
    WarehouseSecondMapper warehouseSecondMapper;

    public int inquiryQuantity(Integer goodsID, String hospitalID) {
        int quantity = 0;
        List<WarehouseSecond> list = warehouseSecondMapper.inquiryByGoodsID(goodsID, hospitalID);
        for (WarehouseSecond warehouseSecond : list){
            quantity += warehouseSecond.getQuantity();
        }
        return quantity;
    }

}
