(function(){
	/*
	* 售后管理 退货单管理  新建入库单
	* goods_returned_alertBtn  新建触发按钮
	* goods_returned_noteBox    弹出框
	* goods_returned_delbtn    右上角关闭按钮
	*/
	var goods_returned_alertBtn=$('.goods_returned_alertBtn'),
		goods_returned_noteBox=$('.goods_returned_noteBox'),
		goods_returned_delbtn=$('.goods_returned_delbtn');

	goods_returned_alertBtn.click(function(){
		goods_returned_noteBox.css('display','block');
	});
	goods_returned_delbtn.click(function(){
		goods_returned_noteBox.css('display','none');
	});




	/*
	* 售后管理 退货单管理  新建入库单 添加商品
	* add_goodssBtn      添加商品触发按钮
	* goods_returned_note_box    弹出框
	* goods_returned_note_delbtn   右上角叉号
	* goods_returned_note_bc    保存按钮
	*/

	var add_goodssBtn=$(".add_goodssBtn"),
		goods_returned_note_box=$(".goods_returned_note_box"),
		goods_returned_note_delbtn=$(".goods_returned_note_delbtn"),
		goods_returned_note_bc=$(".goods_returned_note_bc");

	add_goodssBtn.click(function(){
		goods_returned_note_box.css('display','block');
	});
	goods_returned_note_delbtn.click(function(){
		goods_returned_note_box.css('display','none');
	});
	goods_returned_note_bc.click(function(){
		goods_returned_note_box.css('display','none');
	});



	/*
	* 售后管理 退货单管理  新建入库单  设定库位
	* setThe_location_alertBtn    设定库位触发按钮
	* setThe_location_box   弹出框
	* setThe_location_delbtn    右上角关闭按钮
	* setThe_location_bc     保存
	* setThe_location_qx    取消
	*/

	var setThe_location_alertBtn=$(".setThe_location_alertBtn"),
		setThe_location_box=$(".setThe_location_box"),
		setThe_location_delbtn=$(".setThe_location_delbtn"),
		setThe_location_bc=$(".setThe_location_bc"),
		setThe_location_qx=$(".setThe_location_qx");

	setThe_location_alertBtn.click(function(){
		setThe_location_box.css('display','block');
	});
	setThe_location_delbtn.click(function(){
		setThe_location_box.css('display','none');
	});
	setThe_location_bc.click(function(){
		setThe_location_box.css('display','none');
	});
	setThe_location_qx.click(function(){
		setThe_location_box.css('display','none');
	});




	/*
	* 售后管理 退货单管理  新建入库单  搜索按钮
	* dDsearch    搜索弹出框触发按钮
	* dDsearch_box  弹出框
	* dDsearch_delbtn   右上角关闭按钮
	* dDsearch_bc    保存按钮
	* dDsearch_qx    取消按钮
	*/

	var dDsearch=$(".dDsearch"),
		dDsearch_box=$(".dDsearch_box"),
		dDsearch_delbtn=$(".dDsearch_delbtn"),
		dDsearch_bc=$(".dDsearch_bc"),
		dDsearch_qx=$(".dDsearch_qx");

	dDsearch.click(function(){
		dDsearch_box.css('display','block');
	});
	dDsearch_delbtn.click(function(){
		dDsearch_box.css('display','none');
	});
	dDsearch_bc.click(function(){
		dDsearch_box.css('display','none');
	});
	dDsearch_qx.click(function(){
		dDsearch_box.css('display','none');
	});




	/*
	* 售后管理 退货单管理  查看按钮
	* goods_returned_ck_alertBtn  查看按钮
	* goods_returned_ckBox   弹出框
	* goods_returned_ck_delbtn  右上角关闭按钮
	*/

	var goods_returned_ck_alertBtn=$(".goods_returned_ck_alertBtn"),
		goods_returned_ckBox=$(".goods_returned_ckBox"),
		goods_returned_ck_delbtn=$(".goods_returned_ck_delbtn");

	goods_returned_ck_alertBtn.click(function(){
		goods_returned_ckBox.css('display','block');
	});
	goods_returned_ck_delbtn.click(function(){
		goods_returned_ckBox.css('display','none');
	});

})()