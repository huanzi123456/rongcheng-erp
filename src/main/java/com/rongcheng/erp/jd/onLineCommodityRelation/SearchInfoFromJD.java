package com.rongcheng.erp.jd.onLineCommodityRelation;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.seller.ShopSafService.ShopJosResult;
import com.jd.open.api.sdk.domain.ware.SkuReadService.Page;
import com.jd.open.api.sdk.request.seller.VenderShopQueryRequest;
import com.jd.open.api.sdk.request.ware.SkuReadSearchSkuListRequest;
import com.jd.open.api.sdk.response.seller.VenderShopQueryResponse;
import com.jd.open.api.sdk.response.ware.SkuReadSearchSkuListResponse;
@Controller
public class SearchInfoFromJD {
	
	@Value("#{config['jdAppKeys']}")
	private String appKeys;
	
	@Value("#{config['jdAppSecrets']}")
	private String appSecrets;
	
	/**
	 * 从京东的接口中读取商品信息
	 * @param pageNo
	 * @param accessToken
	 * @param lastStartCreateTime
	 * @return
	 * @throws JdException 
	 */
    public Page searchCommonInfo(Integer pageNo,String accessToken,Date lastStartCreateTime) throws JdException{
    	String SERVER_URL = "https://api.jd.com/routerjson";
    	JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKeys,appSecrets); 
		SkuReadSearchSkuListRequest request=new SkuReadSearchSkuListRequest();
		request.setStartCreatedTime( lastStartCreateTime );
		request.setField( "status,saleAttrs,features,jdPrice,outerId,barCode,categoryId,imgTag,logo,skuName,stockNum,wareTitle,modified,created" );
		request.setPageNo(pageNo);
		SkuReadSearchSkuListResponse response = client.execute(request);;
		String code = response.getCode();//状态:0 正常
		Page page = null;
		if("0".equals(code)){
			page = response.getPage();//返回的数据 
		}else{
			String zhDesc = response.getZhDesc();//提示信息
			System.out.println(zhDesc);
		}		
		return page;
    }
    /**
     * 读取京东的店铺信息
     * @param accessToken
     * @return
     */
    public ShopJosResult searchShopInfo(String accessToken){
    	String SERVER_URL = "https://api.jd.com/routerjson";
    	JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKeys,appSecrets); 
    	VenderShopQueryRequest request=new VenderShopQueryRequest();
    	VenderShopQueryResponse response = null;
    	try {
			response=client.execute(request);
		} catch (JdException e) {
			e.printStackTrace();
		}
    	ShopJosResult shopJosResult = response.getShopJosResult();
    	return shopJosResult;
    }
}
