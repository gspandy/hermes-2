<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="欧飞网充值卡寄售平台" />
	<meta name="keywords" content="欧飞网充值卡寄售平台" />
	<title>首页 - 欧飞网充值卡寄售平台</title>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="http://pic.ofcard.com/jslib/jquery/plugin/cycle/jquery.cycle.all.js"></script>
    <script src="/css/style.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/BigInt.js"></script>
    <script type="text/javascript" src="/js/RSA.js"></script>
    <script type="text/javascript" src="/js/Barrett.js"></script>
    <script type="text/javascript">
        function placeholderSupport() {
            return 'placeholder' in document.createElement('input');
        }

        function boundrandom(n, bound) {
            var rnd = "";
            for ( var i = 0; i < n; i++) {
                if (i > 0)
                    rnd += ",";
                rnd += parseInt(Math.random() * bound);
            }
            return rnd;
        }

        function randomping() {
            var randstr = boundrandom(10, 62);
            var BaseStr = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
            var str = "";
            var randarr = randstr.split(",");
            for ( var i = 0; i < randarr.length; i++) {
                str += BaseStr.substring(randarr[i],
                        parseInt(randarr[i]) + 1);
            }
            $("#ping").val(str);
        }

        function fEvent(sType,oInput){
            switch (sType){
                case "focus" :
                    oInput.isfocus = true;
                    oInput.style.backgroundColor='#FFFFD8';
                case "mouseover" :
                    oInput.style.borderColor = '#FFE222';
                    break;
                case "blur" :
                    oInput.isfocus = false;
                    oInput.style.backgroundColor="";
                case "mouseout" :
                    if(!oInput.isfocus){
                        oInput.style.borderColor='#CCCCCC';
                    }
                    break;
            }
        }

        var VerifyCodeTimes=1;
        function refreshmvp(){
            $("#cimg").attr("src", "/captcha/image?"+(VerifyCodeTimes++));
        }

        $(document).ready(function() {
            $("#oufeihead ul li").removeClass("current");
            $("#li1").addClass("current");

            if(!placeholderSupport()){   // 判断浏览器是否支持 placeholder
                $('[placeholder]').focus(function() {
                    var input = $(this);
                    if (input.val() == input.attr('placeholder')) {
                        input.val('');
                        input.css("color","#000000");
                        input.removeClass('placeholder');
                    }
                }).blur(function() {
                            var input = $(this);
                            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                                input.addClass('placeholder');
                                input.css("color","#bbbbbb");
                                input.val(input.attr('placeholder'));
                            }
                        }).blur();
            };

            $("#loginform").submit(function(event) {
                randomping();
                var ping = $("#ping").val();
                var password = $("#password").val();
                setMaxDigits(67);
                var key = new RSAKeyPair(
                        "22c29d5bda305e5daa0920b86cd410844a7aead043cfc3f78af2166e86e26eb",
                        "",
                        "102e05ea9849d82b2630523751c1cd1984e92d3b45bf8d1ba0d67b55b0119eb9");
                var newpwd = encryptedString(key, password + ping);
                if (newpwd.length < 32) {
                    return false;
                }
                $("#password").val(newpwd);
            });
        });
    </script>
</head>
<body>
    <jsp:include page="/static/inc/header.jsp" />
	<div class="content">
		<div class="slides">
			<div class="slideCon">
				<a href="javascript:void(0)" style="background-image:url(/images/banner01.jpg);cursor:default;"><span></span></a>
				<a href="javascript:void(0)" style="background-image:url(/images/banner02.jpg);cursor:default;"><span></span></a>
				<a href="javascript:void(0)" style="background-image:url(/images/banner03.jpg);cursor:default;"><span></span></a>
			</div>
		</div> <!-- end slides -->
		<div class="loginCon w1200">
			<form action="" id="loginform" class="transparent-white" method="POST" autocomplete="off">
                <input type="hidden" name="ping" id="ping" />
                <input type="hidden" name="xsrf" value="1"/>
				<p class="loginTitle">欧飞商户</p>
                <c:if test="${resultState eq 'fail'}">
                    <p id="errMsg" class="error">${errMsg}</p>
                </c:if>
				<p>
                    <input id="username" name="username" type="text" placeholder="用户名" autofocus required>				</p>
				<p>
                    <input id="password" name="password" type="password" maxlength="128" placeholder="密码" required>
		        </p>
				<p><!-- input输入正确加class＝"correct",否则加"error" -->
                    <input type="text" class="Ccode" name="captcha" id="captcha" placeholder="验证码"
                           maxlength="10"  required/>&nbsp;&nbsp;
                    <img id="cimg" src="/captcha/image" onclick="refreshmvp();"
                         style="width:85px; height: 38px; cursor: hand; clear: both;"
                         title="看不清？点击更换另一个。" />&nbsp;&nbsp;
                    <a href="javascript:void(0)" class="CcodeRef" onclick="refreshmvp();" title="看不清，点击换一张"></a>
		        </p>
				<p><button type="submit" id="loginBtn" class="loginBtn">登录</button></p>
		        <p class="label">
                  <a href="join.html">忘记登录密码？</a>&nbsp;&nbsp;
		        	<a href="/register">免费注册</a>
		        </p>
	        </form>
		</div> <!-- end loginCon -->
		<div class="indexInfo w1200">
			<dl class="indexInfo01">
				<dt></dt>
				<dd>
					<p class="strong">快速消耗，安全省心</p>
					<p>30秒极速消耗</p>
					<p>API自助接入，实现无缝连接</p>
				</dd>
			</dl>
			<dl class="indexInfo02">
				<dt></dt>
				<dd>
					<p class="strong">无费率提现，安全速达</p>
					<p>7&lowast;24小时随时随地提现</p>
					<p>360&deg;安全保护您的财产安全</p>
				</dd>
			</dl>
			<dl class="indexInfo03">
				<dt></dt>
				<dd>
					<p class="strong">7&lowast;24小时贴心客服</p>
					<p>专业的技术支持</p>
					<p>贴心的解决问题</p>
				</dd>
			</dl>
		</div>
	</div>
    <jsp:include page="/static/inc/footer.jsp" />
    <div class="online">
        <s></s><a target="_blank" href="tencent://message/?uin=2355617715&amp;Site=在线咨询&amp;Menu=yes">在线咨询</a>
    </div>
</body>
</html>