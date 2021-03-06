package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseSecondRecord {
    private Integer ID;
    private String warehouseNo;
    private String purchaseNo;
    private String deliveryNo;
    private String businessType;
    private String deliveryUnit;
    private String status;
    private BigDecimal amount;
    private String accountingSubjects;
    private String personID;
    private String warehouseDate;
    private String billingDate;
    private String memo;
    private String externalOutboundOrder;
    private String goodsID;
    private String hospitalID;
    private String orderNo;
    private List<SecondGoodsRecord> list;
}
