
var tn=function(){};
tn.prototype={
	_AddName:function(varclass){
		return document.querySelector(varclass);
	},
	_AddNameAll:function(varclass){
		return document.querySelectorAll(varclass);
	},
	switchover:function(){
		var shipping_btn=this._AddNameAll(".shipping_btn"),
			top_and_bottom_box=this._AddName(".top_and_bottom_box"),
			shipping_list=this._AddName(".shipping_list"),
			num=0;
		for (var i = shipping_btn.length - 1; i >= 0; i--) {
			shipping_btn[0].onclick=function(){
				num++;
				top_and_bottom_box.style.zIndex=num;
			};
			shipping_btn[1].onclick=function(){
				num++;
				shipping_list.style.zIndex=num;
			};
		}
		return this;
	},
	model_del:function(module,element,contentBox){
		var del_Btn=document.createElement("div");
		del_Btn.id="del_Btn";
		del_Btn.innerHTML="x";
		module.appendChild(del_Btn);
		del_Btn.onclick=function(){
			contentBox.removeChild(module);
			if (element) {
				element.style.color="#333";
			}
		};
		del_Btn.onmousedown=function(e){
			var ev=e||window.event;
			ev.stopPropagation()
		};
		return this;
	},
	tableDisplay:function(){
		var table_list=document.getElementById("tableId"),
			serialNumber=this._AddName(".serialNumber"),
			productName=this._AddName(".productName"),
			productNumber=this._AddName(".productNumber"),
			unitPrice=this._AddName(".unitPrice"),
			discounts=this._AddName(".discounts"),
			amountActuallyPaid=this._AddName(".amountActuallyPaid"),
			specification=this._AddName(".specification"),
			money=this._AddName(".money"),
			CODING=this._AddName(".CODING"),
			storageLocation=this._AddName(".storageLocation"),
			aggregate=this._AddName(".aggregate"),
			productPicture=this._AddName(".productPicture");
		// 隐藏某一列，总计所在的行也隐藏
		function setCellsNumberHide(num){
			for (var i = table_list.rows.length - 1; i >= 0; i--) {
				for (var j = table_list.rows.item(0).cells.length - 1; j >= 0; j--) {
					table_list.rows[i].cells[num].style.display="none";
					break;
				}
			}
		};
		// 显示某一列，但是不显示总计所在的行
		function setCellsNumberShow(num){
			for (var i = table_list.rows.length - 2; i >= 0; i--) {
				for (var j = table_list.rows.item(0).cells.length - 1; j >= 0; j--) {
					table_list.rows[i].cells[num].style.display="table-cell";
					break;
				}
			}
		};
		// 显示某一列，总计所在的行也显示
		function setCellsNumberShow2(num){
			for (var i = table_list.rows.length - 1; i >= 0; i--) {
				for (var j = table_list.rows.item(0).cells.length - 1; j >= 0; j--) {
					table_list.rows[i].cells[num].style.display="table-cell";
					break;
				}
			}
		};
		// 判断是否显现
		function ifJudge(judge,num){
			// 在显示对应的列的时候，必须判断总计选中没有，
			// 如果没选中，则执行setCellsNumberShow
			// 如果选中，则执行setCellsNumberShow2
			// 下边的思路一样
			// 序号这里有一个bug，就是一关序号，总计的所在列也会消失。
			// 如果不消失，那样总计所在的行会多出一列。
			if (judge) {
				if (aggregate.checked) {
					setCellsNumberShow2(num);
				}else{
					setCellsNumberShow(num);
				}
			}else{
				setCellsNumberHide(num);
			}
		};
		// 序号显示与隐藏
		serialNumber.onclick=function(){
			ifJudge(serialNumber.checked,0);
		};
		// 商品名称显示与隐藏
		productName.onclick=function(){
			ifJudge(productName.checked,2);
		};
		// 数量显示与隐藏
		productNumber.onclick=function(){
			ifJudge(productNumber.checked,3);
		};
		// 单价显示与隐藏
		unitPrice.onclick=function(){
			ifJudge(unitPrice.checked,6);
		};
		// 优惠显示与隐藏
		discounts.onclick=function(){
			ifJudge(discounts.checked,7);
		};
		// 实付金额显示与隐藏
		amountActuallyPaid.onclick=function(){
			ifJudge(amountActuallyPaid.checked,8);
		};
		// 规格显示与隐藏
		specification.onclick=function(){
			ifJudge(specification.checked,4);
		};
		// 金额显示与隐藏
		money.onclick=function(){
			ifJudge(money.checked,9);
		};
		// 商家编码显示与隐藏
		CODING.onclick=function(){
			ifJudge(CODING.checked,5);
		};
		// 库位显示与隐藏
		storageLocation.onclick=function(){
			ifJudge(storageLocation.checked,10);
		};
		// 商品图片显示与隐藏
		productPicture.onclick=function(){
			ifJudge(productPicture.checked,1);
		};
		// 总计显示与隐藏
		aggregate.onclick=function(){
			// 显示总计所在的行的某一列
			function zongjiNumber(mum){
				for (var i = table_list.rows.length - 2; i >= 0; i--) {
					for (var j = table_list.rows.item(0).cells.length - 1; j >= 0; j--) {
						table_list.rows[3].cells[mum].style.display="table-cell";
						break;
					}
				}
			};
			if (aggregate.checked) {
				// 如果总计被选中，必须判断上边的11列是否被选中，如果选中则显示对应的列
				// 如果没选中，则对应的列不显示。
				if (serialNumber.checked) {
					zongjiNumber(0);
				}else{
					zongjiNumber(0);
				}
				if (productName.checked) {
					zongjiNumber(2);
				}
				if (productNumber.checked) {
					zongjiNumber(3);
				}
				if (unitPrice.checked) {
					zongjiNumber(6);
				}
				if (discounts.checked) {
					zongjiNumber(7);
				}
				if (amountActuallyPaid.checked) {
					zongjiNumber(8);
				}
				if (specification.checked) {
					zongjiNumber(4);
				}
				if (money.checked) {
					zongjiNumber(9);
				}
				if (CODING.checked) {
					zongjiNumber(5);
				}
				if (storageLocation.checked) {
					zongjiNumber(10);
				}
				if (productPicture.checked) {
					zongjiNumber(1);
				}
			}else{
				for (var i = table_list.rows.length - 2; i >= 0; i--) {
					for (var j = table_list.rows.item(0).cells.length - 1; j >= 0; j--) {
						table_list.rows[3].cells[j].style.display="none";
					}
				}
			}
		}
		return this;
	},
	onmove:function(){
		var header_content=this._AddName(".header_content"),
			footer_content=this._AddName(".footer_content"),
			top_and_bottom_box=$(".top_and_bottom_box ul>li");
		for (var i = top_and_bottom_box.length - 1; i >= 0; i--) {
			model_add(this._AddName('.'+top_and_bottom_box[i].className),top_and_bottom_box[i].className,top_and_bottom_box[i].innerHTML,100,30);
		}
		var thas=this;
		function model_add(element,IDname,content,module_width,module_height){
			element.onmousedown=function(e){
				element.onclick=null;
				var ev=e||window.event;
					ev.preventDefault();
				document.onmousemove=function(e){
					var ev=e||window.event;
					ev.stopPropagation();
					ev.preventDefault();
					function creation_ele(that,cw,ch,box_content){
						var module=document.createElement("div");
						module.id=IDname;
						module.style.cssText="width: "+module_width+"px;height: "+module_height+"px;position: absolute;";
						module.draggable=true;
						that.appendChild(module);
						element.style.color="red";
						var textarea=document.createElement("textarea");
						textarea.value=content;
						textarea.style.cssText="width: 100%;height: 100%;background:rgba(100,100,255,.2);cursor:pointer;";
						textarea.readOnly=true;
						module.appendChild(textarea);
						if (0>=cw) {
							module.style.left=0+"px";
							module.style.top=0+"px";
						}else if(that.offsetWidth<=cw+module.offsetWidth){
							module.style.left=0+"px";
							module.style.top=0+"px";
						}else if(that.offsetHeight<=ch+module.offsetHeight){
							module.style.left=0+"px";
							module.style.top=0+"px";
						}else if(0>=ch){
							module.style.left=0+"px";
							module.style.top=0+"px";
						}else{
							module.style.left=cw+"px";
							module.style.top=ch+"px";
						}
						document.onmousemove=null;
						box_content.onmouseup=null;
						thas.model_del(module,element,that);
						module.addEventListener("mousedown",move);
						function move(e){
							var en=e|| window.event;
							var ow=en.offsetX; /*ow是现在鼠标按下的点距离这个元素左边的距离*/
							var oh=en.offsetY; /*oh是现在鼠标按下的点距离这个元素上边的距离*/
							var ConOW=textarea.offsetWidth; /* ConOW获取的是module的宽度*/
							var ConOH=textarea.offsetHeight; /* ConOH获取的是module的高度*/
							var conOW=that.offsetWidth-ConOW; /* conOW获取的是contentBox最右边距离浏览器的距离*/
							var conOH=that.offsetHeight-ConOH; /* conOH获取的是contentBox最下边距离浏览器的距离*/
							textarea.style.boxShadow="0px 0px 5px #f00";
							/*试用keyCode*/
							module.onkeydown=function(e){
								var en=e|| window.event|| arguments.callee.caller.arguments[0];
								var moveLeft=module.offsetLeft;
								var moveTop=module.offsetTop;
								if (en.keyCode==37) {//向左
									if (moveLeft<=2) {
										module.style.left=2+"px";
									}else{
										module.style.left=moveLeft-2+"px";
									}
								}
								if (en.keyCode==38) { //向上
									if (moveTop<=2) {
										module.style.top=2+"px";
									}else{
										module.style.top=moveTop-2+"px";
									}
								}
								if (en.keyCode==39) {//向右
									if (moveLeft>=conOW-2) {
										module.style.left=conOW-2+"px";
									}else{
										module.style.left=moveLeft+2+"px";
									}
								}
								if (en.keyCode==40) {//向下
									if (moveTop>=conOH-2) {
										module.style.top=conOH-2+"px";
									}else{
										module.style.top=moveTop+2+"px";
									}
								}
								textarea.style.boxShadow="";
								document.onmousemove=null;
								document.onmouseup=null;
							};
							document.onmousemove=function(e){
								module.onmousemove=function(e){
									var en=e|| window.event;
									en.stopPropagation();
									en.preventDefault();
								};
								var en=e|| window.event;
								en.preventDefault();
								var aw=textarea.offsetWidth;/*aw,ah获取的是鼠标移动编译器的宽高*/
								var wh=textarea.offsetHeight;
								if (ConOW==aw&&ConOH==wh) {
									var CX=en.offsetX;
									var CY=en.offsetY;
									var xw=CX-ow;
									var xh=CY-oh;
									if (xw<0) {	/*处理左上角和左下角的bug*/
										if (xh<0) {
											module.style.left=2+"px";
											module.style.top=2+"px";
										}else if(xh>=conOH){
											module.style.left=2+"px";
											module.style.top=conOH-2+"px";
										}else{
											module.style.left=2+"px";
											module.style.top=xh+"px";
										} /* 小于contentBox的最左边执行*/
									}else if (xw>=conOW) { /*处理右上角和右下角的bug*/
										if (xh<0) {
											module.style.left=conOW-2+"px";
											module.style.top=2+"px";
										}else if(xh>=conOH){
											module.style.left=conOW-2+"px";
											module.style.top=conOH-2+"px";
										}else{
											module.style.left=conOW-2+"px";
											module.style.top=xh+"px";
										}/* 小于contentBox的最右边执行*/
									}else if (xh<0) { /* 小于contentBox的最上边执行*/
										module.style.left=xw+"px";
										module.style.top=2+"px";
									}else if(xh>=conOH){ /* 小于contentBox的最下边执行*/
										module.style.left=xw+"px";
										module.style.top=conOH-2+"px";
									}else{
										if(xw==0&&xh==0){

										}else{
											module.style.left=xw+"px";
											module.style.top=xh+"px";
										}
									}
								}
							};
							document.onmouseup=function(e){
								textarea.style.boxShadow="";
								document.onmousemove=null;
								document.onmouseup=null;
							};
						};
					};
					header_content.onmouseover=function(){
						var that=this;
						header_content.onmouseup=function(e){
							var en=e||window.event,
								cw=en.offsetX,
								ch=en.offsetY;
							creation_ele(that,cw,ch,header_content)
						};
					};
					header_content.onmouseout=function(){
						footer_content.onmouseover=null;
						header_content.onmouseover=null;
						footer_content.onmouseup=null;
						header_content.onmouseout=null;
					};
					footer_content.onmouseover=function(){
						var that=this;
						footer_content.onmouseup=function(e){
							var en=e||window.event,
								cw=en.offsetX,
								ch=en.offsetY;
							creation_ele(that,cw,ch,footer_content)
						};
					};
					footer_content.onmouseout=function(){
						header_content.onmouseover=null;
						footer_content.onmouseover=null;
						header_content.onmouseup=null;
						footer_content.onmouseout=null;
					};
				};
			};
			element.onmouseup=function(){
				element.onclick=null;
				element.onmousedown=null;
				element.onmouseup=null;
			};
		};
		return this;
	},
};
var Activate=new tn;
Activate.switchover().tableDisplay().onmove();
/*var arr1=[],arr2=[];
if (document.querySelectorAll(".header_content>div[draggable='true']") || document.querySelectorAll(".footer_content>div[draggable='true']")) {
	var module1=document.querySelectorAll(".header_content>div[draggable='true']"),
		textarea1=document.querySelectorAll(".header_content>div[draggable='true']>textarea"),
		module2=document.querySelectorAll(".footer_content>div[draggable='true']"),
		textarea2=document.querySelectorAll(".footer_content>div[draggable='true']>textarea"),
		footer_content=document.querySelector(".footer_content"),
		header_content=document.querySelector(".header_content"),
		top_and_bottom_li=document.querySelectorAll(".top_and_bottom_box ul li");
	function setMove(module,textarea,boxContent,arr){
		for (var i = module.length - 1; i >= 0; i--) {
			module[i].index=i;
			for (var j = top_and_bottom_li.length - 1; j >= 0; j--) {
				if (top_and_bottom_li[j].className==module[i].id) {
					top_and_bottom_li[j].style.color="#f00";
					Activate.model_del(module[i],top_and_bottom_li[j],boxContent);
					arr.unshift(top_and_bottom_li[j].innerHTML);
				}
			}
			for (var j = textarea.length - 1; j >= 0; j--) {
				textarea[j].value=arr[j];
			}
			module[i].addEventListener('mousedown',move);
			function move(e){
				var en=e|| window.event;
				var ow=en.offsetX; /!*ow是现在鼠标按下的点距离这个元素左边的距离*!/
				var oh=en.offsetY; /!*oh是现在鼠标按下的点距离这个元素上边的距离*!/
				var ConOW=textarea[this.index].offsetWidth; /!* ConOW获取的是module的宽度*!/
				var ConOH=textarea[this.index].offsetHeight; /!* ConOH获取的是module的高度*!/
				var conOW=boxContent.offsetWidth-ConOW; /!* conOW获取的是contentBox最右边距离浏览器的距离*!/
				var conOH=boxContent.offsetHeight-ConOH; /!* conOH获取的是contentBox最下边距离浏览器的距离*!/
				textarea[this.index].style.boxShadow="0px 0px 5px #f00";
				/!*试用keyCode*!/
				this.onkeydown=function(e){
					var en=e|| window.event|| arguments.callee.caller.arguments[0];
					var moveLeft=this.offsetLeft;
					var moveTop=this.offsetTop;
					if (en.keyCode==37) {//向左
						if (moveLeft<=2) {
							this.style.left=2+"px";
						}else{
							this.style.left=moveLeft-2+"px";
						}
					}
					if (en.keyCode==38) { //向上
						if (moveTop<=2) {
							this.style.top=2+"px";
						}else{
							this.style.top=moveTop-2+"px";
						}
					}
					if (en.keyCode==39) {//向右
						if (moveLeft>=conOW-2) {
							this.style.left=conOW-2+"px";
						}else{
							this.style.left=moveLeft+2+"px";
						}
					}
					if (en.keyCode==40) {//向下
						if (moveTop>=conOH-2) {
							this.style.top=conOH-2+"px";
						}else{
							this.style.top=moveTop+2+"px";
						}
					}
					textarea[this.index].style.boxShadow="";
					document.onmousemove=null;
					document.onmouseup=null;
				};
				var that=this;
				document.onmousemove=function(e){
					that.onmousemove=function(e){
						var en=e|| window.event;
						en.stopPropagation();
						en.preventDefault();
					};
					var en=e|| window.event;
					en.preventDefault();
					var aw=textarea[that.index].offsetWidth;/!*aw,ah获取的是鼠标移动编译器的宽高*!/
					var wh=textarea[that.index].offsetHeight;
					if (ConOW==aw&&ConOH==wh) {
						var CX=en.offsetX;
						var CY=en.offsetY;
						var xw=CX-ow;
						var xh=CY-oh;
						if (xw<0) {	/!*处理左上角和左下角的bug*!/
							if (xh<0) {
								that.style.left=2+"px";
								that.style.top=2+"px";
							}else if(xh>=conOH){
								that.style.left=2+"px";
								that.style.top=conOH-2+"px";
							}else{
								that.style.left=2+"px";
								that.style.top=xh+"px";
							} /!* 小于contentBox的最左边执行*!/
						}else if (xw>=conOW) { /!*处理右上角和右下角的bug*!/
							if (xh<0) {
								that.style.left=conOW-2+"px";
								that.style.top=2+"px";
							}else if(xh>=conOH){
								that.style.left=conOW-2+"px";
								that.style.top=conOH-2+"px";
							}else{
								that.style.left=conOW-2+"px";
								that.style.top=xh+"px";
							}/!* 小于contentBox的最右边执行*!/
						}else if (xh<0) { /!* 小于contentBox的最上边执行*!/
							that.style.left=xw+"px";
							that.style.top=2+"px";
						}else if(xh>=conOH){ /!* 小于contentBox的最下边执行*!/
							that.style.left=xw+"px";
							that.style.top=conOH-2+"px";
						}else{
							if(xw==0&&xh==0){

							}else{
								that.style.left=xw+"px";
								that.style.top=xh+"px";
							}
						}
					}
				};
				document.onmouseup=function(e){
					textarea[that.index].style.boxShadow="";
					document.onmousemove=null;
					document.onmouseup=null;
				};
			};
		};
	};
	setMove(module1,textarea1,header_content,arr1);
	setMove(module2,textarea2,footer_content,arr2);
}*/

