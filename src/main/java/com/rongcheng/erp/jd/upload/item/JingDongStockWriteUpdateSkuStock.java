package com.rongcheng.erp.jd.upload.item;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.ware.StockWriteUpdateSkuStockRequest;
import com.jd.open.api.sdk.response.ware.StockWriteUpdateSkuStockResponse;
import net.sf.json.JSONObject;

import java.util.Set;

/**
 * 京东 设置sku库存
 * @author 赵滨
 */
public class JingDongStockWriteUpdateSkuStock {
    /**
     * 上传库存
     * @param serverUrl 接口URL
     * @param accessToken 访问令牌
     * @param appKey app钥匙
     * @param appSecret app秘钥
     * @param skuId 商品skuID
     * @param stockNum 库存数量
     * @return
     * @author 赵滨
     */
    public static Boolean updateSkuStock(String serverUrl, String accessToken, String appKey, String appSecret, Long skuId, Long stockNum){
        JdClient client = new DefaultJdClient(serverUrl,accessToken,appKey,appSecret);
        StockWriteUpdateSkuStockRequest request=new StockWriteUpdateSkuStockRequest();
        request.setSkuId(skuId);
        request.setStockNum(stockNum);
        request.setStoreId(null);

        StockWriteUpdateSkuStockResponse response= null;
        try {
            response = client.execute(request);
        } catch (JdException e) {
            e.printStackTrace();
        }

        //解析 返回信息
        JSONObject jsonObject = JSONObject.fromObject(response);
        Boolean success = (boolean)jsonObject.get("success");

        if (success == true) {
            return true;
        } else {
            Set keySet = jsonObject.keySet();
            for (Object key : keySet) {
                System.out.println("key:" + key + ",value:" + jsonObject.get(key));
            }
        }
        return false;
    }
}
