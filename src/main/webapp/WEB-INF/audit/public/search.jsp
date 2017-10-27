<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="body-content inquire_box">
  <div class="form-x">
    <div class="form-group margin_bottom">
      <div class="label">
        <label>时间：</label>
      </div>
      <ul class="field jxb_time_field bottom_10">
        <li class="jxb_time_button"><span class="jxb_timeTemp jxb_none">0</span> <input name="orderCreate" class="jxb_time jxb_none" value="" type="text" disabled="disabled" />当日订单</li>
        <li class="jxb_time_button"><span class="jxb_timeTemp jxb_none">2</span> <input name="orderCreate" class="jxb_time jxb_none" value="" type="text" disabled="disabled" />3日内订单</li>
        <li class="jxb_time_button"><span class="jxb_timeTemp jxb_none">6</span> <input name="orderCreate" class="jxb_time jxb_none" value="" type="text" disabled="disabled" />7日内订单</li>
        <li class="jxb_time_button"><span class="jxb_timeTemp jxb_none">29</span> <input name="orderCreate" class="jxb_time jxb_none" value="" type="text" disabled="disabled" />30日内订单</li>
        <li class="jxb_time_button" id="timesli"><input name="time1" id="time1" class="input jxb_time" type="text" disabled="disabled" readonly="readonly"><span>日到</span> <input name="time2" id="time2" class="input jxb_time" type="text" disabled="disabled" readonly="readonly">日订单</li>
      </ul>
    </div>
    <div class="form-group margin_bottom">
      <div class="label">
        <label>默认项：</label>
      </div>
      <ul class="field bottom_10">
        <li class="jxb_button"><input name="buyerWord" id="buyerWord" value="_" type="text" style="display: none;" disabled="disabled" />有留言备注</li>
        <li class="jxb_button"><input name="invoiceTitle" id="invoiceTitle" value="_" type="text" style="display: none;" disabled="disabled" />有发票</li>
        <li class="jxb_button"><input name="refundClose" id="orderRefunded" value="1" type="text" style="display: none;" disabled="disabled" />有退款</li>
        <li class="jxb_button"><input name="orderCod" id="orderCod" value="1" type="text" style="display: none;" disabled="disabled" />货到付款</li>
      </ul>
    </div>
    <div class="form-group margin_bottom">
      <div class="label">
        <label>快递公司：</label>
      </div>
      <ul class="field bottom_10" id="jxb_carrier_ul">
        <!--  -->
      </ul>
    </div>
    <div class="form-group">
      <div class="label">
        <label></label>
      </div>
      <div class="field">
        <button type="button" class="button bg-main" style="margin-left: 20px;" onclick="loadOrderSelective(1)">
          <span class="icon-search"></span> 查询
        </button>
      </div>
    </div>
  </div>
</div>