function oldFloatingFrame2() {
    var arr1=[],arr2=[];
    if (document.querySelectorAll(".header_content>div[draggable='true']") || document.querySelectorAll(".footer_content>div[draggable='true']")) {
        var module1=document.querySelectorAll(".header_content>div[draggable='true']"),
            textarea1=document.querySelectorAll(".header_content>div[draggable='true']>textarea"),
            module2=document.querySelectorAll(".footer_content>div[draggable='true']"),
            textarea2=document.querySelectorAll(".footer_content>div[draggable='true']>textarea"),
            footer_content=document.querySelector(".footer_content"),
            header_content=document.querySelector(".header_content"),
            top_and_bottom_li=document.querySelectorAll(".top_and_bottom_box ul li");
        function setMove(module,textarea,boxContent,arr){
            for (var i = module.length - 1; i >= 0; i--) {
                module[i].index=i;
                for (var j = top_and_bottom_li.length - 1; j >= 0; j--) {
                    if (top_and_bottom_li[j].className==module[i].id) {
                        top_and_bottom_li[j].style.color="#f00";
                        Activate.model_del(module[i],top_and_bottom_li[j],boxContent);
                        arr.unshift(top_and_bottom_li[j].innerHTML);
                    }
                }
                for (var j = textarea.length - 1; j >= 0; j--) {
                    textarea[j].value=arr[j];
                }
                module[i].addEventListener('mousedown',move);
                function move(e){
                    var en=e|| window.event;
                    var ow=en.offsetX; /*ow是现在鼠标按下的点距离这个元素左边的距离*/
                    var oh=en.offsetY; /*oh是现在鼠标按下的点距离这个元素上边的距离*/
                    var ConOW=textarea[this.index].offsetWidth; /* ConOW获取的是module的宽度*/
                    var ConOH=textarea[this.index].offsetHeight; /* ConOH获取的是module的高度*/
                    var conOW=boxContent.offsetWidth-ConOW; /* conOW获取的是contentBox最右边距离浏览器的距离*/
                    var conOH=boxContent.offsetHeight-ConOH; /* conOH获取的是contentBox最下边距离浏览器的距离*/
                    textarea[this.index].style.boxShadow="0px 0px 5px #f00";
                    /*试用keyCode*/
                    this.onkeydown=function(e){
                        var en=e|| window.event|| arguments.callee.caller.arguments[0];
                        var moveLeft=this.offsetLeft;
                        var moveTop=this.offsetTop;
                        if (en.keyCode==37) {//向左
                            if (moveLeft<=2) {
                                this.style.left=2+"px";
                            }else{
                                this.style.left=moveLeft-2+"px";
                            }
                        }
                        if (en.keyCode==38) { //向上
                            if (moveTop<=2) {
                                this.style.top=2+"px";
                            }else{
                                this.style.top=moveTop-2+"px";
                            }
                        }
                        if (en.keyCode==39) {//向右
                            if (moveLeft>=conOW-2) {
                                this.style.left=conOW-2+"px";
                            }else{
                                this.style.left=moveLeft+2+"px";
                            }
                        }
                        if (en.keyCode==40) {//向下
                            if (moveTop>=conOH-2) {
                                this.style.top=conOH-2+"px";
                            }else{
                                this.style.top=moveTop+2+"px";
                            }
                        }
                        textarea[this.index].style.boxShadow="";
                        document.onmousemove=null;
                        document.onmouseup=null;
                    };
                    var that=this;
                    document.onmousemove=function(e){
                        that.onmousemove=function(e){
                            var en=e|| window.event;
                            en.stopPropagation();
                            en.preventDefault();
                        };
                        var en=e|| window.event;
                        en.preventDefault();
                        var aw=textarea[that.index].offsetWidth;/*aw,ah获取的是鼠标移动编译器的宽高*/
                        var wh=textarea[that.index].offsetHeight;
                        if (ConOW==aw&&ConOH==wh) {
                            var CX=en.offsetX;
                            var CY=en.offsetY;
                            var xw=CX-ow;
                            var xh=CY-oh;
                            if (xw<0) {	/*处理左上角和左下角的bug*/
                                if (xh<0) {
                                    that.style.left=2+"px";
                                    that.style.top=2+"px";
                                }else if(xh>=conOH){
                                    that.style.left=2+"px";
                                    that.style.top=conOH-2+"px";
                                }else{
                                    that.style.left=2+"px";
                                    that.style.top=xh+"px";
                                } /* 小于contentBox的最左边执行*/
                            }else if (xw>=conOW) { /*处理右上角和右下角的bug*/
                                if (xh<0) {
                                    that.style.left=conOW-2+"px";
                                    that.style.top=2+"px";
                                }else if(xh>=conOH){
                                    that.style.left=conOW-2+"px";
                                    that.style.top=conOH-2+"px";
                                }else{
                                    that.style.left=conOW-2+"px";
                                    that.style.top=xh+"px";
                                }/* 小于contentBox的最右边执行*/
                            }else if (xh<0) { /* 小于contentBox的最上边执行*/
                                that.style.left=xw+"px";
                                that.style.top=2+"px";
                            }else if(xh>=conOH){ /* 小于contentBox的最下边执行*/
                                that.style.left=xw+"px";
                                that.style.top=conOH-2+"px";
                            }else{
                                if(xw==0&&xh==0){

                                }else{
                                    that.style.left=xw+"px";
                                    that.style.top=xh+"px";
                                }
                            }
                        }
                    };
                    document.onmouseup=function(e){
                        textarea[that.index].style.boxShadow="";
                        document.onmousemove=null;
                        document.onmouseup=null;
                    };
                };
            };
        };
        setMove(module1,textarea1,header_content,arr1);
        setMove(module2,textarea2,footer_content,arr2);
    }
}