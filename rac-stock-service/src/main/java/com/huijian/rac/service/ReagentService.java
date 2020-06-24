package com.huijian.rac.service;

import com.huijian.rac.bean.Reagent;
import com.huijian.rac.mapper.ReagentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReagentService {
    @Autowired
    ReagentMapper reagentMapper;

    public List<Reagent> getReagentByPage(Integer page, Integer size, String keywords){
        Integer start = (page-1)*size;
        return reagentMapper.getReagentByPage(start,size,keywords);
    }

    public Boolean deleteById(String ids){
        String[] split = ids.split(",");
        return reagentMapper.deleteById(split)==split.length;
    }

}
