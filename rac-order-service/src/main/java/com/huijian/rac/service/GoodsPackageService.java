package com.huijian.rac.service;

import com.huijian.rac.bean.GoodsPackage;
import com.huijian.rac.mapper.GoodsPackageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsPackageService {
    @Autowired
    GoodsPackageMapper goodsPackageMapper;

    public List<GoodsPackage> inquiryPackage() {
        return goodsPackageMapper.inquiryGoodsPackage();
    }
}
