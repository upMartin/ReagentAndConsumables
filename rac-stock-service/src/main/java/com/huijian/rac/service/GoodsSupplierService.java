package com.huijian.rac.service;

import com.huijian.rac.mapper.GoodsSupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsSupplierService {
    @Autowired
    GoodsSupplierMapper goodsSupplierMapper;

    public String inquirySupplierNameByID(Integer supplierID) {
        return goodsSupplierMapper.inquirySupplierNameByID(supplierID);
    }
}
