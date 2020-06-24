package com.huijian.rac.mapper;

import com.huijian.rac.bean.Discard;

import java.util.Date;
import java.util.List;

public interface DiscardMapper {
    Integer inquiryOneDay(String hospitalID);

    List<Discard> getDiscardsByPage(Integer start, Integer size, String keywords, Date startDate, Date endDate);

    int addDiscard(Discard discard);
}
