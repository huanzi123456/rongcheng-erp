package com.rongcheng.erp.dao;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author 薛宗艳
 *
 */
public interface Xzy_RegionCodeNameDao {
	
	/*
	 * 将地区及地区编码插入数据库
	 */
    void insertRegionCodeName(Map<String,Object> map);
    /*
     * 根据地区名查询地区编码
     */
    List<Integer> selectRegionCode(String regionName);
}
