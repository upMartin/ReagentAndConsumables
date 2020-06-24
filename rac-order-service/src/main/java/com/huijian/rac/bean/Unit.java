package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unit {
    private Integer unitID;
    private String unitName;
    private String remark;
    private Integer isActive;
    private Integer proportion;
}
