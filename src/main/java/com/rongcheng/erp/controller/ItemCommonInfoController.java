package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.service.ItemCommonInfo.ItemCommonInfoService;
import com.rongcheng.erp.utils.JsonResult;

@Controller
public class ItemCommonInfoController{
	//分页相关（每页多少条）
//	@Value("#{sysconfig['rows']}")
	private int rows=20;
	@Resource
	private ItemCommonInfoService service;
	@RequestMapping("/storeMessage.do")
	public String saveStoreMessage(){
		return "storeMessage";
	}
	@RequestMapping("/settings.do")
	public String toSettings(){
		return "settings";
	}
	
		
//		@RequestMapping("/settings/load_modify_storeMessage.do")
//		@ResponseBody
//		public JsonResult loadStoreMessage(){
//		List<ItemCommonInfo> list=service.findItemCommonInfoAll();
//			return new JsonResult(list);
//		}
	
	
		@ResponseBody
		@RequestMapping("/settings/modify_storeMessage.do")
		public Object modifyStoreMessage(BigInteger id, String shortName){
			int row=service.modifyItemCommonInfo(id, shortName);
			return new JsonResult(row);	
		}
		
		//根据页码检索商品信息
		@RequestMapping("/load_modify_storeMessage.do")
		@ResponseBody
		public JsonResult loadItemCommonInfo(String page) {
			return loadItemCommonInfos(page);
		}
		
		//根据页码检索
		public JsonResult loadItemCommonInfos(String page) {
			int maxPage = (int) Math.ceil(new Double(service.findItemCommonInfoCount())/rows);
			if(maxPage == 0){
				return new JsonResult();
			}
			/*if(new Integer(page)>max_page){
				page = max_page+"";
			}*/
			
			//System.out.println(max_page);..............
			
			
			//Iterator<LeaveMessage> it = LeaveMessages.iterator();
			/*while (it.hasNext()) {
				LeaveMessage gb = (LeaveMessage) it.next();
				System.out.println("条数"+gb.toString());
			}*/
			List<ItemCommonInfo> itemCommonInfos=service.findItemCommonInfoByPage(new Integer(page)*rows-rows,rows);
			JsonResult jr = new JsonResult(itemCommonInfos);
			jr.setMessage(maxPage+"");
			return jr;
		}
		
		@ResponseBody
		@RequestMapping("/settings/modify_storeMessages.do")
		public Object modifyStoreMessages(BigInteger[] id1, String[] sort){
			System.out.println(id1[0]);	
			System.out.println(sort[0]);
			service.modifyItemCommonInfos(id1, sort);
			return new JsonResult();	
		}
	}

