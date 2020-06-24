package com.huijian.rac.service;

import com.huijian.rac.bean.GoodsRecord;
import com.huijian.rac.bean.LeaveStockListDetail;
import com.huijian.rac.bean.OutboundRecord;
import com.huijian.rac.bean.WarehouseRecord;
import com.huijian.rac.mapper.GoodsDictionaryMapper;
import com.huijian.rac.mapper.LeaveStockListDetailMapper;
import com.huijian.rac.mapper.OutboundRecordMapper;
import com.huijian.rac.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutboundRecordService {
    @Autowired
    private OutboundRecordMapper outboundRecordMapper;
    @Autowired
    private LeaveStockListDetailMapper leaveStockListDetailMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private GoodsDictionaryMapper goodsDictionaryMapper;
    @Autowired
    private OrderStateService orderStateService;

    public List<OutboundRecord> getOutboundRecordByPage(Integer page, Integer size, String keywords, String[] beginDateScope) throws ParseException {
        Integer start = (page - 1) * size;
        String begin = beginDateScope[0];
        String end = beginDateScope[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = simpleDateFormat.parse(begin);
        Date endDate = simpleDateFormat.parse(end);
        List<OutboundRecord> list = outboundRecordMapper.getOutboundRecordByPage(start, size, keywords, startDate, endDate);
        list = list.stream().map(e->{
            Integer orderState = Integer.parseInt(orderStateService.inquiryOrderState(e.getOrderNo()));
            e.setOrderState(orderState);
            return e;
        }).collect(Collectors.toList());
        return list;
    }


    public List<LeaveStockListDetail> inquiryLeaveStockListDetail(Integer leaveStockNo) {
        List<LeaveStockListDetail> list = leaveStockListDetailMapper.inquiryLeaveStockListDetail(leaveStockNo);
        list = list.stream().map(e -> {
            int goodsID = goodsDictionaryMapper.findGoodsIDByGoodsName(e.getName());
            if(warehouseMapper.findWarehouseByGoodsID(goodsID)!=0)
            e.setWarehouse(warehouseMapper.findWarehouseByGoodsID(goodsID));
            return e;
        }).collect(Collectors.toList());
        return list;
    }

    public int inquiryOneYear() {
        return outboundRecordMapper.inquiryOneYear();
    }

    public boolean wareHouse(OutboundRecord outboundRecord) {
        //期望添加insertSum条数据
        int insertSum = 0;
        //实际添加insertResult条数据
        int insertResult = 0;
        int j = outboundRecordMapper.wareHouse(outboundRecord);
        Integer leaveStockNo = outboundRecord.getLeaveStockNo();
        for (LeaveStockListDetail leaveStockListDetail : outboundRecord.getList()) {
            leaveStockListDetail.setLeaveStockNo(leaveStockNo);
            String name = leaveStockListDetail.getName();
            if (name != null && name != "") {
                insertSum += 1;
                insertResult += leaveStockListDetailMapper.wareHouse(leaveStockListDetail);
            }
        }
        if (j == 1 && insertSum == insertResult) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(OutboundRecord outboundRecord) {
        int updateResult = 0;
        //实际添加insertResult条数据
        int insertResult = 0;
        for (LeaveStockListDetail leaveStockListDetail : outboundRecord.getList()) {
            if (leaveStockListDetail.getLeaveStockNo() != null) {
                updateResult = updateResult + leaveStockListDetailMapper.update(leaveStockListDetail);
            } else {
                leaveStockListDetail.setLeaveStockNo(outboundRecord.getLeaveStockNo());
                String name = leaveStockListDetail.getName();
                if (name != null && name != "") {
                    insertResult = insertResult + leaveStockListDetailMapper.wareHouse(leaveStockListDetail);
                }
            }
        }
        int j = outboundRecordMapper.update(outboundRecord);
        return true;
    }

    public int inquiryWarehouse(String name) {
        //由于GID和GoodsID不对应,只能根据物资名字来查找对应的GoodsID
        int goodsID = goodsDictionaryMapper.findGoodsIDByGoodsName(name);
        return warehouseMapper.findWarehouseByGoodsID(goodsID);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean leaveStock(List<LeaveStockListDetail> list) {
        if(list.stream().map(e -> {
                //由于GID和GoodsID不对应,所以只能手动设置
                    int count = warehouseMapper.leaveStock(e);
                    if (count==0){
                            throw new RuntimeException("全部更新操作回滚");
                    }else{
                        return count;
                    }
                }).allMatch(i -> i == 1)){
                return true;
        }else{
                return false;
        }
    }

    public boolean deleteleaveStockListDetailByGoodsIDAndNo(String ids) {
        String[] spli = ids.split("-");
        String[] split = spli[0].split(",");
        Integer[] id = new Integer[split.length];
        int i = 0;
        for (String s : split) {
            id[i] = Integer.parseInt(s);
            i += 1;
        }
        return leaveStockListDetailMapper.deleteLeaveStockListDetailByGoodsIDAndNo(id,spli[1])==id.length;
    }
}
