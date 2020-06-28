package com.huijian.rac.mapper;

import com.huijian.rac.bean.ReturnGoodsDetail;

import java.util.List;

public interface OrderReturnDetailMapper {
    List<ReturnGoodsDetail> inquiryByID(String returnGoodsNo);
}
