package com.huijian.rac.mapper;

import com.huijian.rac.bean.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    List<Order> getOrderByPage(Integer start, Integer size, String keywords, Date startDate, Date endDate,String hospitalID);

    int insert(Order order);

    int inquiryOneYear();

    Order inquiryLastOrder(String hospitalID);

    List<Order> inquiryLastOneYearOrder(String hospitalID);

    int deleteOrderByOrderNo(@Param("orderNos") String[] orderNos);

    int update(Order order);

    int updateOrderState(@Param("order") Order order);

    int updateOrderStateByOrderNo(@Param("orderNo") String orderNo,
                                  @Param("orderDate") String orderDate,
                                  @Param("orderState") Integer orderState);

    Integer inquiryOrderState(String orderNo);
}
