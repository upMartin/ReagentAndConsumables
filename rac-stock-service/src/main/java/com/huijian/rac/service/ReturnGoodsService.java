package com.huijian.rac.service;

import com.huijian.rac.bean.ReturnGoods;
import com.huijian.rac.bean.ReturnGoodsDetail;
import com.huijian.rac.mapper.ReturnGoodsDetailMapper;
import com.huijian.rac.mapper.ReturnGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReturnGoodsService {
    @Autowired
    ReturnGoodsMapper returnGoodsMapper;
    @Autowired
    ReturnGoodsDetailMapper returnGoodsDetailMapper;
    public Integer inquiryOneDay(String hospitalID) {
        return returnGoodsMapper.inquiryOneDay(hospitalID);
    }

    public boolean addReturnGoods(ReturnGoods returnGoods) {
        if (returnGoodsMapper.addReturnGoods(returnGoods)==1){
            return true;
        }else {
            return false;
        }
    }

    public boolean addReturnGoodsDetail(ReturnGoods returnGoods) {
        if(returnGoodsDetailMapper.addReturnGoodsDetail(returnGoods.getList())==returnGoods.getList().size()){
            return true;
        }else {
            return false;
        }
    }

    public List<ReturnGoods> getReturnGoodsByPage(Integer page, Integer size, String keywords, String[] beginDateScope, String hospitalID) throws ParseException {
        Integer start = (page - 1) * size;
        String begin = beginDateScope[0];
        String end = beginDateScope[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = simpleDateFormat.parse(begin);
        Date endDate = simpleDateFormat.parse(end);
        return returnGoodsMapper.getReturnGoodsByPage(start, size, keywords, startDate, endDate, hospitalID);
    }

    public List<ReturnGoodsDetail> inquiryByID(String returnGoodsNo) {
        return returnGoodsDetailMapper.inquiryByID(returnGoodsNo);
    }
}
