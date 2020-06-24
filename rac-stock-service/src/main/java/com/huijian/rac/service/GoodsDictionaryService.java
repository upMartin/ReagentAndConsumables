package com.huijian.rac.service;

import com.huijian.rac.bean.Good;
import com.huijian.rac.mapper.GoodsDictionaryMapper;
import com.huijian.rac.mapper.GoodsRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsDictionaryService {
    @Autowired
    GoodsDictionaryMapper goodsDictionaryMapper;

    public List<Good> getAllGoods() {
        return goodsDictionaryMapper.getAllGoods();

    }

    public Date getWarehouseDate() {
        return goodsDictionaryMapper.getWarehouseDate();
    }

    public boolean updateGoodsDictionary(Good good) {
        if(goodsDictionaryMapper.updateGoodsDictionary(good)==1){
            return true;
        }else{
            return false;
        }
    }

    public List<Good> inquiryByWord(String goodsName) {
        return goodsDictionaryMapper.inquiryByWord(goodsName);
    }

    public List<Good> inquiryByIDAndName(Integer goodsID, String goodsName) {
        return goodsDictionaryMapper.inquiryByIDAndName(goodsID,goodsName);
    }
}
