<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content="欧飞网充值卡寄售平台"/>
    <meta name="keywords" content="欧飞网充值卡寄售平台"/>
    <title>我的账户 － 编辑基本资料 - 欧飞网充值卡寄售平台</title>
    <script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/error.css"/>
    <script>
        $(document).ready(function () {
            // tab 高亮
            bindTab("account", "account_2");
            // 返回按钮
            $("#return").bind("click", function(){
                window.location.href = "/account/info";
            });

            jQuery.validator.addMethod("mobile", function(value) {
                var length = value.length;
                return (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(147)|(18[0-9]{1}))+\d{8})$/.test(value));
            }, "手机号格式不正确");

            // 电话号码验证
            jQuery.validator.addMethod("phone", function(value, element) {
                var tel = /^(0[0-9]{2,3})?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
                return this.optional(element) || (tel.test(value));
            }, "电话号码格式不正确");

            // QQ号码验证
            jQuery.validator.addMethod("qq", function(value, element) {
                var tel = /^[1-9]\d{4,9}$/;
                return this.optional(element) || (tel.test(value));
            }, "qq号码格式不正确");

            // 表单验证
            var $validator = $("#accountForm").validate({
                rules: {
                    email: {
                        email: true
                    },
                    usercell: {
                        mobile: true
                    },
                    usertel: {
                        phone: true
                    },
                    qq: {
                        qq: true
                    },
                    siteurl: {
                        url: true
                    }
                },
                messages: {
                    email: {
                        email: "输入的邮箱格式不正确"
                    },
                    usercell: {
                        mobile: "输入的手机号格式不正确"
                    },
                    usertel: {
                        phone: "输入的电话号码格式不正确"
                    },
                    qq: {
                        qq: "输入的qq号码格式不正确"
                    },
                    siteurl: {
                        url: "输入的网址不正确,请输入以http://或者https://开头的网址"
                    }
                },
//                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    if ( element.is(":checkbox") )
                        error.appendTo(element.parent().next());
                    else
                        error.insertAfter(element);
                }
            });
        });
    </script>
</head>
<body>
<form id="accountForm" name="accountForm" action="/account/info/edit" method="POST" autocomplete="off">
<div class="content">
    <div class="accInfo w1200">
        <h3>基本资料</h3>
        <div id="messageBox" class="msg label" style="display:none">输入有误，请先更正。</div>
        <c:if test="${!empty errMsg}">
            <p class="msg label">${errMsg}</p>
        </c:if>
        <p><label for="">登录账号：</label><span>${user.nickname}</span></p>
        <p><label for="">真实姓名：</label><span>${user.username}</span></p>
        <p><label for="">证件号码：</label><span>${user.idno}<span class="orange">（已通过实名认证）</span></span></p>
        <p><label for="">所在地区：</label><span>${user.prvcin}(省) ${user.cityin}</span></p>

        <h3>联系方式</h3>
        <p>
            <label for="">手机号码：</label>
            <input name="usercell" type="text" class="accInput" value="${user.usercell}">
        </p>
        <p>
            <label for="">Email：</label>
            <input name="email" type="text" class="accInput" value="${user.email}">
        </p>
        <p>
            <label for="">联系电话：</label>
            <input name="usertel" type="text" class="accInput" value="${user.usertel}">
        </p>
        <p>
            <label for="">QQ号码：</label>
            <input name="qq" type="text" class="accInput" value="${user.qq}">
        </p>
        <h3>支付资料</h3>

        <p>
            <label for="">公司地址：</label>
            <input name="company" type="text" class="accInput" value="${userAdd.company}">
        </p>

        <p>
            <label for="">网站地址：</label>
            <input name="siteurl" type="text" class="accInput" value="${userAdd.siteurl}">
        </p>

        <p>
            <label for="">备案号：</label>
            <input name="siteicp" type="text" class="accInput" value="${userAdd.siteicp}">
        </p>

        <p>
            <label for="">应用场合介绍：</label>
            <textarea name="companydes" cols="50" rows="5">${userAdd.companydes}</textarea>
        </p>

        <p class="label">
            <button type="submit" class="btn_small">提交</button>
            <button id="return" class="btn_small" onclick="return false">返回</button>
        </p>
    </div>
</div>
</form>
</body>
</html>