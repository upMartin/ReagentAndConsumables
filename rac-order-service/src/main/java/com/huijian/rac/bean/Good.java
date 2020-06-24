package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {

        private static final long serialVersionUID = 1L;
        /*private Integer goodsID;
        private String goodsName;
        private String specsType;
        private Integer unitID;
        private String unit;
        private BigDecimal unitPrice;
        private Integer categoryTypeID;
        private Integer supplierID;
        private String TXM;
        private String abbreviation;
        private String passCode;
        private Integer expirationDate;*/
        private Integer goodsID;
        private String goodsName;
        private String brand;
        private String specification;
        private String unit;
        private BigDecimal unitPrice;
        private Integer recommendedOrderQuantity;
        private Integer lastOrderQuantity;
        private Integer categoryTypeID;
        private Integer supplierID;
        private String TXM;
        private String abbreviation;
        private String passCode;
        private Integer expirationDate;
        private String secondaryAccounting;
        private String registrationNumber;
        private String pack;
        private String company;
        private String origin;


}
