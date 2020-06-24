package com.huijian.rac.bean;

import lombok.Data;

import java.util.List;

@Data
public class HospitalIDAndGood {
    private String hospitalID;
    private List<Good> good;
}
