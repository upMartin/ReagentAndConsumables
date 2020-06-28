package com.huijian.rac.service;

import com.huijian.rac.bean.ReturnGoods;
import com.huijian.rac.bean.ReturnGoodsDetail;
import com.huijian.rac.mapper.OrderReturnDetailMapper;
import com.huijian.rac.mapper.OrderReturnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderReturnService {
    @Autowired
    OrderReturnMapper orderReturnMapper;
    @Autowired
    OrderReturnDetailMapper orderReturnDetailMapper;

    public List<ReturnGoods> getReturnGoodsByPage(Integer page, Integer size, String keywords, String[] beginDateScope, String hospitalID)
            throws ParseException {
        Integer start = (page-1)*size;
        String begin = beginDateScope[0];
        String end = beginDateScope[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = simpleDateFormat.parse(begin);
        Date endDate = simpleDateFormat.parse(end);
        return orderReturnMapper.getReturnGoodsByPage(start,size,keywords,startDate,endDate);
    }

    public List<ReturnGoodsDetail> inquiryByID(String returnGoodsNo) {
        return orderReturnDetailMapper.inquiryByID(returnGoodsNo);
    }

    public int updateReturnGoodsState(ReturnGoods returnGoods) {
        return orderReturnMapper.updateReturnGoodsState(returnGoods);
    }
}
