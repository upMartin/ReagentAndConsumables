package com.huijian.rac.mapper;

import com.huijian.rac.bean.ReturnGoods;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ReturnGoodsMapper {

    Integer inquiryOneDay(String hospitalID);

    int addReturnGoods(@Param("returnGoods") ReturnGoods returnGoods);

    List<ReturnGoods> getReturnGoodsByPage(Integer start,
                                           Integer size,
                                           String keywords,
                                           Date startDate,
                                           Date endDate,String hospitalID);
}
