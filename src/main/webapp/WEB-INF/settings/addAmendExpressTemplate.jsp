<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>打印</title>
    <link rel="stylesheet" href="/css/expressTemplate.css">
    <link rel="stylesheet" href="/css/pintuer.css">
    <link rel="stylesheet" href="/css/admin.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/pintuer.js"></script> 
    <script src="/js/template.js"></script>
    <script src="/util/jiml-utils.js"></script>
    <script src="/util/templateJson.js"></script>
    <script src="/util/cookie_util.js"></script>
    <script src="/zb/settings/addAmendExpressTemplate.js"></script>
</head>
<body>
<div class="panel admin-panel" style="margin-bottom: 20px;overflow:hidden;">
    <div class="panel-head"><strong class="icon-reorder" id="addAmendExpressTemplate_title"></strong></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li>模板名称：</li>
        <li>
          <input type="text" placeholder="请输入模板名称" name="templateName" class="input" style="width:240px; line-height:17px;display:inline-block" />
        </li>
        <li>面单大小：</li>
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
            <select class="input" style="width:100px;" id="addAmendExpressTemplate_printChoice">
              <!-- 加载打印机 -->
            </select>
        </li>
        <li>打印位置调整：</li>
        <li>
            <span>左移</span>
            <input type="text" name="xCoordinate"  class="input" style="width:50px; line-height:17px;display:inline-block">
            <span>毫米</span>
            <span style="margin-left: 40px;">上移</span>
            <input type="text" name="yCoordinate"  class="input" style="width:50px; line-height:17px;display:inline-block">
            <span>毫米</span>
            <button class="button border-blue" style="margin-left:20px;" id="addAmendExpressTemplate_move"><span class="icon-check"></span> 确认移动 </button>
        </li>
      </ul>
    </div>
    <ul class="left-box" id="addAmendExpressTemplate_list">
        <%--加载列表--%>
    </ul>
    <div class="content">
        <div class="content-box"  id="addAmendExpressTemplate_background">
            <img src="" alt="请插入正确的照片格式" draggable="false">
        </div>
        <div class="panel admin-panel" style="border:none;">
            <div class="body-content">
                 <div class="form-x">  
                    <div class="form-group">
                        <div class="field">
                            <button class="button bg-main" id="addAmendExpressTemplate_commit"> 提交</button>
                            <button class="button border-blue" id="addAmendExpressTemplate_goback"> 返回</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%--<script src="/js/expressTemplate.js"></script>--%>
</html>
