(function(){

	/*
	*inventory_alert_box 仓库列表新建修改弹出灰色框
	*add_button   新建按钮
	*amend_button   修改按钮
	*inventory_del    取消按钮
	*new_inventory_delbtn   关闭按钮
	*/
	var inventory_alert_box=$(".inventory_alert_box"),
		add_button=$(".add_button"),
		amend_button=$(".amend_button"),
		inventory_del=$(".inventory_del"),
		new_inventory_delbtn=$(".new_inventory_delbtn");

	add_button.click(function(){
		inventory_alert_box.css("display","block");
	});

	amend_button.click(function(){
		inventory_alert_box.css("display","block");
	});

	new_inventory_delbtn.click(function(){
		inventory_alert_box.css("display","none");
	});

	inventory_del.click(function(){
		inventory_alert_box.css("display","none");
	});
	/*
	*content_btn	选项卡按钮
	*content_text   选项卡内容
	*num  zIndex值，初始为1
	*/

	var content_btn=$(".content_btn"),
		content_text=$(".content_text"),
		num=1;
	content_text.eq(0).css("zIndex",1);
	$(".content_btn").click(function(){
		num++;
		$(".content_text").eq($(this).index()).css("zIndex",num)
	})

})()

/*
*下边的代码是使用面向对象的方式写了一遍上边的方法。
*inventory_alert_box 仓库列表新建修改弹出灰色框
*add_button   新建按钮
*amend_button   修改按钮
*inventory_del    取消按钮
*new_inventory_delbtn   关闭按钮
*/
/*
var TN={};
var inventory_alert_box=$(".inventory_alert_box");
var add_button=$(".add_button");
var amend_button=$(".amend_button");
var inventory_del=$(".inventory_del");
var new_inventory_delbtn=$(".new_inventory_delbtn");
TN.Display=function(btn,box,text){
	btn.click(function(){
		box.css("display",text);
	});
}
TN.Block1=new TN.Display(add_button,inventory_alert_box,"block");
TN.Block2=new TN.Display(amend_button,inventory_alert_box,"block");
TN.None1=new TN.Display(new_inventory_delbtn,inventory_alert_box,"none");
TN.None2=new TN.Display(inventory_del,inventory_alert_box,"none");
*/
