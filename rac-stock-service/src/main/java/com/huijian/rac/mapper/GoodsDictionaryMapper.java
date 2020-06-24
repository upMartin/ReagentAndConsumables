package com.huijian.rac.mapper;

import com.huijian.rac.bean.Good;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GoodsDictionaryMapper {

    List<Good> getAllGoods();

    Date getWarehouseDate();

    int updateGoodsDictionary(Good good);

    int findGoodsIDByGoodsName(String name);

    List<Good> inquiryByWord(String goodsName);

    List<Good> inquiryByIDAndName(@Param("goodsID") Integer goodsID, @Param("goodsName") String goodsName);
}
