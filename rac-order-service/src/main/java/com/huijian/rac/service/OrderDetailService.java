package com.huijian.rac.service;

import com.huijian.rac.bean.OrderDetail;
import com.huijian.rac.bean.Unit;
import com.huijian.rac.mapper.OrderDetailMapper;
import com.huijian.rac.mapper.UnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailMapper orderDetailMapper;
    @Autowired
    UnitMapper unitMapper;
    /**
     * 查询某个订单中某件物品的数量
     * @param orderNo
     * @param goodsID
     * @return
     */
    public List<OrderDetail> inquriyByOrderNo(Integer[] goodsID) {
        return orderDetailMapper.inquiryByOrderNo(goodsID);
    }

    /**
     * 添加物资明细
     * @param list
     * @return
     */
    public boolean insert(List<OrderDetail> list) {
        int sum = 0;
        for(OrderDetail orderDetail : list){
            /*Unit unit = unitMapper.quiryByUnitName(orderDetail.getUnit());
            orderDetail.setUnitID(unit.getUnitID());*/
            sum += orderDetailMapper.insert(orderDetail);
        }
        if(sum==list.size()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 查询某个订单的物资明细
     * @param orderNo
     * @return
     */
    public List<OrderDetail> findByOrderNo(String orderNo) {
        List<OrderDetail> list = orderDetailMapper.findListByOrderNo(orderNo);
        for (OrderDetail orderDetail : list){
            orderDetail.setUnit(unitMapper.quiryByUnitID(orderDetail.getUnitID()));
        }
        return list;
    }
}
