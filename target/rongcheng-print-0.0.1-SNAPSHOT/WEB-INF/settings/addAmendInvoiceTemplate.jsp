<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>打印</title>
	<link rel="stylesheet" href="/css/pintuer.css">
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/invoiceTemplate.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/pintuer.js"></script>
    <link rel="stylesheet" href="/css/font/iconfont.css">
	<script src="/util/jiml-utils.js"></script>
	<script src="/util/templateJson.js"></script>
	<script src="/js/template.js"></script>
	<script src="/util/cookie_util.js"></script>
	<script src="/zb/settings/addAmendInvoiceTemplate.js"></script>
</head>
<body>
<div class="panel admin-panel" style="margin-bottom: 20px;overflow:hidden;">
	<div class="panel-head"><strong class="icon-reorder" id="addAmendInvoiceTemplate_title"></strong></div>
    <div class="padding border-bottom">
	    <ul class="search" style="padding-left:10px;">
	    	<li>模板名称：</li>
	        <li>
	          <input type="text" placeholder="请输入模板名称" name="templateName" class="input" style="width:240px; line-height:17px;display:inline-block" />
			</li>
			<li>纸张大小：</li>
	        <li>
	        	<span>宽度：</span>
	          	<input type="text" placeholder="模板宽度" name="templateWidth" class="input broad" style="width:80px; line-height:17px;display:inline-block" />
	          	<span>毫米</span>
	          	<span style="margin-left: 40px;">高度：</span>
	          	<input type="text" placeholder="模板高度" name="templateHeight" class="input tall" style="width:80px; line-height:17px;display:inline-block" />
	          	<span>毫米</span>
			</li>
			<li>
				打印机选择：
			</li>
			<li>
	            <select class="input" style="width:100px;" id="addAmendInvoiceTemplate_printChoice">
					<!-- 加载打印机 -->
	            </select>
			</li>
			<li>打印位置调整：</li>
			<li>
				<span>左移</span>
				<input type="text"  name="xCoordinate"  class="input" style="width:50px; line-height:17px;display:inline-block">
				<span>毫米</span>
				<span style="margin-left: 40px;">上移</span>
				<input type="text"  name="yCoordinate"  class="input" style="width:50px; line-height:17px;display:inline-block">
				<span>毫米</span>
				<button class="button border-blue" style="margin-left:20px;" id="addAmendInvoiceTemplate_move"><span class="icon-check"></span> 确认移动 </button>
			</li>
	    </ul>
    </div>
	<div class="left_btn_box">
		<ul class="btn_box">
			<li class="shipping_btn">
				页头和页尾
			</li>
			<li class="shipping_btn">
				发贷清单
			</li>
		</ul>
		<div class="top_and_bottom_box">
			<h4>将打印项拖拽至页头或页尾</h4>
			<ul id="addAmendInvoiceTemplate_list">
				<%--加载列表--%>
			</ul>
			<h5>打印项格式设置</h5>
			<div class="format_box">
				<div class="font_sizeAnd_family">
					<span>字体：</span>
					<select name="" id="">
						<option value="1">宋体</option>
						<option value="2">微软雅黑</option>
					</select>
					<div class="font_size_text">
						<input type="text" value="12">
					</div>
					<div class="font_size_btn">
						<button type="button" class="iconfont">&#xe632;</button>
						<button type="button" class="iconfont">&#xe631;</button>
					</div>
				</div>
				<div class="page_layout">
					<button type="button">B</button>
					<button type="button" class="iconfont">&#xe615;</button>
					<button type="button" class="iconfont">&#xe660;</button>
					<span>|</span>
					<button type="button" class="iconfont">&#xe6f6;</button>
					<button type="button" class="iconfont">&#xe600;</button>
					<button type="button" class="iconfont">&#xe6f4;</button>
				</div>
			</div>
			<div class="button border-main font_submit">
				上述设置应用于页头和页尾
			</div>
		</div>
		<div class="shipping_list">
			<h5>勾选发贷清单内容</h5>
			<ul>
				<li>
					<input type="checkbox" class="serialNumber" >序号
				</li>
				<li>
					<input type="checkbox" class="productName">商品名称
				</li>
				<li>
					<input type="checkbox" class="productNumber">数量
				</li>
				<li>
					<input type="checkbox" class="unitPrice">单价
				</li>
				<li>
					<input type="checkbox" class="discounts">优惠
				</li>
				<li>
					<input type="checkbox" class="amountActuallyPaid">实付金额
				</li>
				<li>
					<input type="checkbox" class="specification">规格
				</li>
				<li>
					<input type="checkbox" class="money">金额
				</li>
				<li>
					<input type="checkbox" class="CODING">商家编码
				</li>
				<li>
					<input type="checkbox" class="storageLocation">库位
				</li>
				<li>
					<input type="checkbox" class="aggregate">总计
				</li>
				<li>
					<input type="checkbox" class="productPicture">商品图片
				</li>
				<li class="row_one">
					<input type="checkbox" class="printOnlineItem">打印线上商品信息
				</li>
				<li class="row_one">
					<input type="checkbox" class="printGroupItem">打印组合商品信息
				</li>
			</ul>
			<h3>
				商品排序：
			</h3>
			<ul>
				<li>
					<input type="radio" name="shipping" class="productName">商品名称
				</li>
				<li>
					<input type="radio" name="shipping" class="CODING">商品编码
				</li>
				<li>
					<input type="radio" name="shipping" class="storageLocation">库位
				</li>
				<li>
					<input type="radio" name="shipping" class="quantityReverse">数量倒序
				</li>
			</ul>
			<h5>发货清单格式设置</h5>
			<div class="format_box">
				<div class="font_sizeAnd_family">
					<span>字体：</span>
					<select name="" id="fontStyle">
						<option value="1">宋体</option>
						<option value="2">微软雅黑</option>
					</select>
					<div class="font_size_text">
						<input type="text" value="12">
					</div>
					<div class="font_size_btn">
						<button type="button" class="iconfont">&#xe632;</button>
						<button type="button" class="iconfont">&#xe631;</button>
					</div>
				</div>
				<div class="page_layout">
					<button type="button">B</button>
					<button type="button" class="iconfont">&#xe615;</button>
					<button type="button" class="iconfont">&#xe660;</button>
					<span>|</span>
					<button type="button" class="iconfont">&#xe6f6;</button>
					<button type="button" class="iconfont">&#xe600;</button>
					<button type="button" class="iconfont">&#xe6f4;</button>
				</div>
			</div>
			<div class="lineHeight">
				<div class="title_height">
					<span>标题行高度(毫米):</span>
					<div class="font_size_text">
						<input type="text" value="12">
					</div>
					<div class="font_size_btn">
						<button type="button" class="iconfont">&#xe632;</button>
						<button type="button" class="iconfont">&#xe631;</button>
					</div>
				</div>
				<div class="title_height">
					<span>&nbsp;&nbsp;页边距(毫米):</span>
					<div class="font_size_text">
						<input type="text" value="12">
					</div>
					<div class="font_size_btn">
						<button type="button" class="iconfont">&#xe632;</button>
						<button type="button" class="iconfont">&#xe631;</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="right_show_box">
		<div class="content-box" id="addAmendInvoiceTemplate_background">
			<div class="header_content"></div>
			<div class="table_list_box">
				<table class="table_list" id="tableId">
					<tr>
						<th>序号</th>
						<th width="100">商品图片</th>
						<th width="100">商品名称</th>
						<th>数量</th>
						<th>规格</th>
						<th width="100">商品编码</th>
						<th>单价</th>
						<th>优惠</th>
						<th>实付金额</th>
						<th>金额</th>
						<th>库位</th>
					</tr>
					<tr>
						<td>1</td>
						<td><img src="/images/abc.png" alt="找不到"></td>
						<td>商品名称</td>
						<td>2</td>
						<td>黄色 190ML</td>
						<td>K1235445</td>
						<td>2.00</td>
						<td>1.00</td>
						<td>3.00</td>
						<td>4.00</td>
						<td>000-A</td>
					</tr>
					<tr>
						<td>2</td>
						<td><img src="/images/abc.png" alt="找不到"></td>
						<td>商品名称</td>
						<td>2</td>
						<td>黄色 190ML</td>
						<td>K1235445</td>
						<td>2.00</td>
						<td>1.00</td>
						<td>3.00</td>
						<td>4.00</td>
						<td>000-A</td>
					</tr>
					<tr>
						<td>总计</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>4</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>2.00</td>
						<td>6.00</td>
						<td>8.00</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
			<div class="footer_content"></div>
		</div>
		<div class="button_box">
		    <button class="button bg-main" id="addAmendInvoiceTemplate_commit"> 提交</button>
		    <button class="button border-blue" id="addAmendInvoiceTemplate_goback"> 返回</button>
        </div>
	</div>
</div>
</body>
<%--<script src="/js/invoiceTemplate.js"></script>--%>
</html>
