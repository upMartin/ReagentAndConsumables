package com.huijian.rac.bean;

import java.io.Serializable;
import java.util.List;

public class MaterialList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Material> list;
    public List<Material> getList() {
        return list;
    }
    public void setList(List<Material> list) {
        this.list = list;
    }
}
