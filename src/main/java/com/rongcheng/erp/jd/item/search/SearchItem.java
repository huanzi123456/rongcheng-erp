package com.rongcheng.erp.jd.item.search;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.ware.WareReadService.Page;
import com.jd.open.api.sdk.request.ware.WareReadSearchWare4ValidRequest;
import com.jd.open.api.sdk.response.ware.WareReadSearchWare4ValidResponse;

/**
 * 
 * 搜索有效商品
 * 
 * @author jxb
 *
 */
@Service("searchItem")
public class SearchItem {
	@Value("#{config['appKey']}")
	private String appKey;
	@Value("#{config['appSecret']}")
	private String appSecret;
	/**
	 * 搜索有效商品
	 * 
	 * @param accessToken
	 * @return
	 * @throws JdException 
	 */
	public Page search(String accessToken, Integer page, Date lastCreateTime, Integer... pageSize) throws JdException{
		String serverUrl = "https://api.jd.com/routerjson";
		JdClient client = new DefaultJdClient(serverUrl, accessToken, appKey, appSecret);
		WareReadSearchWare4ValidRequest request = new WareReadSearchWare4ValidRequest();
		request.setPageNo(page);
		if (pageSize.length == 0) {
			request.setPageSize(100);
		} else {
			request.setPageSize(pageSize[0]);
		}
		request.setField("logo,marketPrice,costPrice,jdPrice,shopId,weight,width,height,length");
		request.setStartCreatedTime(lastCreateTime);
		WareReadSearchWare4ValidResponse response = client.execute(request);
		Page pageResult = response.getPage();
		return pageResult;
	}
}
