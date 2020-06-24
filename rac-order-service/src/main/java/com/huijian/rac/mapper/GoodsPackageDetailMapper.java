package com.huijian.rac.mapper;

import com.huijian.rac.bean.GoodsPackageDetail;

import java.util.List;

public interface GoodsPackageDetailMapper {
    List<GoodsPackageDetail> inquiryGoodsPackageDetail(Integer packageID);
}
