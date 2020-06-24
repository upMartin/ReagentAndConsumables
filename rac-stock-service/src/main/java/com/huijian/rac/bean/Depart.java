package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Depart {
    private String departcode;
    private String departname;
    private String parentcode;
    private int islab;
    private String srm1;
    private String srm2;
    private String srm3;
    private String memo;
    private int isactive;
    private String mobile;
    private String lastsenddate;
    private String hospitalid;
    private int departtype;
}
