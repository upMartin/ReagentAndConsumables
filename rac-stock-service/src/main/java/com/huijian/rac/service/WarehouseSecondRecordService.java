package com.huijian.rac.service;

import com.huijian.rac.bean.*;
import com.huijian.rac.mapper.GoodsDictionaryMapper;
import com.huijian.rac.mapper.SecondGoodsRecordMapper;
import com.huijian.rac.mapper.WarehouseSecondMapper;
import com.huijian.rac.mapper.WarehouseSecondRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WarehouseSecondRecordService {
    @Autowired
    WarehouseSecondRecordMapper warehouseSecondRecordMapper;
    @Autowired
    SecondGoodsRecordMapper secondGoodsRecordMapper;
    @Autowired
    GoodsDictionaryMapper goodsDictionaryMapper;
    @Autowired
    WarehouseSecondMapper warehouseSecondMapper;
    @Autowired
    OrderStateService orderStateService;


    public int inquiryOneYear(String hospitalID) {
        return warehouseSecondRecordMapper.inquiryOneYear(hospitalID);
    }

    public boolean wareHouse(WarehouseSecondRecord warehouseSecondRecord) {
        //期望添加insertSum条数据
        int insertSum = 0;
        //实际添加insertResult条数据
        int insertResult = 0;
        int j = warehouseSecondRecordMapper.wareHouse(warehouseSecondRecord);
        String warehouseNo = warehouseSecondRecord.getWarehouseNo();
        Integer id = warehouseSecondRecordMapper.selectByWarehouseNo(warehouseNo, warehouseSecondRecord.getHospitalID());
        WarehouseSecond warehouseSecond = new WarehouseSecond();
        //将添加条数与订单明细条数比较，如果不等于则为部分到货，并更新订单状态
        Order order = new Order();
        order.setOrderNo(warehouseSecondRecord.getOrderNo());
        Map<String, Object> map = orderStateService.inquiryOrderDetail(order);
        int orderDetailSum = ((List<OrderDetail>)map.get("OrderDetail")).stream().mapToInt(i-> i.getAmount()).sum();
        int warehouseSecondRecordDetailSum = (warehouseSecondRecord.getList()).stream().mapToInt(i->Integer.parseInt(i.getAmount())).sum();
        if (orderDetailSum == warehouseSecondRecordDetailSum){
            orderStateService.updateOrderState(order.getOrderNo(), warehouseSecondRecord.getWarehouseDate(),5);
        }else {
            orderStateService.updateOrderState(order.getOrderNo(), warehouseSecondRecord.getWarehouseDate(),6);
        }

        for (SecondGoodsRecord goodsRecord : warehouseSecondRecord.getList()) {
            goodsRecord.setWarehouseSecondRecordID(id);
            String name = goodsRecord.getName();
            if (name != null && name != "") {
                insertSum += 1;
                insertResult = insertResult + secondGoodsRecordMapper.wareHouse(goodsRecord);
                int goodsID = goodsDictionaryMapper.findGoodsIDByGoodsName(goodsRecord.getName());
                int count = warehouseSecondMapper.selectByGoodsID(goodsID);
                if (count == 0) {
                    warehouseSecond.setGoodsID(goodsID);
                    warehouseSecond.setQuantity(Integer.parseInt(goodsRecord.getAmount()));
                    warehouseSecond.setHospitalID(warehouseSecondRecord.getHospitalID());
                    warehouseSecondMapper.warehouse(warehouseSecond);
                } else {
                    warehouseSecondMapper.update(goodsID, goodsRecord.getAmount(),warehouseSecondRecord.getHospitalID());
                }
            }
        }

        if (j == 1 && insertSum == insertResult) {
            return true;
        } else {
            return false;
        }
    }

    public List<WarehouseSecondRecord> getWarehouseRecordByPage(Integer page, Integer size, String keywords, String[] beginDateScope, String hospitalID) throws ParseException {
        Integer start = (page - 1) * size;
        String begin = beginDateScope[0];
        String end = beginDateScope[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = simpleDateFormat.parse(begin);
        Date endDate = simpleDateFormat.parse(end);
        return warehouseSecondRecordMapper.getWarehouseRecordByPage(start, size, keywords, startDate, endDate, hospitalID);
    }

    public boolean deleteWarehouseSecondRecordById(String ids) {
        String[] spl = ids.split("-");
        String[] split = spl[0].split(",");
        Integer[] id = new Integer[split.length];
        int i = 0;
        for (String s : split) {
            id[i] = Integer.parseInt(s);
            i += 1;
        }
        int sum = 0;
        for (int j = 0; j < id.length; j++) {
            sum += secondGoodsRecordMapper.inquirySecondGoodsRecordByID(id[j],spl[1]).size();
        }
        int realSum = secondGoodsRecordMapper.deleteSecondGoodsRecordByWid(id,spl[1]);
        int count = warehouseSecondRecordMapper.deleteWarehouseSecondRecordById(id,spl[1]);
        if (count == split.length && sum == realSum) {
            return true;
        } else {
            return false;
        }
    }

    public List<SecondGoodsRecord> inquirySecondGoodsRecordByID(Integer warehouseSecondRecordID, String hospitalID) {
        return secondGoodsRecordMapper.inquirySecondGoodsRecordByID(warehouseSecondRecordID, hospitalID);
    }

    public boolean deleteSecondGoodsRecordByGid(String ids) {
        String[] spl = ids.split("-");
        String[] split = spl[0].split(",");
        Integer[] id = new Integer[split.length];
        int i = 0;
        for (String s : split) {
            id[i] = Integer.parseInt(s);
            i += 1;
        }
        int count = secondGoodsRecordMapper.deleteGoodsRecordByGid(id, spl[1],Integer.parseInt(spl[2]));
        if (count == split.length) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(WarehouseSecondRecord warehouseSecondRecord) {
        List<SecondGoodsRecord> list = secondGoodsRecordMapper
                .inquirySecondGoodsRecordByID(warehouseSecondRecord.getID(),warehouseSecondRecord.getHospitalID());

        warehouseSecondRecord.getList().stream().filter(e->{
            return list.stream().map(f->f.getGoodsID()).collect(Collectors.toList()).contains(e.getGoodsID());
        }).forEach(e->secondGoodsRecordMapper.update(e));

        warehouseSecondRecord.getList().stream().filter(e->{
            return !(list.stream().map(f->f.getGoodsID()).collect(Collectors.toList()).contains(e.getGoodsID()));
        }).forEach(e->{
            e.setWarehouseSecondRecordID(warehouseSecondRecord.getID());
            secondGoodsRecordMapper.wareHouse(e);
        });
        int j = warehouseSecondRecordMapper.update(warehouseSecondRecord);
        if (j >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
