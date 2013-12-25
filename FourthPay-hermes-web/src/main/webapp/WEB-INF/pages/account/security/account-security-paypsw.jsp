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
                    paypwsBefore: {
                        required: true,
                        rangelength: [6,19]
                    },
                    paypwsNew: {
                        required: true,
                        rangelength: [6,19]
                    },
                    paypwsNewAgain: {
                        required: true,
                        rangelength: [6,19]
                    }
                },
                messages: {
                    paypwsBefore: {
                        required: "请输入原密码",
                        rangelength: "请输入长度在6-19个字符的密码"
                    },
                    paypwsNew: {
                        required: "请输入新密码",
                        rangelength: "请输入长度在6-19个字符的密码"
                    },
                    paypwsNewAgain: {
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
        });
    </script>
</head>
<body>
<form id="accountForm" name="accountForm" action="/account/security/paypsw" method="POST" autocomplete="off">
	<div class="content">
		<div class="accInfo w1200">
			<h3>提现密码修改</h3>
            <c:if test="${!empty errMsg}">
                <p class="msg label">${errMsg}</p>
            </c:if>
			<p class="pisive">
                <label for="">当前密码：</label>
                <input type="password" id="paypwsBefore" name="paypwsBefore" class="accInput">
                <span class="hide" id="paypwsBeforeTip"><i></i><i class="border"></i>
                    <span></span>
                </span>
			</p>
			<p class="pisive">
                <label for="">新密码：</label>
                <input type="password" id="paypwsNew" name="paypwsNew" class="accInput">
                <span class="hide" id="paypwsNewTip"><i></i><i class="border"></i>
                    <span></span>
                </span>
			</p>
			<p class="pisive">
                <label for="">确认新密码：</label>
                <input type="password" name="paypwsNewAgain" class="accInput">
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
            elem: '#paypwsBefore',
            onFocus: '用户名长度在6-19个字符之间（格式）只能由中文、英文、数字及及"_"、"-"组成；'
        });
        validateChkFun.defaultChk({
            elem: '#paypwsNew',
            onFocus: '用户名长度在6-19个字符之间（格式）只能由中文、英文、数字及及"_"、"-"组成；'
        });
    });
</script>
</body>
</html>