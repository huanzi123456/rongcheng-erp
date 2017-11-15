// 头部导航模块按钮变颜色
var bread_li=document.querySelectorAll(".bread>li"),
	bread_li_a=document.querySelectorAll(".bread>li>a"),
	iframe=document.querySelector("iframe[name='right']"),
	/*arr1=[
			'settings/commodity_management.html',
			'audit/order_add.html',
			'audit/have_print.html',
			'shipment/shipment_inspection.html',
			'inventory/inventory_state.html',
			'dataStatistics/dataStatistics.html',
			'account/account_add_amend.html'
		]*/
	arr1=[
		'/ItemInfoAdmin.do',
		'/audit/orderAdd.do',
		'/toPrint.do',
		'/shipmentInspection.do',
		'/inventoryState.do',
		'/dataStatistics/dataStatistics.html',
		'/account/account_add_amend.html'
	]

for (var i = bread_li.length - 1; i >= 0; i--) {
	bread_li[i].index=i;
	bread_li[i].onclick=function(){
		for (var j = bread_li.length - 1; j >= 0; j--) {
			bread_li[j].style.background="";
			bread_li_a[j].style.color="#333";
		};
		bread_li[this.index].style.background="#10719E";
		bread_li_a[this.index].style.color="#fff";
		iframe.src=arr1[this.index];
	}
};

