package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveStockListSecond {
    private int ID;
    private int goodsID;
    private String personID;
    private String memo;
    private BigDecimal unitPrice;
    private String TXM;
    private int Quantity;
    private String pickDate;
    private String hospitalID;
}
