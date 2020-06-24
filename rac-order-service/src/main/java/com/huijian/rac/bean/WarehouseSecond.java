package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseSecond {
    private Integer ID;
    private Integer goodsID;
    private Integer quantity;
    private String storageLocation;
    private String hospitalID;
}
