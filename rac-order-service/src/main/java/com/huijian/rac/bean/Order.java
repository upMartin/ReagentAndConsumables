package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderNo;
    private String departCode;
    private String collarPersonID;
    private String auditPersonID;
    private String memo;
    private String sumPrice;
    private Integer urgent;
    private Integer orderState;
    private String arrivalDate;
    private String orderDate;
    private String auditDate;
    private String outStockDate;
    private String hospitalID;
    private String hospitalName;
    private List<OrderDetail> list;
}
