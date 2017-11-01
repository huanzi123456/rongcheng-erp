(function(){

	/*
	*matching_alert_box  添加配对弹出的灰色框
	*matching_delBtn  关闭按钮
	*matching_bc   保存按钮
	*alert_matching   弹出按钮
	*/

	var matching_alert_box=$(".matching_alert_box"),
		matching_delBtn=$(".matching_delBtn"),
		matching_bc=$(".matching_bc"),
		alert_matching=$(".alert_matching");
	
	alert_matching.click(function(){
		matching_alert_box.css("display","block");
	});

	matching_bc.click(function(){
		matching_alert_box.css("display","none");
	});

	matching_delBtn.click(function(){
		matching_alert_box.css("display","none");
	});

})()