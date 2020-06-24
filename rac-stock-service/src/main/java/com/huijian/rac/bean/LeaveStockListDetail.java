package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveStockListDetail {
    private Integer goodsID;
    private String name;
    private String brand;
    private String specification;
    private String unit;
    private String secondaryAccounting;
    private String amount;
    private String unitPrice;
    private String sum;
    private String productionBatch;
    private String termOfValidity;
    private String registrationNumber;
    private String pack;
    private String company;
    private String origin;
    private Integer leaveStockNo;
    private Integer warehouse;
}
