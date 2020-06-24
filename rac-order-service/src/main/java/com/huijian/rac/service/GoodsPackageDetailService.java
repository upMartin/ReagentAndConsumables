package com.huijian.rac.service;

import com.huijian.rac.bean.GoodsPackageDetail;
import com.huijian.rac.bean.OrderDetail;
import com.huijian.rac.mapper.GoodsPackageDetailMapper;
import com.huijian.rac.mapper.UnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsPackageDetailService {
    @Autowired
    GoodsPackageDetailMapper goodsPackageDetailMapper;
    @Autowired
    UnitMapper unitMapper;

    public List<GoodsPackageDetail> inquiryGoodsPackageDetail(Integer packageID) {
        List<GoodsPackageDetail> list = goodsPackageDetailMapper.inquiryGoodsPackageDetail(packageID);
        for (GoodsPackageDetail goodsPackageDetail : list){
            goodsPackageDetail.setUnit(unitMapper.quiryByUnitID(goodsPackageDetail.getUnitID()));
        }
        return list;
    }
}
