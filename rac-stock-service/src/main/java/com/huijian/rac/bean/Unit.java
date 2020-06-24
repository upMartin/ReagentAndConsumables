package com.huijian.rac.bean;

import lombok.Data;

@Data
public class Unit {
    private Integer unitID;
    private String unitName;
    private String remark;
    private Integer isActive;
    private Integer proportion;
}
