package com.huijian.rac.mapper;

import com.huijian.rac.bean.OrderDetail;
import com.huijian.rac.bean.ReturnGoodsDetail;

import java.util.List;

public interface ReturnGoodsDetailMapper {
    int addReturnGoodsDetail(List<ReturnGoodsDetail> list);

    List<ReturnGoodsDetail> inquiryByID(String returnGoodsNo);
}
