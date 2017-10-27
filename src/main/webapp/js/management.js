var NO_1_input=$(".NO_1 input");
var NO_2_input=$(".NO_2 input");
var NO_3_input=$(".NO_3 input");
NO_1_input.click(function(){
	if (NO_1_input.is(":checked")) {
		NO_2_input.prop("checked",true);
		NO_3_input.prop("checked",true);
	}else{
		NO_2_input.prop("checked",false);
		NO_3_input.prop("checked",false);
	}
})
NO_2_input.click(function(){
	if ($(this).is(":checked")) {
		$(this).parents().next().children(".NO_3").children("input").prop("checked",true);
	}else{
		$(this).parents().next().children(".NO_3").children("input").prop("checked",false);
	}
})
NO_3_input.click(function(){
	if ($(this).is(":checked")) {
		$(this).parents().prev().children(".NO_2").children("input").prop("checked",true);
	}else{
		if ($(this).parents(".NO_3").siblings().children("input").is(":checked")) {

		}else{
			$(this).parents().prev().children(".NO_2").children("input").prop("checked",false);
		}
		
	}
})
