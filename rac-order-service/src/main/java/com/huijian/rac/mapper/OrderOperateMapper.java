package com.huijian.rac.mapper;

import com.huijian.rac.bean.OrderOperate;

import java.util.List;

public interface OrderOperateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderOperate record);

    int insertSelective(OrderOperate record);

    OrderOperate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderOperate record);

    int updateByPrimaryKey(OrderOperate record);

    List<OrderOperate> inquiryOrderSchedule(String orderNo);

    int insertOrderOperating(OrderOperate orderOperate);
}