package com.huijian.rac.mapper;

import com.huijian.rac.bean.Unit;

public interface UnitMapper {
    Unit quiryByUnitName(String unitName);

    String quiryByUnitID(Integer unitID);
}
