<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="body-content inquire_box">
  <div class="form-x">
    <div class="form-group">
      <div class="label">
        <label>创建时间：</label>
      </div>
      <div class="field">
        <div class="xinxi">
          <div class="date">
            <input type="text" class="date-check jxb_ASCheck jxb_dblclick_clear" id="time3" name="time1" form="">
          </div>
          <span>至</span>
          <div class="date">
            <input type="text" class="date-check jxb_ASCheck jxb_dblclick_clear" id="time4" name="time2" form="">
          </div>
        </div>
        <div class="xinxi">
          <span>来源：</span> <select class="newInput jxb_ASCheck" id="platformSelect" name="platformId" form="">
            <%-- <option value="1">全部</option> --%>
          </select>
        </div>
        <div class="xinxi">
          <span>店铺：</span> <select class="newInput jxb_ASCheck" id="shopSelect" name="shopId" form="">
          </select>
        </div>
        <div class="xinxi">
          <span class="span">单号：</span> <input type="text" name="erpOrderNum" class="newInput jxb_ASCheck" placeholder="原始单号/系统单号" form="">
        </div>
      </div>
    </div>
    <div class="form-group">
      <div class="label">
        <label>付款时间：</label>
      </div>
      <div class="field">
        <div class="xinxi">
          <div id="from" class="date">
            <input type="text" class="date-check jxb_ASCheck jxb_dblclick_clear" id="payTime1" name="payTime1" form="">
          </div>
          <span>至</span>
          <div id="to" class="date">
            <input type="text" class="date-check jxb_ASCheck jxb_dblclick_clear" id="payTime2" name="payTime2" form="">
          </div>
        </div>
        <div class="xinxi" id="provinceDiv">
          <span>省份：</span> <select class="newInput jxb_ASCheck" name="province" form=""></select>
        </div>
        <div class="xinxi" style="width: 193px;">
          <%-- <span>仓库：</span> <select class="newInput">
            <option value="1">全部</option>
            <option value="2">北欧</option>
          </select> --%>
        </div>
        <div class="xinxi">
          <span class="span">关键字：</span> <input type="text" name="keyword" class="newInput jxb_ASCheck" placeholder="收件人/买家昵称/买家手机/买家编码/商品" form="">
        </div>
      </div>
    </div>
    <div class="form-group">
      <div class="label">
        <label>商品总数：</label>
      </div>
      <div class="field">
        <div class="xinxi">
          <span>商品总数：</span> <input type="text" class="input_width inputNumber jxb_ASCheck" name="quantity" value="" form="">
          <div class="btn_numberAdd">
            <div class="numberAdd iconfont">&#xe632;</div>
            <div class="numberReduce iconfont">&#xe631;</div>
          </div>
          <span>--</span> <input type="text" class="input_width inputNumber jxb_ASCheck" name="quantity" value="" form="">
          <div class="btn_numberAdd">
            <div class="numberAdd iconfont">&#xe632;</div>
            <div class="numberReduce iconfont">&#xe631;</div>
          </div>
        </div>
        <div class="xinxi">
          <span>商品品类：</span> <input type="text" class="input_width inputNumber jxb_ASCheck" name="count" value="" form="">
          <div class="btn_numberAdd">
            <div class="numberAdd iconfont">&#xe632;</div>
            <div class="numberReduce iconfont">&#xe631;</div>
          </div>
          <span>--</span> <input type="text" class="input_width inputNumber jxb_ASCheck" name="count" value="" form="">
          <div class="btn_numberAdd">
            <div class="numberAdd iconfont">&#xe632;</div>
            <div class="numberReduce iconfont">&#xe631;</div>
          </div>
        </div>
        <div class="xinxi">
          <span>订单金额：</span> <input type="text" class="input_width jxb_ASCheck" name="price" form=""> <span>--</span> <input type="text" class="input_width jxb_ASCheck" name="price" form=""> <span>元</span>
        </div>
        <div class="xinxi">
          <span>订单重量：</span> <input type="text" class="input_width jxb_ASCheck" name="weight" form=""> <span>--</span> <input type="text" class="input_width jxb_ASCheck" name="weight" form=""> <span>kg</span>
        </div>
      </div>
    </div>
    <div class="form-group">
      <div class="label">
        <label>默认项：</label>
      </div>
      <div class="field">
        <div class="xinxi">
          <input type="checkbox" name="buyerWord" value="_"> <span>有留言</span>
        </div>
        <div class="xinxi">
          <input type="checkbox" name="invoiceTitle" value="_"> <span>有发票</span>
        </div>
        <div class="xinxi">
          <input type="checkbox" name="refundCloseArray" value="1"> <span>有退款</span>
        </div>
        <div class="xinxi">
          <input type="checkbox" name="refundCloseArray" value="0"> <span>无退款</span>
        </div>
        <div class="xinxi">
          <input type="checkbox" name="orderMerge" value="1"> <span>已合并</span><%-- 字段有值（时间类型） --%>
        </div>
        <div class="xinxi">
          <input type="checkbox" name="orderSplit" value="1"> <span>已拆单</span><%-- 字段有值（时间类型） --%>
        </div>
       <%--  <div class="xinxi">
          <input type="checkbox"> <span>村镇订单</span>
        </div> --%>
        <div class="xinxi">
          <input type="checkbox" name="presellArray" value="1"> <span>有预售</span>
        </div>
        <div class="xinxi">
          <input type="checkbox" name="orderCodArray" value="1"> <span>货到付款</span>
        </div>
        <div class="xinxi">
          <input type="checkbox" name="orderCodArray" value="0"> <span>非货到付款</span>
        </div>
        <div class="xinxi">
          <input type="checkbox" name="orderHold" value="1"> <span>已锁定</span>
        </div>
        <div class="xinxi">
          <input type="checkbox" name="orderEdit" value="1"> <span>已更新</span><%-- 字段有值（时间类型） --%>
        </div>
        <%-- <div class="xinxi">
          <input type="checkbox"> <span class="iconfont" style="color: #7D7D7D;">&#xe8b4;</span>
        </div>
        <div class="xinxi">
          <input type="checkbox"> <span class="iconfont" style="color: #f00;">&#xe8b4;</span>
        </div>
        <div class="xinxi">
          <input type="checkbox"> <span class="iconfont" style="color: #ff0;">&#xe8b4;</span>
        </div>
        <div class="xinxi">
          <input type="checkbox"> <span class="iconfont" style="color: #0f0;">&#xe8b4;</span>
        </div>
        <div class="xinxi">
          <input type="checkbox"> <span class="iconfont" style="color: #00f;">&#xe8b4;</span>
        </div>
        <div class="xinxi">
          <input type="checkbox"> <span class="iconfont" style="color: #f0f;">&#xe8b4;</span>
        </div> --%>
      </div>
    </div>
    <div class="form-group">
      <div class="label">
        <label>打印状态：</label>
      </div>
      <div class="field">
        <div class="xinxi">
          <input type="radio" name="print" value="" checked="checked" disabled="disabled"> <span>全部</span>
        </div>
        <div class="xinxi">
          <input type="radio" name="print" value="1"> <span>已打快递已打发货</span>
        </div>
        <div class="xinxi">
          <input type="radio" name="print" value="2"> <span>已打快递未打发货</span>
        </div>
        <div class="xinxi">
          <input type="radio" name="print" value="3"> <span>未打快递已打发货</span>
        </div>
        <div class="xinxi">
          <input type="radio" name="print" value="4"> <span>未打快递未打发货</span>
        </div>
      </div>
    </div>
    <div class="form-group">
      <div class="label">
        <label>快递公司：</label>
      </div>
      <div class="field">
        <div class="expressCompany" id="carrier_div">
          <%-- <div class="xinxi">
            <input type="checkbox"> <span>百世快递</span>
          </div> --%>
        </div>
        <div class="submit_box">
          <span class="moreBtn">更多</span>
          <button class="button border-main" type="button">查询</button>
          <button class="button border-red" type="button">清空</button>
          <button class="button border-main" type="button">生成快捷查询项</button>
        </div>
      </div>
    </div>
    <div class="form-group ">
      <div class="label">
        <label></label>
      </div>
      <div class="field">
        <button type="button" class="button bg-main" style="margin-left: 20px;" onclick="orderAdvancedSearch(1)">
          <span class="icon-search"></span> 查询
        </button>
      </div>
    </div>
  </div>
</div>