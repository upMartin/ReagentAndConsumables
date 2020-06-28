package com.huijian.rac.mapper;

import com.huijian.rac.bean.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailMapper {
    List<OrderDetail> inquiryByOrderNo(
                         @Param("goodsIDs") Integer[] goodsIDs);

    int insert(OrderDetail orderDetail);

    int deleteOrderDetailByOrderNo(String orderNo);

    int findByOrderNo(String orderNo);

    List<OrderDetail> findListByOrderNo(String orderNo);

    int update(List<OrderDetail> list);
}
