package com.huijian.rac.service;

import com.huijian.rac.bean.Depart;
import com.huijian.rac.mapper.DepartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartService {
    @Autowired
    DepartMapper departMapper;

    public List<Depart> getDeparts(Integer page, Integer size, String keywords, String departID) {
        Integer start = (page-1)*size;
        return departMapper.getDepartsByPageSize(start, size, keywords, departID);

    }

    public boolean addDepart(Depart depart) {
        if(departMapper.addDepart(depart)>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean updateDepart(Depart depart) {
        if(departMapper.updateDepart(depart)>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean delete(String ids) {
        String[] split = ids.split(",");
        if(departMapper.delete(split)==split.length){
            return true;
        }else {
            return false;
        }
    }
}
