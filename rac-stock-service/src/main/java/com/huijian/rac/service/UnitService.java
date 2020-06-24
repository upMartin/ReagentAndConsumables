package com.huijian.rac.service;

import com.huijian.rac.bean.Unit;
import com.huijian.rac.mapper.UnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitService {
    @Autowired
    UnitMapper unitMapper;

    public Unit quiryByUnitID(Integer unitID) {
        return unitMapper.quiryByUnitID(unitID);
    }
}
