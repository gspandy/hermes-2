<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="欧飞网充值卡寄售平台" />
	<meta name="keywords" content="欧飞网充值卡寄售平台" />
	<title>我的账户 － 编辑安全设置 - 欧飞网充值卡寄售平台</title>
    <script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/error.css"/>
    <script type="text/javascript" src="${ctx}/js/validateform.js"></script>
    <script>
        $(document).ready(function () {
            // tab 高亮
            bindTab("account", "account_3");
            // 表单验证
            var $validator = $("#accountForm").validate({
                rules: {
                    logpswBefore: {
                        required: true,
                        rangelength: [6,19]
                    },
                    logpswNew: {
                        required: true,
                        rangelength: [6,19]
                    },
                    logpswNewAgain: {
                        required: true,
                        rangelength: [6,19]
                    }
                },
                messages: {
                    logpswBefore: {
                        required: "请输入原密码",
                        rangelength: "请输入长度在6-19个字符的密码"
                    },
                    logpswNew: {
                        required: "请输入新密码",
                        rangelength: "请输入长度在6-19个字符的密码"
                    },
                    logpswNewAgain: {
                        required: "请重新输入新密码",
                        rangelength: "请输入长度在6-19个字符的密码"
                    }
                },
                errorPlacement: function(error, element) {
                    if ( element.is(":checkbox") )
                        error.appendTo(element.parent().next());
                    else
                        error.insertAfter(element);
                }
            });



            $("#return").bind("click", function(){
                window.location.href = "/account/security";
            });
            randomping();
        });

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

        function show(){

        }

    </script>

</head>
<body>
<form id="accountForm" name="accountForm" action="/account/security/logpsw" method="POST" autocomplete="off">
    <input type="hidden" name="ping" id="ping" />
	<div class="content">
		<div class="accInfo w1200">
			<h3>登录密码修改</h3>
            <c:if test="${!empty errMsg}">
                <p class="msg label">${errMsg}</p>
            </c:if>
            <div id="messageBox" class="msg label"></div>
			<p class="pisive">
                <label for="">当前密码：</label>
                <input type="password" id="logpswBefore" name="logpswBefore" class="accInput" onfocus="show()">
                <span class="hide" id="logpswBeforeTip"><i></i><i class="border"></i>
                    <span></span>
                </span>
            </p>
			<p class="pisive">
                <label for="">新密码：</label>
                <input type="password" id="logpswNew" name="logpswNew" class="accInput">
                <span class="hide" id="logpswNewTip"><i></i><i class="border"></i>
                    <span></span>
                </span>
			</p>
			<p class="pisive">
                <label for="">确认新密码：</label>
                <input type="password" id="logpswNewAgain" name="logpswNewAgain" class="accInput">
			</p>
			<p class="label push30">
				<button type="submit" class="btn_small">保存修改</button>
				<button id="return" type="submit" class="btn_small gray_small" onclick="return false">取消</button>
			</p>
		</div>
	</div>
</form>
<script>
    validateChkFun.init(function() {
        validateChkFun.defaultChk({
            elem: '#logpswBefore',
            onFocus: '用户名长度在6-19个字符之间（格式）只能由中文、英文、数字及及"_"、"-"组成；'
        });
        validateChkFun.defaultChk({
            elem: '#logpswNew',
            onFocus: '用户名长度在6-19个字符之间（格式）只能由中文、英文、数字及及"_"、"-"组成；'
        });
    });
</script>
</body>
</html>