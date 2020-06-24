package com.huijian.rac.service;

import com.huijian.rac.bean.Discard;
import com.huijian.rac.mapper.DiscardDetailMapper;
import com.huijian.rac.mapper.DiscardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DiscardService {
    @Autowired
    DiscardMapper discardMapper;
    @Autowired
    DiscardDetailMapper discardDetailMapper;


    public Integer inquiryOneDay(String hospitalID) {
        return discardMapper.inquiryOneDay(hospitalID);
    }

    public List<Discard> getDiscardsByPage(Integer page, Integer size, String keywords, String[] beginDateScope) throws ParseException {
        Integer start = (page - 1) * size;
        String begin = beginDateScope[0];
        String end = beginDateScope[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = simpleDateFormat.parse(begin);
        Date endDate = simpleDateFormat.parse(end);
        return discardMapper.getDiscardsByPage(start, size, keywords, startDate, endDate);
    }

    public boolean addDiscard(Discard discard) {
        if (discardMapper.addDiscard(discard)==1){
            return true;
        }else {
            return false;
        }
    }

    public boolean addDiscardDetail(Discard discard) {
        if(discardDetailMapper.addDiscardDetail(discard.getList())==discard.getList().size()){
            return true;
        }else {
            return false;
        }
    }
}
