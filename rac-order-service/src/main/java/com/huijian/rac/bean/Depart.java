package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Depart {
    private String departCode;
    private String departName;
    private String parentCode;
    private int isLab;
    private String srmOne;
    private String srmTwo;
    private String srmThree;
    private String memo;
    private int isActive;
    private String mobile;
    private String lastSendDate;
    private String hospitalID;
    private int departType;
}
