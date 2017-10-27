var laozi=$(".laozi");//下拉相关
var	erzi=$(".erzi");//下拉相关
function reload(data,isAdvancedSearch) {
	$("#checkall").removeAttr("checked");
	$("#item_table").empty();
	var ul = $("#reload");
	$(".jxb_clear_ul").remove();
	var uls = '';
	if(data != null){
		for (var i = 0; i < data.length; i++) {
			var obj = data[i];
			uls += '<ul class="table-ul two_row jxb_clear_ul">';
			uls += '<li>';
			uls += ' <input type="checkbox" name="id" value="'+obj.id+'" style="float: left;margin: 15px 0 0 5px;"/>'+(obj.erpOrderNum==null?"":obj.erpOrderNum)+'<br> ';
			uls += '<span>'+(obj.platformOrderId==null?"":obj.platformOrderId)+'</span>';
			uls += '</li>';
			uls += '<li>';
			var platform = obj.platformInfo;
			uls += '<span>'+(platform==null?"":platform.name) +'</span><br>';
			var shop = obj.shopInfo;
			uls += '<span>'+(shop==null?"":shop.name) +'</span>';
			uls += '</li>';
			uls += '<li>';
			var buyerInfo = obj.buyerInfo;
			uls += '<span>'+(buyerInfo==null?"":buyerInfo.buyerAccount==null?"":buyerInfo.buyerAccount)+'</span><br>';
			uls += '<span>'+(obj.orderPay==null?"未付款":("已付款"+((new Date()-obj.orderPay)/3600000).toFixed(1))+"小时")+'</span>';
			uls += '</li> ';
			uls += '<li> ';
			uls += '<span>'+(buyerInfo==null?"":buyerInfo.consigneeName)+'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>'+(buyerInfo==null?"":buyerInfo.consigneeTel)+'</span><br>';
			uls += '<span class="regionCode">'+(buyerInfo==null?"":buyerInfo.regionId)+'</span><span>'+(buyerInfo==null?"":buyerInfo.userAddress)+'</span>  ';
			uls += '</li> '; 
			uls += ' <li> ';
			uls += '<div class="button border-main xialaBtn"> ';
			uls += '<span class="icon-plus"></span><span id="carrierName'+obj.id+'">'+getCarrierName(obj.carrierId)+'</span>';

			uls += '<ul> ';
	    	for (var j = 0; j < carrier.length; j++) {
	    		uls += '<li onclick="updateCarrier('+obj.id+','+(j+1)+')">';
	    		uls += carrier[j]+'</li> ';
			}
			uls += '</ul> ';
			uls += '</div>  ';
			uls += '</li>   ';
			uls += '<li>'+ (obj.invoiceTitle == null ?"":obj.invoiceTitle) +(obj.invoiceDetail == null ?"":("/"+obj.invoiceDetail))+'</li> ';
			uls += ' <li>'+($.isEmptyObject(obj.buyerWord)?"":obj.buyerWord)+($.isEmptyObject(obj.sellerWord)?"":("/"+obj.sellerWord))+'</li>'; 
			uls += ' <li>￥normalPrice</li>  '; 
			uls += '<li>quantity</li> ';
			uls += '<li> ';
			uls += '<div class="button-group"> ';
			uls += '<a class="button border-main" href="orderEditAment.do?id='+obj.id+'"><span class="icon-edit"></span> 修改</a> ';
			uls += '<a class="button border-black laozi" href="javascript:;"><span class="icon-chevron-down"></span>下拉</a> ';
			uls += '</div> ';
			uls += '</li> ';
			uls += '<div class="table-ul-son erzi">';
			if(obj.itemCommonWarehouse_S != null){
				var q = 0;
				var p = 0;
				for(var y=0;y<obj.itemCommonWarehouse_S.length;y++){
					var icw = obj.itemCommonWarehouse_S[y];
					q+=icw.quantity;
					p+=icw.normalPrice*icw.quantity;
					uls += '<ul>';
					uls += '<li>'+icw.erpItemNum+'</li>';  
					uls += '<li>'+icw.name+'</li>  ';
					uls += '<li>'+icw.color+'*'+icw.size+'</li>';
					uls += '<li>￥'+(icw.normalPrice==null?"0":icw.normalPrice)+'</li>';    
					uls += '<li>'+icw.quantity+'</li>';
					uls += '<li>0 kg</li>';
					uls += '<li>总重：0 kg</li>';
					var warehouse = icw.warehouseInfo;
					if(warehouse != null){
						uls += '<li>'+warehouse.name+'</li>';
					}else{
						uls += '<li></li>';
					}
					uls += '</ul>';
				}
				uls = uls.replace(/quantity/,q);
				uls = uls.replace(/normalPrice/,p);
			}
			uls += '</div>';
			uls += '</ul>';
		}
		ul.after(uls);
		$(".icon-reorder").html("&nbsp;待审核订单");
		if(isAdvancedSearch){
			pagination(max_page,now_page,"pagelist","orderAdvancedSearch");
		}else{
			pagination(max_page,now_page,"pagelist","loadOrderSelective");
		}
		//下拉相关
		laozi=$(".laozi");
		erzi=$(".erzi");
		for (var i = laozi.length - 1; i >= 0; i--) {
			laozi[i].index=i;
			laozi[i].flag=false;
			laozi[i].addEventListener("click",function(){
				if (laozi[this.index].flag) {
					erzi[this.index].style.display="none";	
					laozi[this.index].flag=false;
				}else{
					for (var i = erzi.length - 1; i >= 0; i--) {
						erzi[i].style.display="none";
						laozi[i].flag=false;
					};
					erzi[this.index].style.display="block";
					laozi[this.index].flag=true;
				}
				
			},true)
		};
		erzi.on("click",function(e){
			e.stopPropagation()
		})
		//替换地区编码为汉字
      	var regionCodeSpans = $(".regionCode");
      	for(var r=0;r<regionCodeSpans.length;r++){
           	var province,city,area;
			var json = getRegionNameByCode($(regionCodeSpans[r]).html());
			$(regionCodeSpans[r]).html(json.province+json.city+json.area);
      	}
	}
}
