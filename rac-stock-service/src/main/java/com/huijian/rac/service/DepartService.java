package com.huijian.rac.service;

import com.huijian.rac.mapper.DepartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartService {
    @Autowired
    DepartMapper departMapper;

    public String inquiryDepartNameByCode(String departCode, String hospitalID) {
        return departMapper.inquiryDepartNameByCode(departCode, hospitalID);
    }
}
