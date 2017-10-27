(function(){

	/*
	*new_location_box 库位管理新建或者修改按钮弹出的灰色框
	*location_delbtn 关闭按钮
	*amend_btn 修改按钮 这个是多个的
	*add_btn   新建按钮
	*kw_qx    取消按钮
	*save_kw  保存按钮
	*/

	var new_location_box=$(".new_location_box"),
		location_delbtn=$(".location_delbtn"),
		amend_btn=$(".amend_btn"),
		add_btn=$(".add_btn"),
		kw_qx=$(".kw_qx"),
		save_kw=$(".save_kw");

	add_btn.click(function(){
		new_location_box.css("display","block");
	});

	amend_btn.click(function(){
		new_location_box.css("display","block");
	});

	location_delbtn.click(function(){
		new_location_box.css("display","none");
	});

	kw_qx.click(function(){
		new_location_box.css("display","none");
	});

	save_kw.click(function(){
		new_location_box.css("display","none");
	});


	/*
	*shopping_add_box 添加商品弹出的灰色框
	*shopping_delbtn 关闭按钮
	*button_tj 提交按钮
	*add_shopping  添加商品
	*/

	var shopping_add_box=$(".shopping_add_box"),
		shopping_delbtn=$(".shopping_delbtn"),
		button_tj=$(".button_tj"),
		add_shopping=$(".add_shopping");

	add_shopping.click(function(){
		shopping_add_box.css("display","block");
	});

	shopping_delbtn.click(function(){
		shopping_add_box.css("display","none");
	});

	button_tj.click(function(){
		shopping_add_box.css("display","none");
	});

})()
