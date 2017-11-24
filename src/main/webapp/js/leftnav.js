// 左部导航模块按钮变颜色
var leftnav_h2=document.querySelectorAll(".leftnav>h2");
var leftnav_h2_a=document.querySelectorAll(".leftnav>h2>a");
// var not_h2=document.querySelector(".leftnav>h2")
for (var i = leftnav_h2.length - 1; i >= 0; i--) {
	leftnav_h2[i].index=i;
	leftnav_h2[i].onclick=function(){
		for (var j = leftnav_h2.length - 1; j >= 0; j--) {
			leftnav_h2[j].style.background="";
			leftnav_h2_a[j].style.color="#333";
		};
		leftnav_h2[this.index].style.background="#10719E";
		leftnav_h2_a[this.index].style.color="#fff";
	}
};

