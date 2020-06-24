package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryUnit {
    private Integer supplierID;
    private String supplierName;
    private String abbreviation;
    private String linkman;
    private String telephone;
    private String address;
    private String email;
    private String postboat;
}
