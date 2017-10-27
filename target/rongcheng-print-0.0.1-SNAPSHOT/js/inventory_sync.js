(function(){

	/*
	*inventory_sync_box  库存同步弹出的灰色框
	*inventory_sync_delbtn  关闭按钮
	*inventory_qx   取消按钮
	*alert_btn   弹出按钮
	*/

	var inventory_sync_box=$(".inventory_sync_box"),
		inventory_sync_delbtn=$(".inventory_sync_delbtn"),
		inventory_qx=$(".inventory_qx");

	inventory_qx.click(function(){
		inventory_sync_box.css("display","none");
	});

	inventory_sync_delbtn.click(function(){
		inventory_sync_box.css("display","none");
	});

})()