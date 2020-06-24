package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalIDAndGood {
    private String hospitalID;
    private List<Good> good;
}
