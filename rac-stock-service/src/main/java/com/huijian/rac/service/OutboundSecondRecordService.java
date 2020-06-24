package com.huijian.rac.service;

import com.huijian.rac.bean.LeaveStockListDetail;
import com.huijian.rac.bean.OutboundRecord;
import com.huijian.rac.bean.OutboundSecondRecord;
import com.huijian.rac.bean.SecondLeaveStockListDetail;
import com.huijian.rac.mapper.GoodsDictionaryMapper;
import com.huijian.rac.mapper.OutboundSecondRecordMapper;
import com.huijian.rac.mapper.SecondLeaveStockListDetailMapper;
import com.huijian.rac.mapper.WarehouseSecondMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutboundSecondRecordService {
    @Autowired
    OutboundSecondRecordMapper outboundSecondRecordMapper;
    @Autowired
    SecondLeaveStockListDetailMapper secondLeaveStockListDetailMapper;
    @Autowired
    GoodsDictionaryMapper goodsDictionaryMapper;
    @Autowired
    WarehouseSecondMapper warehouseSecondMapper;

    public List<OutboundSecondRecord> getOutboundSecondRecordByPage(
            Integer page, Integer size, String keywords, String[] beginDateScope,String hospitalID) throws ParseException {
        Integer start = (page - 1) * size;
        String begin = beginDateScope[0];
        String end = beginDateScope[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = simpleDateFormat.parse(begin);
        Date endDate = simpleDateFormat.parse(end);
        List<OutboundSecondRecord> list = outboundSecondRecordMapper.getOutboundSecondRecordByPage(
                start, size, keywords, startDate, endDate,hospitalID);
        return list;
    }

    public boolean deleteSecondleaveStockListDetail(String ids) {
        String[] spl = ids.split("-");
        String[] split = spl[0].split(",");
        Integer[] id = new Integer[split.length];
        int i = 0;
        for (String s : split) {
            id[i] = Integer.parseInt(s);
            i += 1;
        }
        int count = secondLeaveStockListDetailMapper.deleteGoodsRecordByGid(id, spl[1],Integer.parseInt(spl[2]));
        if (count == split.length) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteOutboundSecondRecord(String ids) {
        int sum = 0;
        String[] id = ids.split("-");
        OutboundSecondRecord outboundSecondRecord = new OutboundSecondRecord();
        outboundSecondRecord.setLeaveStockNo(Integer.parseInt(id[0]));
        outboundSecondRecord.setHospitalID(id[1]);
        List<SecondLeaveStockListDetail> list =
        secondLeaveStockListDetailMapper.inquirySecondLeaveStockListDetail(outboundSecondRecord);
        sum = list.size();
        int realSum = secondLeaveStockListDetailMapper.deletesecondLeaveStockListDetail(Integer.parseInt(id[0]),id[1]);

        int count = outboundSecondRecordMapper.deleteOutboundSecondRecord(Integer.parseInt(id[0]),id[1]);
        if (count == 1 && sum == realSum) {
            return true;
        } else {
            return false;
        }
    }

    public List<SecondLeaveStockListDetail> inquirySecondLeaveStockListDetail(OutboundSecondRecord outboundSecondRecord) {
        List<SecondLeaveStockListDetail> list = secondLeaveStockListDetailMapper.inquirySecondLeaveStockListDetail(outboundSecondRecord);
        list = list.stream().map(e -> {
            int sum = warehouseSecondMapper.findWarehouse(e.getGoodsID(), outboundSecondRecord.getHospitalID());
            if(sum!=0)
                e.setWarehouse(sum);
            return e;
        }).collect(Collectors.toList());
        return list;
    }

    public int inquiryOneYear(String hospitalID) {
        return outboundSecondRecordMapper.inquiryOneYear(hospitalID);
    }

    public boolean wareHouse(OutboundSecondRecord outboundSecondRecord) {
        //期望添加insertSum条数据
        int insertSum = 0;
        //实际添加insertResult条数据
        int insertResult = 0;
        int j = outboundSecondRecordMapper.wareHouse(outboundSecondRecord);
        Integer leaveStockNo = outboundSecondRecord.getLeaveStockNo();
        for (SecondLeaveStockListDetail secondLeaveStockListDetail : outboundSecondRecord.getList()) {
            secondLeaveStockListDetail.setLeaveStockNo(leaveStockNo);
            String name = secondLeaveStockListDetail.getName();
            if (name != null && name != "") {
                insertSum += 1;
                insertResult += secondLeaveStockListDetailMapper.wareHouse(secondLeaveStockListDetail);
            }
        }
        if (j == 1 && insertSum == insertResult) {
            return true;
        } else {
            return false;
        }
    }

    public int inquiryWarehouse(int goodsID, String hospitalID) {
        return warehouseSecondMapper.findWarehouse(goodsID,hospitalID);
    }

    public boolean update(OutboundSecondRecord outboundSecondRecord) {
        int updateResult = 0;
        //实际添加insertResult条数据
        int insertResult = 0;
        for (SecondLeaveStockListDetail secondLeaveStockListDetail : outboundSecondRecord.getList()) {
            if (secondLeaveStockListDetail.getLeaveStockNo() != null) {
                updateResult = updateResult + secondLeaveStockListDetailMapper.update(secondLeaveStockListDetail);
            } else {
                secondLeaveStockListDetail.setLeaveStockNo(outboundSecondRecord.getLeaveStockNo());
                String name = secondLeaveStockListDetail.getName();
                if (name != null && name != "") {
                    insertResult = insertResult + secondLeaveStockListDetailMapper.wareHouse(secondLeaveStockListDetail);
                }
            }
        }
        int j = outboundSecondRecordMapper.update(outboundSecondRecord);
        return true;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean leaveStock(List<SecondLeaveStockListDetail> list) {
        if(list.stream().map(e -> {
            //由于GID和GoodsID不对应,所以只能手动设置
            int count = warehouseSecondMapper.leaveStock(e);
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


    public int updateLeaveStockState(String leaveStockState, String hospitalID, Integer leaveStockNo) {
        return outboundSecondRecordMapper.updateLeaveStockState(leaveStockState,hospitalID,leaveStockNo);
    }
}
