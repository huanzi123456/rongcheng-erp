(function(){
	// 这段代码写的是只要是个程序员就会写的选项卡效果。
	var strong_btn=$(".strong_btn"),
		inquire_box=$(".inquire_box"),
		num=1;
    inquire_box[0].style.zIndex=1;
	for (var i = strong_btn.length - 1; i >= 0; i--) {
		strong_btn[i].index=i;
		strong_btn[i].onclick=function(){
			for (var j = inquire_box.length - 1; j >= 0; j--) {
				strong_btn[j].style.borderColor="rgba(0,0,0,0)";
			}
			num++;
			inquire_box[this.index].style.zIndex=num;
			strong_btn[this.index].style.borderColor="#00AAEE";
		};
	}
	// 商品总数，商品品类的加减。
	var numberAdd=$(".numberAdd"),
		numberReduce=$(".numberReduce"),
		inputNumber=$(".inputNumber");
	for (var i = numberAdd.length - 1; i >= 0; i--) {
		numberAdd[i].index=i;
		numberAdd[i].onclick=function(){
			var re = /^[0-9]*]*$/;
			if(re.test(inputNumber[this.index].value)){
				if (inputNumber[this.index].value>=999) {
					inputNumber[this.index].value=0;
				}else{
					inputNumber[this.index].value++;
				}
			}else{
				inputNumber[this.index].value=0;
				alert("只能输入正整数");
				return false;
			}
		};
	}
	for (var i = numberReduce.length - 1; i >= 0; i--) {
		numberReduce[i].index=i;
		numberReduce[i].onclick=function(){
			var re = /^[0-9]*]*$/;
			if(re.test(inputNumber[this.index].value)){
				if (inputNumber[this.index].value<=0) {
					inputNumber[this.index].value=999;
				}else{
					inputNumber[this.index].value--;
				}
			}else{
				inputNumber[this.index].value=0;
				alert("只能输入正整数");
				return false;
			}
		};
	}
	// 快递公司更多的展现。
	var expressCompany=$(".expressCompany"),
		moreBtn=$(".moreBtn"),
		inquire_content_box=$(".inquire_content_box"),
		inquire_box=$(".inquire_box");
	moreBtn.click(function(){
		if (expressCompany.height()==30) {
			moreBtn.html("收起");
			expressCompany.height("auto");
			inquire_content_box.height(440);
			inquire_box.height(440);
		}else{
			moreBtn.html("更多");
			expressCompany.height(30);
			inquire_content_box.height(360);
			inquire_box.height(340);
		}
	});
})()
