package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 试剂实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reagent implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;

}
