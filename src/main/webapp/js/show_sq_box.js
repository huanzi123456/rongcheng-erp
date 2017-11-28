(function(w){

	/*
	* 授权弹出框
	* show_sq_box
	* show_sq_delbtn
	* show_sq_btn
	*/
	
	
	

	var show_sq_box=$('.show_sq_box'),
		show_sq_delbtn=$('.show_sq_delbtn'),
		show_sq_btn=$('.show_sq_btn');

	show_sq_btn.click(function(){
		show_sq_box.css('display','block');
	});
	show_sq_delbtn.click(function(){
		show_sq_box.css('display','none');
	})
})(window)