package com.huijian.rac.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsPackageDetail {
    private Integer packageID;
    private Integer goodsID;
    private String goodsName;
    private String specification;
    private Integer urgent;
    private Integer amount;
    private Integer unitID;
    private String unit;
    private BigDecimal unitPrice;
   /* private Integer receiveNum;
    private Integer sendNum;*/
    private String memo;
    /*private Integer recommendedOrderQuantity;
    private Integer lastOrderQuantity;*/
    private String registrationNumber;
    private String pack;
    private String company;
    private String origin;
    /*private Integer currentInventory;
    private Integer lastOrderCycle;
    private Integer unarrivedQuantity;*/
    private Integer expirationDate;
}
