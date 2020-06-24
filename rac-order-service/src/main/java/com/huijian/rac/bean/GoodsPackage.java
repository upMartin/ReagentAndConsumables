package com.huijian.rac.bean;

import lombok.Data;

import java.util.List;

@Data
public class GoodsPackage {
    private Integer packageID;
    private String name;
    private List<GoodsPackageDetail> list;
}
