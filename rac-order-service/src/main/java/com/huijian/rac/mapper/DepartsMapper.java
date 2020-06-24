package com.huijian.rac.mapper;

import com.huijian.rac.bean.Depart;

import java.util.List;

public interface DepartsMapper {
    List<Depart> quiryDepartsByHospitalID(String hospitalID);
}
