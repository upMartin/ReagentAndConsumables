package com.huijian.rac.mapper;

import com.huijian.rac.bean.DiscardDetail;

import java.util.List;

public interface DiscardDetailMapper {
    int addDiscardDetail(List<DiscardDetail> list);

    List<DiscardDetail> inquiryByID(String discardNo);
}
