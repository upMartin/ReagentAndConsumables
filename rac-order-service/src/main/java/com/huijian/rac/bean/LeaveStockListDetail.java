package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveStockListDetail {
    private Integer gid;
    private String name;
    private String brand;
    private String specification;
    private String unit;
    private String secondaryAccounting;
    private String amount;
    private String unitPrice;
    private String sum;
    private String productionBatch;
    private String TermOfValidity;
    private String RegistrationNumber;
    private String pack;
    private String company;
    private String origin;
    private Integer leaveStockNo;
}
