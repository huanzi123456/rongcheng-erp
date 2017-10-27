<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>登录</title>

<link rel="stylesheet" href="/css/pintuer.css">
<link rel="stylesheet" href="/css/admin.css">
<script src="/js/jquery.js"></script>
<script src="/js/pintuer.js"></script>
<script src="/util/jiml-utils.js"></script>
<script type="text/javascript">
	$(function() {
		$("input").keydown(qk);
	});
	function qk() {
		$("#err").html("");
	}
	function sub(e) {
		var keynum;
		if (e == "sub") {
			keynum = 13;
		} else if (window.event) { // IE
			keynum = e.keyCode;
		} else if (e.which) { // Netscape/Firefox/Opera
			keynum = e.which;
		}
		if (keynum == 13) {
			loading();
			$.ajax({
				url : "/login.do",
				data : $('#loginForm').serialize(),
				type : "post",
				async : "false",
				dataType : "json",
				success : function(result) {
					if (result.data == 0) {
						window.location.replace("/index.do");
					} else {
						$("#err").html(result.data);
					}
				},
				error : function() {
					$("#err").html("页面异常，请刷新页面重新登录。");
				},
				complete : function() {
					loading(false);
				}
			});
		}
	}
</script>

</head>
<body>
  <div class="bg"></div>
  <div class="container">
    <div class="line bouncein">
      <div class="xs6 xm4 xs3-move xm4-move">
        <div style="height: 150px;"></div>
        <div class="media media-y margin-big-bottom"></div>
        <div class="panel loginbox">
          <div class="text-center margin-big padding-big-top">
            <h1>打印软件登录页面</h1>
          </div>
          <div class="panel-body" style="padding: 30px; padding-bottom: 10px; padding-top: 10px;">
            <form id="loginForm">
              <div>
                <div class="field field-icon-right">
                  <input class="input input-big" name="accountNum" placeholder="账号" /> <span class="icon icon-user margin-small" style=""></span>
                </div>
              </div>
              <br>
              <div>
                <div class="field field-icon-right">
                  <input class="input input-big" name="password" placeholder="密码" type="password" /> <span class="icon icon-key margin-small"></span>
                </div>
              </div>
              <br>
              <div>
                <div class="field">
                  <input class="input input-big" name="yzm" placeholder="验证码" onkeypress="sub(event)" /> <img src="createImg.do" onclick="this.setAttribute('src','createImg.do?x='+Math.random())" alt="验证码" title="点击更换" width="100" height="32" class="passcode" style="height: 43px; cursor: pointer;" />
                </div>
              </div>
            </form>
            <span style="color: red; display: block; height: 20px;" id="err"></span>
          </div>
          <div>
            <input type="button" id="login" class="button button-block bg-main text-big input-big" value="登录" style="margin-bottom: 10px;" onclick="sub('sub')">
          </div>
          <a href="sign.do">如果没有账号请点击这里<span style="color: red;">注册</span></a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>