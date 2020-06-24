package com.huijian.rac.mapper;

import com.huijian.rac.bean.DeliveryUnit;

import java.util.List;

public interface DeliveryUnitMapper {
    List<DeliveryUnit> getAllDeliveryUnit();

    DeliveryUnit getDeliveryUnitBySupplierName(String name);
}
