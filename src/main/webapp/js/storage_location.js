(function(){

	/*
	*stock_adjustment_btn 批量调整库存按钮
	*stock_adjustment_box 批量调整库存弹出框
	*stock_adjustment_delbtn 批量调整库存关闭按钮
	*/

	var stock_adjustment_btn=$(".stock_adjustment_btn"),
		stock_adjustment_box=$(".stock_adjustment_box"),
		stock_adjustment_delbtn=$(".stock_adjustment_delbtn");

	stock_adjustment_btn.click(function(){
		stock_adjustment_box.css("display","block");
	});

	stock_adjustment_delbtn.click(function(){
		stock_adjustment_box.css("display","none");
	});

	/*
	*adjustment 调整库存按钮
	*adjustment_box 调整库存弹出框
	*adjustment_delbtn 调整库存关闭按钮
	*/
	var adjustment=$(".adjustment"),
		adjustment_box=$(".adjustment_box"),
		adjustment_delbtn=$(".adjustment_delbtn");

	adjustment.click(function(){
		adjustment_box.css("display","block");
	});

	adjustment_delbtn.click(function(){
		adjustment_box.css("display","none");
	});

	/*
	*storage_location_box  批量调整库存库位弹出框
	*storage_location_delbtn  批量调整库存库位关闭按钮
	*storage_location_qx    批量调整库存库位取消按钮
	*storage_location_btn   批量调整库存库位打开按钮
	*/

	var storage_location_box=$(".storage_location_box"),
		storage_location_delbtn=$(".storage_location_delbtn"),
		storage_location_qx=$(".storage_location_qx"),
		storage_location_btn=$(".storage_location_btn");

	storage_location_btn.click(function(){
		storage_location_box.css("display","block");
	});

	storage_location_delbtn.click(function(){
		storage_location_box.css("display","none");
	});

	storage_location_qx.click(function(){
		storage_location_box.css("display","none");
	});

	/*
	*choose_location_btn   选择库位打开按钮
	*choose_location_box    选择库位弹出框
	*choose_location_delbtn    选择库位关闭按钮
	*choose_location_qx    选择库位取消按钮
	*/

	var choose_location_btn=$(".choose_location_btn"),
		choose_location_box=$(".choose_location_box"),
		choose_location_delbtn=$(".choose_location_delbtn"),
		choose_location_qx=$(".choose_location_qx");

	choose_location_btn.click(function(){
		choose_location_box.css("display","block");
	});

	choose_location_delbtn.click(function(){
		choose_location_box.css("display","none");
	});

	choose_location_qx.click(function(){
		choose_location_box.css("display","none");
	});
	
})()