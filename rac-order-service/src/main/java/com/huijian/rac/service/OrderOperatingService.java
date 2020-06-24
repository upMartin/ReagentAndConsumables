package com.huijian.rac.service;

import com.huijian.rac.bean.OrderOperate;
import com.huijian.rac.mapper.OrderOperateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderOperatingService {
    @Autowired
    OrderOperateMapper orderOperateMapper;

    public List<OrderOperate> inquiryOrderSchedule(String orderNo) {
        return orderOperateMapper.inquiryOrderSchedule(orderNo);
    }

    public int insertOrderOperating(OrderOperate orderOperate) {
        return orderOperateMapper.insertOrderOperating(orderOperate);
    }
}
