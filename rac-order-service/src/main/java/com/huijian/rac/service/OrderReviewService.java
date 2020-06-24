package com.huijian.rac.service;

import com.huijian.rac.bean.Order;
import com.huijian.rac.mapper.OrderReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderReviewService {
    @Autowired
    OrderReviewMapper orderReviewMapper;

    public List<Order> getOrderByPage(Integer page, Integer size, String keywords, String[] beginDateScope) throws ParseException {
        Integer start = (page-1)*size;
        String begin = beginDateScope[0];
        String end = beginDateScope[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = simpleDateFormat.parse(begin);
        Date endDate = simpleDateFormat.parse(end);
        return orderReviewMapper.getOrderByPage(start,size,keywords,startDate,endDate);
    }

    public boolean updateOrderState(String orderNo) {
        if (orderReviewMapper.updateOrderState(orderNo)==1){
            return true;
        }else {
            return false;
        }
    }
}
