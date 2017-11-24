(function(){

	/*
	* 采购管理 采购入库单页面 新建入库单
	* new_godown_entryBtn 新建按钮
	* print_godown_entryBox  弹出框
	* print_godown_delbtn	右上角叉号
	* print_godown_bc   保存
	* print_godown_qx  保存并收货入库
	*/

	var new_godown_entryBtn=$(".new_godown_entryBtn"),
		print_godown_entryBox=$(".print_godown_entryBox"),
		print_godown_delbtn=$(".print_godown_delbtn");
		
	new_godown_entryBtn.click(function(){
		print_godown_entryBox.css('display','block');
	});
	print_godown_delbtn.click(function(){
		print_godown_entryBox.css('display','none');
	});




	/*
	* 采购管理 采购入库单页面 新建入库单 添加商品
	* add_goodsBtn 添加商品按钮
	* print_code_box 弹出框
	* print_code_delbtn 右上角叉号
	* print_code_bc   保存
	*/

	var add_goodsBtn=$(".add_goodsBtn"),
		print_code_box=$(".print_code_box"),
		print_code_delbtn=$(".print_code_delbtn"),
		print_code_bc=$(".print_code_bc");

	add_goodsBtn.click(function(){
		print_code_box.css('display','block');
	});
	print_code_delbtn.click(function(){
		print_code_box.css('display','none');
	});
	print_code_bc.click(function(){
		print_code_box.css('display','none');
	});



	/*
	* 采购管理 采购入库单页面 新建入库单 设定库位
	* print_godown_kwBtn    设定库位按钮
	* storageLocation_code_box  弹出框
	* storageLocation_code_delbtn  右上角叉号
	* storageLocation_code_bc   保存
	* storageLocation_code_qx 取消
	*/
	var print_godown_kwBtn=$('.print_godown_kwBtn'),
		storageLocation_code_box=$(".storageLocation_code_box"),
		storageLocation_code_delbtn=$(".storageLocation_code_delbtn"),
		storageLocation_code_bc=$(".storageLocation_code_bc"),
		storageLocation_code_qx=$(".storageLocation_code_qx");

	print_godown_kwBtn.click(function(){
		storageLocation_code_box.css('display','block');
	});
	storageLocation_code_delbtn.click(function(){
		storageLocation_code_box.css('display','none');
	});
	storageLocation_code_bc.click(function(){
		storageLocation_code_box.css('display','none');
	});
	storageLocation_code_qx.click(function(){
		storageLocation_code_box.css('display','none');
	});



	/*
	* 采购管理 采购入库单页面  打印入库单
	* godown_entryStamp_alertBtn   打印入库单按钮
	* godown_entryStamp_box   弹出框
	* godown_entryStamp_delbtn   右上角叉号关闭按钮
	*/

	var godown_entryStamp_alertBtn=$(".godown_entryStamp_alertBtn"),
		godown_entryStamp_box=$(".godown_entryStamp_box"),
		godown_entryStamp_delbtn=$(".godown_entryStamp_delbtn");

	godown_entryStamp_alertBtn.click(function(){
		godown_entryStamp_box.css('display','block');
	});
	godown_entryStamp_delbtn.click(function(){
		godown_entryStamp_box.css('display','none');
	});



	/*
	* 采购管理 采购入库单页面 查看按钮弹出框
	* examine_entry_alertBtn   查看按钮
	* examine_entry_entryBox  弹出框
	* examine_entry_delbtn   右上角关闭按钮
	*/

	var examine_entry_alertBtn=$(".examine_entry_alertBtn"),
		examine_entry_entryBox=$(".examine_entry_entryBox"),
		examine_entry_delbtn=$(".examine_entry_delbtn");

	examine_entry_alertBtn.click(function(){
		examine_entry_entryBox.css('display','block');
	});
	examine_entry_delbtn.click(function(){
		examine_entry_entryBox.css('display','none');
	});
})()