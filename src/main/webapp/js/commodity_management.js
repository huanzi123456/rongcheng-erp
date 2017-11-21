(function(){
	/*
	* 商品管理页面的新建和新建并复制按钮弹出框
	* management_xj 新建按钮
	* management_fzxj  新建并复制按按钮
	* management_bc  弹出框里边的保存按钮
	* management_qx   弹出框里边的取消按钮
	* management_delbtn  弹出框右上角的叉号
	* commodity_management_box   弹出框
	* management_amend 列表页面修改按钮
	*/
	var management_xj=$(".management_xj"),
		management_fzxj=$(".management_fzxj"),
		management_bc=$(".management_bc"),
		management_qx=$(".management_qx"),
		management_delbtn=$(".management_delbtn"),
		management_amend=$(".management_amend"),
		commodity_management_box=$(".commodity_management_box");
	management_xj.click(function(){
		commodity_management_box.css("display","block");
	});
	management_amend.click(function(){
		commodity_management_box.css("display","block");
	});
	management_fzxj.click(function(){
		commodity_management_box.css("display","block");
	});
	management_bc.click(function(){
		commodity_management_box.css("display","none");
	});
	management_qx.click(function(){
		commodity_management_box.css("display","none");
	});
	management_delbtn.click(function(){
		commodity_management_box.css("display","none");
	});


	/*
	* 新建 复制新建弹出框 模拟下拉框
	* select_simulate_box 弹出框
	* select_simulate_xb  触发按钮
	*/
	var select_simulate_xb=$('.select_simulate_xb'),
		select_simulate_box=$('.select_simulate_box');
		
	select_simulate_xb.click(function(){
		if (select_simulate_box.css('display')=='none') {
			select_simulate_box.css("display","block");
		}else{
			select_simulate_box.css("display","none");
		}
	});




	/*
	* 商品管理页面的更改分类按钮弹出框
	* classify_fl 更改分类按钮
	* classify_delbtn 弹出框右上角的叉号
	* amend_classify_box  弹出框
	* classify_bc   弹出框保存按钮
	* classify_qx   弹出框取消按钮
	*
	*/
	var classify_fl=$(".classify_fl"),
		classify_delbtn=$(".classify_delbtn"),
		amend_classify_box=$(".amend_classify_box"),
		classify_bc=$(".classify_bc"),
		classify_qx=$(".classify_qx");
	classify_fl.click(function(){
		amend_classify_box.css("display","block");
	});
	classify_delbtn.click(function(){
		amend_classify_box.css("display","none");
	});
	classify_bc.click(function(){
		amend_classify_box.css("display","none");
	});
	classify_qx.click(function(){
		amend_classify_box.css("display","none");
	});



	/*
	* 商品管理页面的分类 新增 修改按钮弹出框
	* class_add  新增按钮
	* class_amend  修改按钮
	* class_add_box  弹出框
	* class_add__bc  弹出框保存按钮
	* class_add__qx  弹出框取消按钮
	* class_add_delbtn   弹出框右上角叉号
	*/

	var class_add=$(".class_add"),
		class_amend=$(".class_amend "),
		class_add_box=$(".class_add_box"),
		class_add__bc=$(".class_add__bc"),
		class_add__qx=$(".class_add__qx"),
		class_add_delbtn=$(".class_add_delbtn");
	class_add.click(function(){
		class_add_box.css("display","block");
	});
	class_amend.click(function(){
		class_add_box.css("display","block");
	});
	class_add__bc.click(function(){
		class_add_box.css("display","none");
	});
	class_add__qx.click(function(){
		class_add_box.css("display","none");
	});
	class_add_delbtn.click(function(){
		class_add_box.css("display","none");
	});
})()