package com.rongcheng.erp.dao;

import java.util.List;

import com.rongcheng.erp.entity.ItemEspInfo;

public interface Jxb_ItemEspDAO {   
    int insertListSelective(List<ItemEspInfo> record);
}