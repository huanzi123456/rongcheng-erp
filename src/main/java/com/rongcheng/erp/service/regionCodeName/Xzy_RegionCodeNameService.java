package com.rongcheng.erp.service.regionCodeName;
/**
 * 
 * @author 薛宗艳
 *
 */
public interface Xzy_RegionCodeNameService {
	/*
	 * 将全国地址信息的json字符串插入到regionCodeName表中
	 */
    void save(String str);
    /*
	 * 根据地区名查询地区编码
	 */
    Integer getCode(String area, String city, String province);
}
