(function(){

	/*
	*inventory_state_box  警戒库存弹出框
	*alert_btn   警戒库存点击按钮
	*inventory_state_delbtn  警戒库存关闭按钮
	*invascript_qx   警戒库存取消按钮
	*/

	var inventory_state_box=$(".inventory_state_box"),
		alert_btn=$(".alert_btn"),
		inventory_state_delbtn=$(".inventory_state_delbtn"),
		invascript_qx=$(".invascript_qx");

	alert_btn.click(function(){
		inventory_state_box.css("display","block");
	});

	inventory_state_delbtn.click(function(){
		inventory_state_box.css("display","none");
	});

	invascript_qx.click(function(){
		inventory_state_box.css("display","none");
	});

})()