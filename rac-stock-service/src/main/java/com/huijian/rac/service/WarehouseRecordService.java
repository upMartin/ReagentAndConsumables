package com.huijian.rac.service;


import com.huijian.rac.bean.*;
import com.huijian.rac.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class WarehouseRecordService {
    @Autowired
    WarehouseRecordMapper warehouseRecordMapper;
    @Autowired
    GoodsRecordMapper goodsRecordMapper;
    @Autowired
    LeaveStockListDetailMapper leaveStockListDetailMapper;
    @Autowired
    WarehouseMapper warehouseMapper;
    @Autowired
    GoodsDictionaryMapper goodsDictionaryMapper;

    public List<WarehouseRecord> getWarehouseRecordByPage(Integer page, Integer size, String keywords, String[] beginDateScope) throws ParseException {
        Integer start = (page - 1) * size;
        String begin = beginDateScope[0];
        String end = beginDateScope[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = simpleDateFormat.parse(begin);
        Date endDate = simpleDateFormat.parse(end);
        return warehouseRecordMapper.getWarehouseRecordByPage(start, size, keywords, startDate, endDate);
    }

    public boolean update(WarehouseRecord warehouseRecord) {
        int updateResult = 0;
        //实际添加insertResult条数据
        int insertResult = 0;
        for (GoodsRecord goodsRecord : warehouseRecord.getList()) {
            if (goodsRecord.getWarehouseRecordID() != null) {
                updateResult = updateResult + goodsRecordMapper.update(goodsRecord);
            } else {
                goodsRecord.setWarehouseRecordID(warehouseRecord.getID());
                String name = goodsRecord.getName();
                if (name != null && name != "") {
                    insertResult = insertResult + goodsRecordMapper.wareHouse(goodsRecord);
                }
            }
        }
        int j = warehouseRecordMapper.update(warehouseRecord);
        if (j >= 0 && ((updateResult + insertResult) == warehouseRecord.getList().size())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteGoodsRecordByGid(String ids) {
        String[] split = ids.split("-");
        String[] spl = split[0].split(",");
        Integer[] gids = new Integer[spl.length];
        int i = 0;
        for (String s : spl) {
            gids[i] = Integer.parseInt(s);
            i += 1;
        }
        int count = goodsRecordMapper.deleteGoodsRecordByGoodsId(gids,Integer.parseInt(split[1]));
        if (count == spl.length) {
            return true;
        } else {
            return false;
        }
    }

    public boolean wareHouse(WarehouseRecord warehouseRecord) {
        //期望添加insertSum条数据
        int insertSum = 0;
        //实际添加insertResult条数据
        int insertResult = 0;
        int j = warehouseRecordMapper.wareHouse(warehouseRecord);
        String warehouseNo = warehouseRecord.getWarehouseNo();
        Integer id = warehouseRecordMapper.selectByWarehouseNo(warehouseNo);
        Warehouse warehouse = new Warehouse();
        for (GoodsRecord goodsRecord : warehouseRecord.getList()) {
            goodsRecord.setWarehouseRecordID(id);
            String name = goodsRecord.getName();
            if (name != null && name != "") {
                insertSum += 1;
                insertResult = insertResult + goodsRecordMapper.wareHouse(goodsRecord);
                int goodsID = goodsDictionaryMapper.findGoodsIDByGoodsName(goodsRecord.getName());
                int count = warehouseMapper.selectByGoodsID(goodsID);
                if (count == 0) {
                    warehouse.setGoodsID(goodsID);
                    warehouse.setQuantity(Integer.parseInt(goodsRecord.getAmount()));
                    warehouseMapper.warehouse(warehouse);
                } else {
                    warehouseMapper.update(goodsID, goodsRecord.getAmount());
                }
            }
        }

        if (j == 1 && insertSum == insertResult) {
            return true;
        } else {
            return false;
        }
    }

    public List<GoodsRecord> inquiryGoodsRecordByID(Integer id) {
        return goodsRecordMapper.inquiryGoodsRecordByID(id);
    }

    public int inquiryOneYear() {
        return warehouseRecordMapper.inquiryOneYear();
    }

    public boolean deleteWarehouseRecordById(String ids) {
        String[] split = ids.split(",");
        Integer[] id = new Integer[split.length];
        int i = 0;
        for (String s : split) {
            id[i] = Integer.parseInt(s);
            i += 1;
        }
        int sum = 0;
        for (int j = 0; j < id.length; j++) {
            sum += goodsRecordMapper.inquiryGoodsRecordByID(id[j]).size();
        }
        int realSum = goodsRecordMapper.deleteGoodsRecordByWid(id);
        int count = warehouseRecordMapper.deleteWarehouseRecordById(id);
        if (count == split.length && sum == realSum) {
            return true;
        } else {
            return false;
        }
    }

    public int inquiryCount(String keywords, String[] beginDateScope) throws ParseException {
        String begin = beginDateScope[0];
        String end = beginDateScope[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = simpleDateFormat.parse(begin);
        Date endDate = simpleDateFormat.parse(end);
        return warehouseRecordMapper.inquiryCount(keywords, startDate, endDate);
    }
}
