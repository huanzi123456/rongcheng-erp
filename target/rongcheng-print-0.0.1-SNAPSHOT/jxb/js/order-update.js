//修改订单
function submitOrder() {
	if(subCheck()){
		var data = $("#form1").serialize();
		$.ajax({
			url : "updateOneOrder.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if(result.state==0){
					showMessage("修改成功");
					//$("#have_item_table").empty();
					//$(".input").val("");
				}else if(result.state==1){
					showMessage("提交资料不全或数据丢失，请稍后重试。");
				}else if(result.state==2){
					showMessage("数据库存储失败，请稍后重试。");
				}
			},
			error : function() {
				showMessage("系统错误，请稍后重试。");
			}
		});
	}
}
