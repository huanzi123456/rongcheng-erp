package com.rongcheng.erp.service.onlineCommodityRelation;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.seller.ShopSafService.ShopJosResult;
import com.jd.open.api.sdk.domain.ware.SkuReadService.Page;
import com.jd.open.api.sdk.domain.ware.SkuReadService.Prop;
import com.jd.open.api.sdk.domain.ware.SkuReadService.Sku;
import com.jd.open.api.sdk.request.seller.VenderShopQueryRequest;
import com.jd.open.api.sdk.request.ware.SkuReadSearchSkuListRequest;
import com.jd.open.api.sdk.response.seller.VenderShopQueryResponse;
import com.jd.open.api.sdk.response.ware.SkuReadSearchSkuListResponse;
import com.rongcheng.erp.dao.Xzy_OnlineCommodityRelationDao;
import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.PlatformErpLink;
import com.rongcheng.erp.entity.ShopInfo;
import com.rongcheng.erp.entity.UserInfo;
/**
 * 读取京东的商品信息及店铺信息
 * @author 薛宗艳
 *
 */
@Service
public class Xzy_SaveDataFromJDServiceImpl implements Xzy_SaveDataFromJDService{
	@Value("#{config['jdAccessTokens']}")
	private String jdAccessTokens;
	
	@Value("#{config['jdAppKeys']}")
	private String jdAppKeys;
	
	@Value("#{config['jdAppSecrets']}")
	private String jdAppSecrets;
	
	@Resource
    Xzy_OnlineCommodityRelationDao dao ;	
	
