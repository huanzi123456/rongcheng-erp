(function(){
	/*
	* 云仓订单分发 
	* 选择商品弹出框
	* select_commodity_box  弹出框
	* select_commodity_delbtn    右上角叉号
	* select_commodity_bc 保存按钮
	* select_commodity_qx  取消按钮
	* sp_alert    点击按钮
	*/
	var select_commodity_box=$('.select_commodity_box'),
		select_commodity_delbtn=$('.select_commodity_delbtn'),
		select_commodity_bc=$('.select_commodity_bc'),
		select_commodity_qx=$('.select_commodity_qx'),
		sp_alert=$('.sp_alert');
	sp_alert.click(function(){
		select_commodity_box.css("display","block");
	});	
	select_commodity_delbtn.click(function(){
		select_commodity_box.css("display","none");
	});	
	select_commodity_bc.click(function(){
		select_commodity_box.css("display","none");
	});	
	select_commodity_qx.click(function(){
		select_commodity_box.css("display","none");
	});	

	/*
	* 发货商品匹配弹出框
	* goods_matching_box 弹出框
	* goods_matching_delbtn    右上角叉号
	* goods_matching_bc 保存按钮
	* goods_matching_qx  取消按钮
	* dp_alert    点击按钮
	*/
	var goods_matching_box=$('.goods_matching_box'),
		goods_matching_delbtn=$('.goods_matching_delbtn'),
		goods_matching_bc=$('.goods_matching_bc'),
		goods_matching_qx=$('.goods_matching_qx'),
		dp_alert=$('.dp_alert');
	dp_alert.click(function(){
		goods_matching_box.css("display","block");
	});	
	goods_matching_delbtn.click(function(){
		goods_matching_box.css("display","none");
	});	
	goods_matching_bc.click(function(){
		goods_matching_box.css("display","none");
	});	
	goods_matching_qx.click(function(){
		goods_matching_box.css("display","none");
	});	

	/*
	* 发货商品匹配弹出框 下拉效果
	* select_goods_btn
	* select_goods_bc
	* select_goods_content
	*/

	var select_goods_btn=$('.select_goods_btn'),
		select_goods_bc=$('.select_goods_bc'),
		select_goods_content=$('.select_goods_content');
	select_goods_btn.click(function(){
		if (select_goods_content.css("display")==='none') {
			select_goods_content.css("display","block");
		}else{
			select_goods_content.css("display","none");
		}
	});
	select_goods_bc.click(function(){
		select_goods_content.css("display","none");
	});	


	/*
	* 覆盖区域规则设置弹出框
	* regional_rules_box 弹出框
	* regional_rules_delbtn    右上角叉号
	* regional_rules_bc 保存按钮
	* regional_rules_qx  取消按钮
	* dq_alert    点击按钮
	*/
	var regional_rules_box=$('.regional_rules_box'),
		regional_rules_delbtn=$('.regional_rules_delbtn'),
		regional_rules_bc=$('.regional_rules_bc'),
		regional_rules_qx=$('.regional_rules_qx'),
		dq_alert=$('.dq_alert');
		
	dq_alert.click(function(){
		regional_rules_box.css("display","block");
	});	
	regional_rules_delbtn.click(function(){
		regional_rules_box.css("display","none");
	});	
	regional_rules_bc.click(function(){
		regional_rules_box.css("display","none");
	});	
	regional_rules_qx.click(function(){
		regional_rules_box.css("display","none");
	});	



	/*
	* 覆盖区域规则设置弹出框全选，全不选
	* regional_rules_check
	* regional_rules_notCheck
	* regional_rules_inputCheck
	*/
	var regional_rules_check=$('.regional_rules_check'),
		regional_rules_notCheck=$('.regional_rules_notCheck'),
		regional_rules_inputCheck=$('.regional_rules_content li input[type="checkbox"]');

	regional_rules_check.click(function(){
		regional_rules_inputCheck.prop('checked',true)
	})
	regional_rules_notCheck.click(function(){
		regional_rules_inputCheck.prop('checked',false)
	})
})()