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
				uls += ' <input type="checkbox" name="id" value="'+obj.id+'" />'+obj.erpOrderNum+'<br> ';
				uls += '<span>'+obj.platformOrderId+'</span>';
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
				uls += '<span>'+(obj.orderPay==null?"未付款":("已付款"+((new Date()-obj.orderPay)/3600000).toFixed(1)+"小时"))+'</span>';
			uls += '</li> ';
			uls += '<li> ';
				uls += '<span>'+(buyerInfo==null?"":buyerInfo.consigneeName)+'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>'+(buyerInfo==null?"":buyerInfo.consigneeTel)+'</span><br>';
				uls += '<span class="regionCode">'+(buyerInfo==null?"":buyerInfo.regionId)+'</span><span>'+(buyerInfo==null?"":buyerInfo.userAddress)+'</span>  ';
			uls += '</li> ';
			uls += '<li> ';
			if(isOtherOrder){
				uls += '<span>'+getCarrierName(obj.carrierId)+'</span>';
			}else{
				uls += '<div class="button border-main xialaBtn"> ';
				uls += '<span class="icon-plus"></span><span id="carrierName'+obj.id+'">'+getCarrierName(obj.carrierId)+'</span>';
				
				uls += '<input name="carrierId" id="carrierId'+obj.id+'" value="'+(obj.carrierId==null?"":obj.carrierId)+'" style="display:none;"/>';
				
				uls += '<ul> ';
				for (var j = 0; j < carrier.length; j++) {
					uls += '<li onclick="updateCarrier('+obj.id+','+(j+1)+')">';
					uls += carrier[j]+'</li> ';
				}
				uls += '</ul>';
				uls += '</div>  ';
			}
			uls += '</li>';
			if(isOtherOrder){
				var status = obj.orderStatus;
				uls += '<li><span class="jxb_wlzt">'+(status < 7?"未发货":status == 7?"已发货":status == 8?"问题件":status == 9?"已签收":status == 10?"订单完成":"状态未知")+'</span></li>';
			}else{
				uls += '<li>'+ (obj.invoiceTitle == null ?"":obj.invoiceTitle) +(obj.invoiceDetail == null ?"":("/"+obj.invoiceDetail))+'</li> ';
			}
			uls += '<li>'+(obj.buyerWord==null?"":obj.buyerWord)+(obj.sellerWord==null?"":("/"+obj.sellerWord))+'</li>'; 
			if(isOtherOrder){
				uls += '<li><span>'+(obj.trackingNum == null ?"":obj.trackingNum)+'</span></li>';
			}else{
				uls += '<li><input type="text" class="input jxb-trackingNum" value="'+(obj.trackingNum == null ?"":obj.trackingNum)+'" title="普通面单需要输入单号,电子面单自动获取" placeholder="普通面单需要输入单号,电子面单自动获取"></li>';
			}
			if(isOtherOrder){
				uls += '<li class="overflow_inh">';
					uls += '<div class="logistics-record">';
						uls += '<span class="button border-main logistics-record-btn" onclick="inquireExpress('+obj.id+','+obj.carrierId+','+obj.trackingNum+','+obj.orderStatus+',this)">刷新物流</span>';
						uls += '<div class="logistics-record-box">';
							uls += '<div class="logistics-record_delBtn">×</div>';
							uls += '<div class="logistics-record-date">';
								uls += '<span>付款时间：</span><span>'+new Date(obj.orderPay).format('yyyy/MM/dd hh:mm')+'</span>';
							uls += '</div>';
							uls += '<div class="logistics-record-text jxb_wlxq">';
								uls += $.isEmptyObject(obj.traces)?"":obj.traces;
							uls += '</div>';
						uls += '</div>';
					uls += '</div>';
				uls += '</li>';
			}else{
				uls += '<li>'+new Date(obj.orderPay).format('yyyy/MM/dd hh:mm')+'</li>';
			}
			uls += '<li> ';
			uls += '<div class="button-group"> ';
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
					p+=icw.normalPrice;
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
				//alert(q);
				uls = uls.replace(/quantity/,q);
				uls = uls.replace(/normalPrice/,p);
			}
			uls += '</div>';
			uls += '</ul>';
		}
		ul.after(uls);
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
      	var logistics_record_delBtn=$(".logistics-record_delBtn");
      	logistics_record_delBtn.click(function(){
      		$(".logistics-record-box").css('display','none');
      	})
	}
}
function inquireExpress(orderId, carrierId, trackingNum, status, obj) {
	var box = $(".logistics-record-box");
		// 掉快递
		$.ajax({
			url : "/refreshExpress.do",
			type : "post",
			data : {"orderId":orderId, "carrierId":carrierId, "expNo":trackingNum, "status":status},
			dataType : "json",
			success : function(result) {
				if(result == -1){
					showMessage("快递信息查询。");
				}else{
					var state = result.State;
					var traces = result.Traces;
					if(state != 0){
						$(obj). parents(".jxb_clear_ul").find(".jxb_wlzt").html(state == 2?"已发货":state == 3?"已签收":state == 4?"问题件":"");
					}
					if(traces.length>0){
						var ps = '';
						for (var i = 0; i < traces.length; i++) {
							ps += '<p>'+traces[i].AcceptTime+'&emsp;'+traces[i].AcceptStation+'</p>';
						}
						$(obj).next().find(".jxb_wlxq").html(ps);
					}
				}
			},
			error : function() {
				showMessage("快递信息查询。");
			}
		});
		
		box.css('display', 'none');
		$(obj).next(".logistics-record-box").css('display', 'block');
}

