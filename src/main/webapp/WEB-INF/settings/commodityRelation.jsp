<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>  
    <link rel="stylesheet" href="../css/pintuer.css">
    <link rel="stylesheet" href="../css/admin.css">
    <script src="../js/jquery.js"></script>
    <script src="../js/pintuer.js"></script>  
    <link rel="stylesheet" href="../css/inventory_state.css">
    <link rel="stylesheet" href="../css/alert_box.css">
    <link rel="stylesheet" href="../css/commodity_relation.css">
    <script src="../js/big_box.js"></script>
    <style>
      .div_big_box:after{
      content: "";
      display: block;
      height: 0;
      clear: both;
    }
    </style> 
    <script type="text/javascript" src="/wzy/itemInfo.js"></script>
    <script type="text/javascript" src="/xzy/commonRelation/commondityRelation.js"></script>
    <script type="text/javascript" src="/util/jiml-utils.js"></script>
    
</head>
<body style="position: relative;">
  <div class="div_big_box">
      <div class="panel admin-panel accredit-height">
        <!-- <div class="panel-head"><strong class="icon-reorder"> 库存同步</strong></div> -->
        <div class="padding border-bottom">
          <ul class="search">
              <li>状态：
                <select class="input" style="width:80px; line-height:17px; display:inline-block" id="xzy_select0">
                  <option value="-1">全部</option>
                  <option value="1">在售商品</option>
                  <option value="0">下架商品</option>
                </select>
              </li>
              <li>店铺：
                <select class="input" style="width:80px; line-height:17px; display:inline-block" id="xzy_select1">
                  <!-- 
                  <option value="">全部店铺</option>
                  <option value="1">店铺1</option>
                  <option value="0">店铺2</option>
                   -->
                </select>
              </li>
              <li>线上商品：
                <input type="text" placeholder="商品编号/商品名称" class="input" style="width:140px; line-height:17px;display:inline-block" id="xzy_online"/>
              </li>
              <li>系统商品：
                <input type="text" placeholder="商品编号/商品名称" class="input" style="width:140px; line-height:17px;display:inline-block" id="xzy_system"/>
                <a href="javascript:;" class="button border-main icon-search"> 查询</a>
              </li>
              <li style="line-height: 20px;">
                <input type="checkbox"><span>只显示编码不一致</span><br>
                <input type="checkbox"><span>只显示未关联</span>
              </li>
          </ul>
        </div>
        <ul class="search search_bottom">
            <li style="width: 18%;">
              <div class="select_mf">
                <a href="javascript:;" class="button border-main commodity_relation_select" id="xzy_maintainRelation">批量维护对应关系</a>
                <div class="commodity_xiala">
                  <div class="dsjx"></div>
                </div>
                <div class="select_mf_xl">
                  <a href="javascript:;" class="button border-main hs_code_buttom">批量更新系统商品</a>
                </div>
              </div>
            </li>
            <li style="width: 18%;">
              <a href="javascript:;" class="button border-main"> 更新待审核订单的商品</a>
            </li>
            <li class="search_middle">
              <div class="hint_top">
                <div class="commodity_left">
                  <div></div>
                  线上商品
                </div>
            <div class="commodity_middle"></div>
            <div class="commodity_right">
              <div></div>
              系统商品
            </div>
              </div>
              <div class="hint_bottom">
                <div class="fl">(每分钟和平台同步显示) <a href="javascript:;" class="sgtb_btn">手工同步</a></div>
                <div class="fr">(对应实际的货品信息)</div>
              </div>
            </li>
            <li style="width: 14%;margin-right: 0;">
              <a href="javascript:;">点击查看使用说明</a>
            </li>
        </ul>
        <table class="table table-hover text-center list_table" id="xzy_limit">
        <!-- 
        <tr>
          <th width="30"><input type="checkbox"/>1</th>
          <th width="100">&nbsp;</th>
          <th>线上店铺</th>       
          <th>
            <span class="span50">线上商品编码</span>
            <span class="span50">线上商品规格</span>
          </th>
          <th width="50">&nbsp;</th>
          <th>
            <span class="span50">对应系统商品编码</span>
            <span class="span50">对应系统商品规格</span>
          </th>
          <th>操作</th>       
        </tr>             
        <tr>
          <td>
            <input type="checkbox" name="id[]" value="1" class="check_coding" /><br>
              9
          </td>
          <td>
            <div class="sjx" title="线上商品和系统商品编码不一致"></div>
            <div class="img_box">
              <img src="../images/400.png" alt="">
              <div class="show_img_box">
                <img src="../images/400.png" alt="">
              </div>
            </div>
            <div class="dianpu">
              <img src="../images/dp.png" alt="" title="微店">
            </div>
          </td>
          <td>
            <p>
              盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
            </p>
            <span>25659652695</span>
          </td>
          <td>
            <div>
              <div class="wh" title="线上商品的商家编码没有编写">线上商品编码用sku</div>
               -->
              <!-- <span>2556496549595</span>
              <span class="span50">线上商品规格</span>
            </div>
            <p>
              二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
            </p>
          </td>  
          <td style="border-left: 1px dashed #999;">
            <div class="img_box">
              <img src="../images/400.png" alt="">
              <div class="show_img_box">
                <img src="../images/400.png" alt="">
              </div>
            </div>
          </td>
          <td >
            <div>
              <span class="span50">41546486496</span>
              <span class="span50">4659659958</span>
            </div>
            <p>
              二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
            </p>
          </td>
          <td>
            <a href="javascript:;" class="button border-main single_huan"> 换</a>
          </td>
        </tr>
        <tr>
          <td>
            <input type="checkbox" name="id[]" value="1" class="check_coding" /><br>
              1
          </td>
          <td>
            <div class="sjx" title="线上商品和系统商品编码不一致"></div>
            <div class="img_box">
              <img src="../images/400.png" alt="">
              <div class="show_img_box">
                <img src="../images/400.png" alt="">
              </div>
            </div>
            <div class="dianpu">
              <img src="../images/dp.png" alt="" title="微店">
            </div>
          </td>
          <td>
            <p>
              盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
            </p>
            <span>25659652695</span>
          </td>
          <td>
            <div>
              <div class="wh" title="线上商品的商家编码没有编写"></div> -->
              <!-- <span>2556496549595</span> 

              <span class="span50">2556496549595</span>
            </div>
            <p>
              二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
            </p>
          </td>  
          <td style="border-left: 1px dashed #999;">
            <div class="img_box">
              <img src="../images/400.png" alt="">
              <div class="show_img_box">
                <img src="../images/400.png" alt="">
              </div>
            </div>
          </td>
          <td>
            <div>
              <span class="span50">41546486496</span>
              <span class="span50">4659659958</span>
            </div>
            <p>
              二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
            </p>
          </td>
          <td>
            <a href="javascript:;" class="button border-main single_huan"> 换</a>
          </td>
        </tr>
        <tr>
          <td>
            <input type="checkbox" name="id[]" value="1" class="check_coding" /><br>
              1
          </td>
          <td>
            <div class="sjx" title="线上商品和系统商品编码不一致"></div>
            <div class="img_box">
              <img src="../images/400.png" alt="">
              <div class="show_img_box">
                <img src="../images/400.png" alt="">
              </div>
            </div>
            <div class="dianpu">
              <img src="../images/dp.png" alt="" title="微店">
            </div>
          </td>
          <td>
            <p>
              盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
            </p>
            <span>25659652695</span>
          </td>
          <td>
            <div>
              <span class="span50">2556496549595</span>
              <span class="span50">2556496549595</span>
            </div>
            <p>
              二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
            </p>
          </td>  
          <td style="border-left: 1px dashed #999;">
            <div class="img_box">
              <img src="../images/400.png" alt="">
              <div class="show_img_box">
                <img src="../images/400.png" alt="">
              </div>
            </div>
          </td>
          <td>
            <div>
              <span class="span50">41546486496</span>
              <span class="span50">4659659958</span>
            </div>
            <p>
              二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
            </p>
          </td>
          <td>
            <a href="javascript:;" class="button border-main single_huan"> 换</a>
          </td>
        </tr>
        <tr>
          <td colspan="10"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>
        </tr>
        -->
      </table>
    </div>
   </div>
  <div class="commodity_relation_box">
    <div class="new_commodity_relation">
      <div class="commodity_relation_title">
        <span>批量处理商品对应关系</span>
        <div class="commodity_relation_delbtn">×</div>
      </div>
      <div class="commodity_relation_content">
        <div class="commodity_relation_top">
          <h3>在这里，可以更快捷地搜索并替换或新建对应的系统商品。</h3>
          <div class="jgPng"></div>
          <a href="javascript:;">点击查看教程</a>
          <div class="commodity_zdpp">
            <a href="javascript:;" class="button border-main">按编码自动匹配</a>
            <br>
            <span>线上商品编码＝系统商品编码 则自动关联</span>
          </div>
        </div>
        <div class="commodity_relation_middle">
          <table>
             <!--
            <tr>
              <th width="50">序号</th>
              <th>线上店铺</th>
              <th width="240">
                <span class="span50">线上商品编码/名称</span>
                <span class="span50">线上商品规格</span>
              </th>
              <th width="125">对应系统商品编码</th>
              <th>对应系统商品名称</th>
              <th>对应系统商品规格</th>
              <th width="64">取消</th>
            </tr>          
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <div class="spbm">
                  <input type="text" value="T35335697">
                  <div class="fdjPng search_commodity"></div>
                </div>
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场
                </p>
              </td>
              <td>
                <p>时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <div class="spbm">
                  <input type="text" value="T35335697">
                  <div class="fdjPng search_commodity"></div>
                </div>
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场
                </p>
              </td>
              <td>
                <p>时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <div class="spbm">
                  <input type="text" value="T35335697">
                  <div class="fdjPng search_commodity"></div>
                </div>
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场
                </p>
              </td>
              <td>
                <p>时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <div class="spbm">
                  <input type="text" value="T35335697">
                  <div class="fdjPng search_commodity"></div>
                </div>
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场
                </p>
              </td>
              <td>
                <p>时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <div class="spbm">
                  <input type="text" value="T35335697">
                  <div class="fdjPng search_commodity"></div>
                </div>
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场
                </p>
              </td>
              <td>
                <p>时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <div class="spbm">
                  <input type="text" value="T35335697">
                  <div class="fdjPng search_commodity"></div>
                </div>
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场
                </p>
              </td>
              <td>
                <p>时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <div class="spbm">
                  <input type="text" value="T35335697">
                  <div class="fdjPng search_commodity"></div>
                </div>
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场
                </p>
              </td>
              <td>
                <p>时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <div class="spbm">
                  <input type="text" value="T35335697">
                  <div class="fdjPng search_commodity"></div>
                </div>
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场
                </p>
              </td>
              <td>
                <p>时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <div class="spbm">
                  <input type="text" value="T35335697">
                  <div class="fdjPng search_commodity"></div>
                </div>
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场
                </p>
              </td>
              <td>
                <p>时代广场时代广场时代广场时代广场时代广场时代广场时代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
             -->
          </table>
        </div>
        <div class="commodity_relation_btn">
          <a href="javascript:;" class="button border-main commodity_relation_bc"> 保存</a>
          <a href="javascript:;" class="button border-main commodity_relation_qx"> 取消</a>
        </div>
      </div>
    </div>
  </div>
  <div class="hs_code_box">
    <div class="new_hs_code">
      <div class="hs_code_title">
        <span>选择系统商品</span>
        <div class="hs_code_delbtn">×</div>
      </div>
      <div class="hs_code_content">
        <div class="hs_code_keyword">
          <span>关键字：&nbsp;</span>
          <input type="text" placeholder="商品编码/商品名称/商品规格，多个请用分号隔开。" class="xzy_SecondOnBlur">
        </div>
        <div class="hs_code_table">
          <table id="tableLimits"> 
          <!-- 
            <tr>
              <th width="5">&nbsp;</th>
              <th width="19">
                商品编码
              </th>
              <th width="19">
                商品名称
              </th>
              <th width="19">
                商品规格
              </th>
              <th width="19">
                商品价格
              </th>
              <th width="19">
                单位
              </th>
            </tr>
            <tr>
              <td>
                <input type="radio" name="NumOne">
              </td>
              <td>
                <span class="span">T4654656565</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                ￥100000.00
              </td>
              <td>
                人民币
              </td>
            </tr>
            <tr>
              <td>
                <input type="radio" name="NumOne">
              </td>
              <td>
                <span class="span">T4654656565</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                ￥100000.00
              </td>
              <td>
                人民币
              </td>
            </tr>
            <tr>
              <td>
                <input type="radio" name="NumOne">
              </td>
              <td>
                <span class="span">T4654656565</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                ￥100000.00
              </td>
              <td>
                人民币
              </td>
            </tr>
            <tr>
              <td>
                <input type="radio" name="NumOne">
              </td>
              <td>
                <span class="span">T4654656565</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                ￥100000.00
              </td>
              <td>
                人民币
              </td>
            </tr>
            <tr>
              <td>
                <input type="radio" name="NumOne">
              </td>
              <td>
                <span class="span">T4654656565</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                ￥100000.00
              </td>
              <td>
                人民币
              </td>
            </tr>
            <tr>
              <td>
                <input type="radio" name="NumOne">
              </td>
              <td>
                <span class="span">T4654656565</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                ￥100000.00
              </td>
              <td>
                人民币
              </td>
            </tr>
            <tr>
              <td>
                <input type="radio" name="NumOne">
              </td>
              <td>
                <span class="span">T4654656565</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                ￥100000.00
              </td>
              <td>
                人民币
              </td>
            </tr>
            <tr>
              <td>
                <input type="radio" name="NumOne">
              </td>
              <td>
                <span class="span">T4654656565</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                ￥100000.00
              </td>
              <td>
                人民币
              </td>
            </tr>
            <tr>
              <td>
                <input type="radio" name="NumOne">
              </td>
              <td>
                <span class="span">T4654656565</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
              </td>
              <td>
                ￥100000.00
              </td>
              <td>
                人民币
              </td>
            </tr>
            <tr>
              <td colspan="10"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>
            </tr>
             -->
          </table>
        </div>
        <div class="hs_code_btn">
          <a href="javascript:;" class="button border-main hs_code_bc" id="xzy_keeps"> 保存</a>
        </div>
      </div>
    </div>
  </div>
  <div class="mass_update_box">
    <div class="new_mass_update">
      <div class="mass_update_title">
        <span>批量更新系统商品信息</span>
        <div class="mass_update_delbtn">×</div>
      </div>
      <div class="mass_update_content">
        <div class="mass_update_top">
          <div style="float: left;">
            <h3 style="padding-bottom: 5px;"><b>覆盖更新</b>一下列表中的<b>系统商品信息？</b></h3>
            <br><span >
            *系统将按对应关系将线上商品的商品名称、商品规格、商品图片等信息覆盖更新到系统商品
          </span>
          </div>
          <div class="commodity_zdpp">
            <a href="javascript:;" class="button border-main">更新系统商品信息</a>
          </div>
        </div>
        <div class="mass_update_middle">
          <table>
            <tr>
              <th width="50">序号</th>
              <th>线上店铺</th>
              <th width="240">
                <span class="span50">线上商品编码/名称</span>
                <span class="span50">线上商品规格</span>
              </th>
              <th>
                <span class="span50">对应系统商品编码/名称</span>
                <span class="span50">对应系统商品规格</span>
              </th>
              <th width="64">取消</th>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <span class="span50">6596596565965965</span>
                <span class="span50">代广场时代广场时代广场</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <span class="span50">6596596565965965</span>
                <span class="span50">代广场时代广场时代广场</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <span class="span50">6596596565965965</span>
                <span class="span50">代广场时代广场时代广场</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <span class="span50">6596596565965965</span>
                <span class="span50">代广场时代广场时代广场</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <span class="span50">6596596565965965</span>
                <span class="span50">代广场时代广场时代广场</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <span class="span50">6596596565965965</span>
                <span class="span50">代广场时代广场时代广场</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <span class="span50">6596596565965965</span>
                <span class="span50">代广场时代广场时代广场</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
            <tr>
              <td>
                1
                <div class="dianpu">
                  <img src="../images/dp.png" alt="">
                </div>  
              </td>
              <td>
                <p>
                  时代广场时代广场时代广场时代广场
                </p>
                <span>265965965</span>
              </td>
              <td>
                <span class="span50">&nbsp;</span>
                <span class="span50">65965965</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td style="border-left: 1px dashed #999;">
                <span class="span50">6596596565965965</span>
                <span class="span50">代广场时代广场时代广场</span>
                <p>时代广场时代广场时代广场时代广场代广场时代广场代广场时代广场</p>
              </td>
              <td>
                <a href="javascript:;" class="button border-red"> 取消</a>
              </td>
            </tr>
          </table>
        </div>
        <div class="mass_update_btn">
          <a href="javascript:;" class="button border-main mass_update_bc"> 保存</a>
          <a href="javascript:;" class="button border-main mass_update_qx"> 取消</a>
        </div>
      </div>
    </div>
  </div>
  <div class="single_updating_box">
    <div class="new_single_updating">
      <div class="single_updating_title">
        <span>选择或新建商品</span>
        <div class="single_updating_delbtn">×</div>
      </div>
      <div class="single_updating_content">
        <div class="single_updating_list_btn_box">
          <span class="active" id="pagesOne">
            选择已有
          </span>
          <span id="pagesTwo">
            新建
          </span>
        </div>
        <div class="single_updating_list">
          <div class="single_updating_keyword">
            <span>关键字：&nbsp;</span>
            <input type="text" placeholder="商品编码/商品名称/商品规格。" class="xzy_onBlur">
          </div>
          <div class="single_updating_table">                 
            <table id="xzy_limits">
              <!-- 
              <tr>
                <th width="5">&nbsp;</th>
                <th width="19">
                  商品编码
                </th>
                <th width="19">
                  商品名称
                </th>
                <th width="19">
                  商品规格
                </th>
                <th width="19">
                  商品价格
                </th>
                <th width="19">
                  单位
                </th>
              </tr>
              <tr>
                <td>
                  <input type="radio" name="NumOne">
                </td>
                <td>
                  <span class="span">T4654656565</span>
                </td>
                <td>
                  <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
                </td>
                <td>
                  <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
                </td>
                <td>
                  ￥100000.00
                </td>
                <td>
                  人民币
                </td>
              </tr>
              <tr>
                <td>
                  <input type="radio" name="NumOne">
                </td>
                <td>
                  <span class="span">T4654656565</span>
                </td>
                <td>
                  <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
                </td>
                <td>
                  <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
                </td>
                <td>
                  ￥100000.00
                </td>
                <td>
                  人民币
                </td>
              </tr>
              <tr>
                <td>
                  <input type="radio" name="NumOne">
                </td>
                <td>
                  <span class="span">T4654656565</span>
                </td>
                <td>
                  <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
                </td>
                <td>
                  <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
                </td>
                <td>
                  ￥100000.00
                </td>
                <td>
                  人民币
                </td>
              </tr>
              <tr>
                <td>
                  <input type="radio" name="NumOne">
                </td>
                <td>
                  <span class="span">T4654656565</span>
                </td>
                <td>
                  <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
                </td>
                <td>
                  <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
                </td>
                <td>
                  ￥100000.00
                </td>
                <td>
                  人民币
                </td>
              </tr>
              <tr>
                <td>
                  <input type="radio" name="NumOne">
                </td>
                <td>
                  <span class="span">T4654656565</span>
                </td>
                <td>
                  <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
                </td>
                <td>
                  <span class="span">咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻咻</span>
                </td>
                <td>
                  ￥100000.00
                </td>
                <td>
                  人民币
                </td>
              </tr>
              <tr>
                <td colspan="10"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>
              </tr>
              --> 
            </table>          
          </div>
          <div class="single_updating_btn">
            <a href="javascript:;" class="button border-main single_updating_bc" id="xzy_save"> 保存</a>
          </div>
        </div>
        <div class="single_updating_list">
          <ul class="management_content">
            <li>
                    <ol>
                        <li class="bfz33">
                            <span>商品编码:</span>
                            <b>*</b>
                            <input type="text" id="erpItemNum">
                        </li>
                        <li class="bfz33">
                            <span>商品类目:</span>
                            <input type="text" id="category">
                        </li>
                        <li class="bfz33">
                            <span>商品品牌:</span>
                            <input type="text" id="brand">
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz66">
                            <span>商品名称:</span>
                            <b>*</b>
                            <input type="text" id="name">
                        </li>
                        <li class="bfz33">
                            <span>商品简称:</span>
                            <input type="text" id=shortName>
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz33">
                            <span>商品规格:</span>
                            <input type="text" id="spec">
                        </li>
                        <li class="bfz33">
                            <span>颜色:</span>
                            <input type="text" id="color">
                        </li>
                        <li class="bfz33">
                            <span>尺码:</span>
                            <input type="text" id="size">
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz33">
                            <span>商品价格:</span>
                            <input type="text" id="normalPrice">
                        </li>
                        <li class="bfz33">
                            <span>成本价:</span>
                            <input type="text" id="costPrice">
                        </li>
                        <li class="bfz33">
                            <span>商品条码:</span>
                            <input type="text" id="barCode">
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz33">
                            <span>商品包装:</span>
                            <input type="text" id="packageCondition">
                        </li>
                        <li class="bfz33">
                            <span>长*宽*高:</span>
                            <input type="text" class="ckg" id="length">
                            <input type="text" class="ckg" id="width">
                            <input type="text" class="ckg" id="height">
                        </li>
                        <li class="bfz33">
                            <span>商品重量:</span>
                            <input type="text" class="ckg" id="weight">kg
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz33">
                            <span>商品单位:</span>
                            <input type="text" id="unit">
                        </li>
                        <li class="bfz33">
                            <span>商品批次:</span>
                            <input type="text" id="batchCode">
                        </li>
                        <li class="bfz33">
                            <span>保质期:</span>
                            <input type="text" id="expireDate">
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz33">
                            <span>季节:</span>
                            <input type="text">
                        </li>
                        <li class="bfz33">
                            <span>款号:</span>
                            <input type="text" id="styleCode">
                        </li>
                        <li class="bfz33">
                            <span>系统分类:</span>
                            <div style="" class='select_simulate'>
                                <span>请设置商品的分类</span>
                                <div class="select_simulate_xb"></div>
                                <ul class="select_simulate_box">
                                    
                                </ul>
                            </div>
                        </li>
                    </ol>
                </li>
                <li style="margin: 10px 0">
                    <ol>
                        <li class="bfz33">
                            <span>自定义1:</span>
                            <input type="text" id="reserved1">
                        </li>
                        <li class="bfz33">
                            <span>自定义2:</span>
                            <input type="text" id="reserved2">
                        </li>
                        <li class="bfz33">
                            <span>自定义3:</span>
                            <input type="text" id="reserved3">
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li>
                            <span>备注:</span>
                            <textarea name="note" id="note" cols="30" rows="10"></textarea>
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li>
                            <span>其他:</span>
                        </li>
                        <li>
                            <div class="fl_div">
                                <input type="checkbox" name="presell" value="0">
                                <span>预售</span>
                            </div>
                            <div class="fl_div">
                                <input type="checkbox" name="commissionSell" value="0">
                                <span>代售</span>
                            </div>
                            <div class="fl_div">
                                <input type="checkbox" name="gift" value="0">
                                <span>赠品</span>
                            </div>
                        </li>
                    </ol>
                </li>
                <li class="management_tj">
                    <a href="javascript:;" class="button border-main management_bc" id="xzy_saves"> 保存</a>
                    <a href="javascript:;" class="button border-main management_qx"> 取消</a>
                </li>                     
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="manual_sync_box">
    <div class="new_manual_sync">
      <div class="manual_sync_title">
        <span>商品手工同步</span>
        <div class="manual_sync_delbtn">×</div>
      </div>
      <div class="manual_sync_content">
        <div class="manual_sync_keyword">
          <span>店铺：&nbsp;</span>
          <select name="" id="">
            <option value="1">商店1</option>
            <option value="2">商店2</option>
          </select>
        </div>
        <div class="ts">
          点击同步所有商品信息
        </div>
        <div class="manual_sync_btn">
          <a href="javascript:;" class="button border-main"> 同步</a>
        </div>
      </div>
    </div>
  </div>
  <script src="../js/commodity_relation.js"></script>
</body>
<script>
  var boxHeight=document.querySelector('.div_big_box').offsetHeight;
  var commodity_relation_box=document.querySelector(".commodity_relation_box");
  blackHeight(commodity_relation_box,boxHeight);
  var hs_code_box=document.querySelector(".hs_code_box");
  blackHeight(hs_code_box,boxHeight);
  var mass_update_box=document.querySelector(".mass_update_box");
  blackHeight(mass_update_box,boxHeight);
  var single_updating_box=document.querySelector(".single_updating_box");
  blackHeight(single_updating_box,boxHeight);
  var manual_sync_box=document.querySelector(".manual_sync_box");
  blackHeight(manual_sync_box,boxHeight);
</script>
</html>