package com.huijian.rac.service;

import com.huijian.rac.bean.DiscardDetail;
import com.huijian.rac.mapper.DiscardDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscardDetailService {
    @Autowired
    DiscardDetailMapper discardDetailMapper;

    public List<DiscardDetail> inquiryByID(String discardNo) {
        return discardDetailMapper.inquiryByID(discardNo);
    }
}
