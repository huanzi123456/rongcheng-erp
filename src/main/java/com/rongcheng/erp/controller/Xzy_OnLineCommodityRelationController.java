package com.rongcheng.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/onLineCommodity")
public class Xzy_OnLineCommodityRelationController {
	@RequestMapping("/commodityRelation.do")
    public String toCommodityRelation(){
    	return "settings/commodityRelation";
    }
}
