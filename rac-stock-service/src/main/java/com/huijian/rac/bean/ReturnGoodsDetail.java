package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnGoodsDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private String returnGoodsNo;
    private Integer goodsID;
    private String goodsName;
    private Integer unitID;
    private String unit;
    private BigDecimal unitPrice;
    private Integer amount;
    private String productionBatch;
}
