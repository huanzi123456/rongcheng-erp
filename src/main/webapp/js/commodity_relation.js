(function(){

	/*
	* 线上商品对应关系页面 模拟的下拉框
	* commodity_xiala  实现下拉的按钮
	* select_mf_xl    下拉的框
	*/

	var commodity_xiala=$(".commodity_xiala"),
		select_mf_xl=$(".select_mf_xl");

	commodity_xiala.click(function(){
		if (select_mf_xl.css('display')==='block') {
			select_mf_xl.css("display","none");
		}else{
			select_mf_xl.css("display","block");
		}
	})




	/*
	* 线上商品对应关系页面 批量处理商品对应关系按钮弹出框
	* commodity_relation_bc 弹出框保存按钮
	* commodity_relation_qx 弹出框取消按钮
	* commodity_relation_delbtn  弹出框右上角叉号
	* commodity_relation_box  弹出框
	* commodity_relation_select  触发弹出框的点击按钮
	*/

	var commodity_relation_bc=$(".commodity_relation_bc"),
		commodity_relation_qx=$(".commodity_relation_qx"),
		commodity_relation_delbtn=$(".commodity_relation_delbtn"),
		commodity_relation_box=$(".commodity_relation_box"),
		commodity_relation_select=$(".commodity_relation_select");

		commodity_relation_select.click(function(){
			commodity_relation_box.css("display","block");
		});
		commodity_relation_bc.click(function(){
			commodity_relation_box.css("display","none");
		});
		commodity_relation_qx.click(function(){
			commodity_relation_box.css("display","none");
		});
		commodity_relation_delbtn.click(function(){
			commodity_relation_box.css("display","none");
		});


	/*
	* 线上商品对应关系页面 批量处理商品对应关系按钮弹出框里边的系统商品按钮
	* search_commodity 打开弹出框的按钮
	* hs_code_bc 保存按钮
	* hs_code_box 弹出框
	* hs_code_delbtn  弹出框右上角的叉号
	*/

	var search_commodity=$(".search_commodity"),
		hs_code_bc=$(".hs_code_bc"),
		hs_code_box=$(".hs_code_box"),
		hs_code_delbtn=$(".hs_code_delbtn");

		search_commodity.click(function(){
			hs_code_box.css("display","block");
		});
		hs_code_bc.click(function(){
			hs_code_box.css("display","none");
		});
		hs_code_delbtn.click(function(){
			hs_code_box.css("display","none");
		})

	/*
	* 线上商品对应关系页面 批量更新系统商品弹出框按钮
	* hs_code_buttom  打开弹出框的按钮
	* mass_update_bc 弹出框保存按钮
	* mass_update_qx 弹出框取消按钮
	* mass_update_box 弹出框
	* mass_update_delbtn 弹出框右上角的叉号
	*/

	var hs_code_buttom=$(".hs_code_buttom"),
		mass_update_bc=$(".mass_update_bc"),
		mass_update_qx=$(".mass_update_qx"),
		mass_update_box=$(".mass_update_box"),
		mass_update_delbtn=$(".mass_update_delbtn");
	
	hs_code_buttom.click(function(){
		mass_update_box.css("display","block");
	});
	mass_update_bc.click(function(){
		mass_update_box.css("display","none");
	});
	mass_update_qx.click(function(){
		mass_update_box.css("display","none");
	});
	mass_update_delbtn.click(function(){
		mass_update_box.css("display","none");
	});

	/*
	* 线上商品对应关系页面 换按钮弹出框
	* single_updating_box 弹出框
	* single_updating_bc  弹出框的已有框保存按钮
	* single_updating_list  弹出框的选项卡列表
	* single_updating_list_btn_box   弹出框的选项卡按钮盒子
	* single_new_bc  弹出框新建框保存按钮
	* single_new_qx  弹出框新建框取消按钮
	* single_huan  打开弹出框按钮
	* single_updating_delbtn   弹出框右上角叉号
	*/

	var single_updating_box=$(".single_updating_box"),
		single_updating_bc=$(".single_updating_bc"),
		single_new_bc=$(".single_new_bc"),
		single_new_qx=$(".single_new_qx"),
		single_huan=$(".single_huan"),
		single_updating_delbtn=$(".single_updating_delbtn"),
		single_updating_list=$(".single_updating_list"),
		single_updating_list_btn=$(".single_updating_list_btn_box span"),
		num=1;

	single_huan.click(function(){
		single_updating_box.css("display","block");
	});
	single_updating_bc.click(function(){
		single_updating_box.css("display","none");
	});
	single_new_bc.click(function(){
		single_updating_box.css("display","none");
	});
	single_new_qx.click(function(){
		single_updating_box.css("display","none");
	});
	single_updating_delbtn.click(function(){
		single_updating_box.css("display","none");
	});

	/*
	* 选项卡效果
	*/

	single_updating_list.eq(0).css("z-index","1");
	single_updating_list_btn.click(function(){
		num++;
		single_updating_list_btn.attr('class','').eq($(this).index()).attr('class','active');
		single_updating_list.eq($(this).index()).css("z-index",num);
	});
	
	/*
	* 线上商品对应关系页面 手工同步按钮弹出框
	* sgtb_btn 手工同步弹出框打开按钮
	* manual_sync_box 弹出框
	* manual_sync_delbtn 弹出框右上角叉号
	*/

	var sgtb_btn=$(".sgtb_btn"),
		manual_sync_box=$(".manual_sync_box"),
		manual_sync_delbtn=$(".manual_sync_delbtn");

	sgtb_btn.click(function(){
		manual_sync_box.css("display",'block');
	});
	manual_sync_delbtn.click(function(){
		manual_sync_box.css("display",'none');
	});

})()