package com.huijian.rac.mapper;

import com.huijian.rac.bean.Menu;

import java.util.List;

/**
 * Created by sang on 2017/12/28.
 */
public interface MenuMapper {
    List<Menu> getAllMenu();

    List<Menu> getMenusByUserId(String id);

    List<Menu> menuTree();

    List<Long> getMenusByRid(Long rid);
}
