package com.huijian.rac.service;

import com.huijian.rac.bean.Order;
import com.huijian.rac.mapper.OrderDetailMapper;
import com.huijian.rac.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;

    /**
     * 分页查询订单
     * @param page
     * @param size
     * @param keywords
     * @param beginDateScope
     * @return
     * @throws ParseException
     */
    public List<Order> getOrderByPage(Integer page, Integer size, String keywords, String[] beginDateScope,String hospitalID) throws ParseException {
        Integer start = (page-1)*size;
        String begin = beginDateScope[0];
        String end = beginDateScope[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = simpleDateFormat.parse(begin);
        Date endDate = simpleDateFormat.parse(end);
        return orderMapper.getOrderByPage(start,size,keywords,startDate,endDate,hospitalID);

    }

    public boolean insert(Order order) throws ParseException{
        int sum = orderMapper.insert(order);
        if (sum==1){
            return true;
        }else{
            return false;
        }
    }

    public int inquiryOneYear() {
        return orderMapper.inquiryOneYear();
    }

    public Order inquiryLastOrder(String hospitalID) {
        return orderMapper.inquiryLastOrder(hospitalID);
    }

    public List<Order> inquiryLastOneYearOrder(String hospitalID) {
        return orderMapper.inquiryLastOneYearOrder(hospitalID);
    }

    public boolean deleteOrderByOrderNo(String ids) {
        String[] orderNos = ids.split(",");
        int expectedSum = 0;
        int actualSum = 0;
        for(String o : orderNos){
            expectedSum += 1;
            expectedSum += orderDetailMapper.findByOrderNo(o);
            actualSum += orderDetailMapper.deleteOrderDetailByOrderNo(o);
        }
        actualSum += orderMapper.deleteOrderByOrderNo(orderNos);
        if (expectedSum == actualSum){
            return true;
        }else {
            return false;
        }
    }

    public boolean update(Order order) {
        int num = orderMapper.update(order);
        int sum = orderDetailMapper.update(order.getList());
        /*&allowMultiQueries=true*/
        if (num == 1){
            return true;
        }else {
            return false;
        }
    }

    public boolean updateOrderState(Order order) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setOrderDate(simpleDateFormat.format(new Date()));
        if(orderMapper.updateOrderState(order)==1){
            return true;
        }else{
            return false;
        }
    }

    public int updateOrderStateByOrderNo(String orderNo, String orderDate, Integer orderState) {
        return orderMapper.updateOrderStateByOrderNo(orderNo, orderDate, orderState);
    }

    public Integer inquiryOrderState(String orderNo) {
        return orderMapper.inquiryOrderState(orderNo);
    }
}