	//@PostConstruct
	@Override
	public void save(HttpSession session) throws JdException {
		//String jdAccessTokens = ((AuthorityAccess) (session.getAttribute("authorityAccess"))).getAccessToken();
		//查询京东的店铺信息
		ShopJosResult shop = searchShopInfo(jdAppKeys, jdAppSecrets, jdAccessTokens);
		String platformShopId = shop.getShopId().toString();//店铺编号                   
    	String platformShopName = shop.getShopName();       //店铺名称
    	
    	//查询商品所属的店铺id
    	ShopInfo shops = new ShopInfo();
		shops.setPlatformId(new BigInteger(3+""));
		shops.setPlatformShopId(platformShopId);
		BigInteger shop_id = dao.seleIdFromShopInfo(shops);//商品所属的店铺id
    	
		//查询platform_erp_link表中的数据的最大创建时间
		BigInteger ownerId = ((UserInfo)session.getAttribute("user")).getOwnerId();
    	PlatformErpLink plat = new PlatformErpLink();
    	plat.setOwnerId(ownerId);
    	plat.setPlatformId(new BigInteger(3+""));
    	//Date lastCreatTime = dao.seleLastCreatTime(plat);//创建时间
    	/////////////////////
    	Date lastCreatTime = null;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
    		lastCreatTime = sdf.parse("2012-12-12 12:12:12");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	///////////////////////
		Integer pageNo = 1;//页码
    	Integer pageSize = 100;//每页条数
    	//查询京东的商品信息
      	Page jdResult = searchCommonInfo(pageSize, pageNo, jdAppKeys, jdAppSecrets, jdAccessTokens, lastCreatTime);
      	saveData(jdResult,platformShopId,platformShopName,ownerId,shop_id);
      	Long count = jdResult.getTotalItem();//京东可以返回的数据的记录数
      	if(count > 0){
    		Long num;//页数
    		if(count%pageSize == 0){
    			num = count/pageSize;
    		}else{
    			num = (count/pageSize)+1;
    		}
    		if(num == 1){
    			return;
    		}
    		for(int i=1;i<num;i++){//i<num
    			pageNo++;
    			jdResult = searchCommonInfo(pageSize, pageNo, jdAppKeys, jdAppSecrets, jdAccessTokens, lastCreatTime);
    	        saveData(jdResult,platformShopId,platformShopName,ownerId,shop_id);	
      	    }
    	}	     	  	
	}
	/**
	 * 将京东的返回的商品及店铺信息进行处理
	 * @param jdResult         :京东返回的商品信息
	 * @param platformShopId   :平台店铺id
	 * @param platformShopName :平台店铺名称
	 * @param ownerId          :数据表的拥有者
	 * @param shop_id          :商品所属店铺id
	 */
	public void saveData(Page jdResult,String platformShopId,String platformShopName,BigInteger ownerId,BigInteger shop_id){
    	List<Sku> list = jdResult.getData();
    	for(Sku sku : list){     		   		
    		BigInteger platformItemSku = new BigInteger(sku.getSkuId()+"");//平台（来源）商品编码
    		String wareTitle = sku.getWareTitle();//平台商品名称		
    		Long WareId = sku.getWareId();  //商品ID
    		String platformUserImg = "https://img10.360buyimg.com/imgzone/s70x70_"+sku.getLogo();//平台商品主图
    		String platformUserCode = sku.getOuterId();//平台商品卖家自定义编码   没有时是空""
    		String platformItemState = sku.getStatus().toString();//平台商品状态 
    		BigInteger platformId = new BigInteger(3+"");//来源（平台）编码 京东是3 
    		Date date = sku.getCreated();   		  
    	    Timestamp gmtCreate = new Timestamp(date.getTime());//记录创建时间
			//根据skuid和platform_id 查询erp商品id
			ItemCommonInfo item = new ItemCommonInfo();
			item.setPlatformItemSku(new BigInteger(sku.getSkuId()+""));
			item.setPlatformId(new BigInteger(3+""));
			BigInteger itemId = dao.seleItemCommonInfoId(item);//erp商品ID
			if(itemId == null){
				System.out.println("skuid:"+platformItemSku);
			}
			
//			System.out.println("erp商品ID  1:"+itemId);
//			System.out.println("来源(平台)编码  2:"+platformId);
//			System.out.println("商品所属店铺id 3:"+shop_id);
//			System.out.println("平台店铺id	 4:"+platformShopId);
//			System.out.println("店铺名称	 5:"+platformShopName);
//			System.out.println("平台(来源)商品编码  6:"+platformItemSku);
//			System.out.println("平台商品名称 7:"+wareTitle);
//			System.out.println("平台商品id :"+WareId);
//			System.out.println("平台商品颜色 8:");
//			System.out.println("平台商品尺码 9:");
//			System.out.println("平台商品状态  10:"+platformItemState);
//			System.out.println("平台商品卖家自定义编码 11:"+platformUserCode);
//			System.out.println("平台商品主图 12:"+platformUserImg);
//			System.out.println("ownerId 13:"+ownerId);
//			System.out.println("记录创建时间 14:"+gmtCreate);			
			
			PlatformErpLink link = new PlatformErpLink();
			link.setItemId(itemId);
			link.setPlatformId(platformId);
			link.setShopId(shop_id);
			link.setPlatformShopId(platformShopId);
			link.setPlatformShopName(platformShopName);
			link.setPlatformItemSku(platformItemSku);
			link.setPlatformItemName(wareTitle);
			link.setPlatformItemWareid(new BigInteger(WareId+""));
			link.setPlatformItemState(platformItemState);
			link.setPlatformUserCode(platformUserCode);
			link.setPlatformUserImg(platformUserImg);
			link.setOwnerId(ownerId);
			link.setGmtCreate(gmtCreate);			
			/*
			 * saleAttrs:销售属性 包含:
			 *  1.attrId         :属性ID
			 *  2.attrValueAlias :属性值的描述或者别名,与attrValues一一对应
			 *  3.attrValues     :属性值ID
			 *  注:saleAttrs的结构:
			 *     长度为1:saleAttrs:[
			 *                       {"attrId":"","attrValueAlias":[""],"attrValues":[""]}
			 *                      ]
			 *     长度为2:saleAttrs:[
			 *                      {"attrId":"","attrValueAlias":[""],"attrValues":[""]},
			 *                      {"attrId":"","attrValueAlias":[""],"attrValues":[""]}
			 *                      ]
			 *     其中,第一个大括号中描述的可能是颜色或尺码,不确定;将第一个大括号中的数据保存到platform_item_color字段中,
			 *   将第二个大括号中的数据保存到platform_item_size中.                 
			 */
			Set<Prop> saleAttr = sku.getSaleAttrs();
			if(saleAttr != null){
				List<Prop> saleAttrs = new ArrayList<>(saleAttr);
				Integer size = saleAttrs.size();
				Prop p1 = null;
				Prop p2 = null;
				if(size == 1){
					p1 = saleAttrs.get(0);
				}else if(size == 2){
					p1 = saleAttrs.get(0);
					p2 = saleAttrs.get(1);
				}
				if(p1 != null && p2 == null){
					String attrId1 = "";
					String attrValueAlias1 = "";
					String attrValue1 = "";
					attrId1 = p1.getAttrId();
					String[] attr2 = p1.getAttrValueAlias();
					for(int i=0;i<attr2.length;i++){
						attrValueAlias1+=attr2[i];
					}
					String[] att1 = p1.getAttrValues();
					for(int i=0;i<att1.length;i++){
						attrValue1+=att1[i];
					}
					link.setPlatformItemAttrid1(attrId1);
					link.setPlatformItemAttrvaluealias1(attrValueAlias1);
					link.setPlatformItemAttrvalue1(attrValue1);
					link.setPlatformItemAttrid2("");
					link.setPlatformItemAttrvaluealias2("");
					link.setPlatformItemAttrvalue2("");
					dao.savePlatformErpLink(link);
//					System.out.println("attrId1:"+attrId1);
//					System.out.println("attrValueAlias1:"+attrValueAlias1);
//					System.out.println("attrValue1:"+attrValue1);				
	            }else if(p1 != null && p2 != null){
	            	String attrId1 = p1.getAttrId();
					String attrValueAlias1 = "";
					String attrValue1 = "";
					String[] attr1 = p1.getAttrValueAlias();
					for(int i=0;i<attr1.length;i++){
						attrValueAlias1+=attr1[i];
					}
					String[] att1 = p1.getAttrValues();
					for(int i=0;i<att1.length;i++){
						attrValue1+=att1[i];
					}
					String attrId2 = p2.getAttrId();
					String attrValueAlias2 = "";
					String attrValue2 = "";
					String[] attr2 = p2.getAttrValueAlias();
					for(int i=0;i<attr2.length;i++){
						attrValueAlias2+=attr2[i];
					}
					String[] att2 = p2.getAttrValues();
					for(int i=0;i<att2.length;i++){
						attrValue2+=att2[i];
					}
					link.setPlatformItemAttrid1(attrId1);
					link.setPlatformItemAttrvaluealias1(attrValueAlias1);
					link.setPlatformItemAttrvalue1(attrValue1);
					link.setPlatformItemAttrid2(attrId2);
					link.setPlatformItemAttrvaluealias2(attrValueAlias2);
					link.setPlatformItemAttrvalue2(attrValue2);
					dao.savePlatformErpLink(link);
//					System.out.println("attrId1:"+attrId1);
//					System.out.println("attrValueAlias1:"+attrValueAlias1);
//					System.out.println("attrValue1:"+attrValue1);
//					System.out.println("attrId2:"+attrId2);
//					System.out.println("attrValueAlias2:"+attrValueAlias2);
//					System.out.println("attrValue2:"+attrValue2);
	            }else if(p1 == null && p2 == null){
	            	dao.savePlatformErpLink(link);
	            }
			}						
    	}   
    }
	/**
	 * 读取京东的商品信息
	 * @param pageSize       :每页条数
	 * @param pageNo         :页码
	 * @param appKeys        :
	 * @param appSecrets     :
	 * @param accessToken    :
	 * @param lastCreatTime  :创建时间
	 * @return
	 * @throws JdException
	 */
	public Page searchCommonInfo(Integer pageSize,Integer pageNo,String appKeys,String appSecrets,String accessToken,Date lastCreatTime) throws JdException{
    	String SERVER_URL = "https://api.jd.com/routerjson";
    	JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKeys,appSecrets); 
		SkuReadSearchSkuListRequest request=new SkuReadSearchSkuListRequest();
		request.setStartCreatedTime( lastCreatTime );
		request.setField( "status,saleAttrs,features,jdPrice,outerId,barCode,categoryId,imgTag,logo,skuName,stockNum,wareTitle,modified,created" );
		request.setPageNo(pageNo);
		request.setPageSize(pageSize);
		SkuReadSearchSkuListResponse response = client.execute(request);;
		String code = response.getCode();
		Page page = null;
		if("0".equals(code)){
			page = response.getPage(); 
		}else{
			String zhDesc = response.getZhDesc();
			System.out.println(zhDesc);
		}		
		return page;
    }
	/**
	 * 读取京东的店铺信息
	 * @param appKeys      :
	 * @param appSecrets   :
	 * @param accessToken  :
	 * @return
	 */
    public ShopJosResult searchShopInfo(String appKeys,String appSecrets,String accessToken){
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