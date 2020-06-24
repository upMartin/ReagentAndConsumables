package com.huijian.rac.service;

import com.huijian.rac.bean.DeliveryUnit;
import com.huijian.rac.mapper.DeliveryUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryUnitService {
    @Autowired
    DeliveryUnitMapper deliveryUnitMapper;

    public List<DeliveryUnit> getAllDeliveryUnit() {
        return deliveryUnitMapper.getAllDeliveryUnit();
    }

    public DeliveryUnit getDeliveryUnitBySupplierName(String name) {
        return deliveryUnitMapper.getDeliveryUnitBySupplierName(name);
    }
}
