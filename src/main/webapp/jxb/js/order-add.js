//提交订单
function submitOrder() {
	if(subCheck()){
		var data = $("#form1").serialize();
		$.ajax({
			url : "manuallyCreateOrder.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if(result.state==0){
					showMessage("提交成功");
					$("#have_item_table").empty();
					$(".input").val("");
				}else if(result.state==1){
					showMessage("提交资料不全或数据丢失，请稍后重新创建订单。");
				}else if(result.state==2){
					showMessage("保存收货人失败，请稍后重新创建订单。");
				}else if(result.state==3){
					showMessage("创建订单失败，请稍后重新创建订单。");
				}else if(result.state==4){
					showMessage("系统异常，订单内商品丢失，请找到对应订单继续添加。");
				}
			},
			error : function() {
				showMessage("系统错误，请稍后重试。");
			}
		});
	}
}
