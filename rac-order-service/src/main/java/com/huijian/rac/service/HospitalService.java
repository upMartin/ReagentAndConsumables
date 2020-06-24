package com.huijian.rac.service;

import com.huijian.rac.mapper.HospitalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {
    @Autowired
    HospitalMapper hospitalMapper;

    public String inquiryHospitalNameByID(String hospitalID) {
        return hospitalMapper.inquiryHospitalNameByID(hospitalID);
    }
}
