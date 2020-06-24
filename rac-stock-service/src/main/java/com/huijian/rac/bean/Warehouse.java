package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    private int ID;
    private int goodsID;
    private int quantity;
    private String storgeLocation;
}
