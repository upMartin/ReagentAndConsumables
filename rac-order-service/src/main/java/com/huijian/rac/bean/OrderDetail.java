package com.huijian.rac.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderNo;
    private Integer goodsID;
    private String goodsName;
    private String brand;
    private String specification;
    private Integer unitID;
    private String unit;
    private BigDecimal unitPrice;
    private Integer amount;
    private Integer receiveNum;
    private Integer sendNum;
    private String memo;
    private Integer recommendedOrderQuantity;
    private Integer lastOrderQuantity;
    private String productionBatch;
    private String termOfValidity;
    private String registrationNumber;
    private String pack;
    private String company;
    private String origin;
    private Integer currentInventory;
    private Integer lastOrderCycle;
    private Integer unarrivedQuantity;
}
