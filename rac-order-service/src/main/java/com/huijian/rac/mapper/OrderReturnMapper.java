package com.huijian.rac.mapper;

import com.huijian.rac.bean.ReturnGoods;
import com.huijian.rac.bean.ReturnGoodsDetail;

import java.util.Date;
import java.util.List;

public interface OrderReturnMapper {
    List<ReturnGoods> getReturnGoodsByPage(Integer start, Integer size, String keywords, Date startDate, Date endDate);

    List<ReturnGoodsDetail> inquiryByID(String returnGoodsNo);

    int updateReturnGoodsState(ReturnGoods returnGoods);
}
