package com.huijian.rac.service;

import com.huijian.rac.bean.LeaveStockListDetail;
import com.huijian.rac.mapper.GoodsDictionaryMapper;
import com.huijian.rac.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    WarehouseMapper warehouseMapper;
    @Autowired
    GoodsDictionaryMapper goodsDictionaryMapper;

    public void cancelLeaveStock(List<LeaveStockListDetail> list) {
        list.stream().forEach(e->
                warehouseMapper.cancelLeaveStock(goodsDictionaryMapper.findGoodsIDByGoodsName(e.getName()), e.getAmount()));
    }

    public int inquiryQuantityByGoodsID(Integer goodsID) {
        return warehouseMapper.inquiryQuantityByGoodsID(goodsID);
    }
}
