package com.rongcheng.erp.service.regionCodeName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.jd.open.api.sdk.internal.JSON.JSON;
import com.rongcheng.erp.dao.Xzy_RegionCodeNameDao;
/**
 * 
 * @author 薛宗艳
 *
 */
@Service
public class Xzy_RegionCodeNameServiceImpl implements Xzy_RegionCodeNameService{
    @Resource
    Xzy_RegionCodeNameDao dao;
    /**
     * 将全国地址信息的json字符串插入到regionCodeName表中
     */
	public void save(String str) {
    	String strs= "{\"110000\":\"北京市\"}";
		Map<String, Object> maps = (Map)JSON.parse(strs); 
		Map<String, Object> obj = new HashMap<String, Object>();
    	obj.put("code",maps);
    	dao.insertRegionCodeName(obj);		
	}
	/**
	 * 根据地区名查询地区编码
	 */
	public Integer getCode(String area, String city, String province) {
		String areaName = "朝阳";//旗县区名
    	String cityName = "北京市";//市名
    	//String provinceName = "辽宁";//省名
    	Integer thisCode = null;//该地区的编码
    	/*
    	 * 按地区名模糊查询
    	 */
    	List<Integer> areaCode = dao.selectRegionCode(areaName);
    	/*
    	 * 如果用旗县区查询的集合lists的长度大于1,在用市名查询
    	 */
    	List<Integer> cityCode;
    	if(areaCode.size()>1){
    		cityCode = dao.selectRegionCode(cityName);
    		for(Integer areaCodes : areaCode){//旗县区
    			for(Integer cityCodes : cityCode){//市    				
    				if((areaCodes/100) == (cityCodes/100)){
    					if(!(areaCodes.equals(cityCodes))){
    						thisCode = areaCodes;   						
    					}    					
    				}
        		}
    		}	
    	}else{
    		for(Integer areaCodes : areaCode){
    			thisCode = areaCodes;
    		}
    	}
		return thisCode;
	}
}
