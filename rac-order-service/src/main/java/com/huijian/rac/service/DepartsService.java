package com.huijian.rac.service;

import com.huijian.rac.bean.Depart;
import com.huijian.rac.mapper.DepartsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartsService {
    @Autowired
    DepartsMapper departsMapper;

    public List<Depart> quiryDepartsByHospitalID(String hospitalID) {
        return departsMapper.quiryDepartsByHospitalID(hospitalID);
    }
}
