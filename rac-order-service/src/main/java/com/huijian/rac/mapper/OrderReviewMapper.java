package com.huijian.rac.mapper;

import com.huijian.rac.bean.Order;

import java.util.Date;
import java.util.List;

public interface OrderReviewMapper {

    List<Order> getOrderByPage(Integer start, Integer size, String keywords, Date startDate, Date endDate);

    int updateOrderState(String orderNo);
}
