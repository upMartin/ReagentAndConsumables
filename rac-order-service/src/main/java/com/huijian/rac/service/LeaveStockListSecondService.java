package com.huijian.rac.service;

import com.huijian.rac.bean.LeaveStockList;
import com.huijian.rac.bean.LeaveStockListSecond;
import com.huijian.rac.bean.Order;
import com.huijian.rac.mapper.LeaveStockListSecondMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveStockListSecondService {
    @Autowired
    LeaveStockListSecondMapper leaveStockListSecondMapper;

    public List<LeaveStockListSecond> inquiryLastOneYear(String hospitalID, Integer[] goodsID) {
        return leaveStockListSecondMapper.inquiryLastOneYear(hospitalID,goodsID);
    }
}
