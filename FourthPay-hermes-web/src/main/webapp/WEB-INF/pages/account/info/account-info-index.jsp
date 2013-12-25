<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="欧飞网充值卡寄售平台" />
	<meta name="keywords" content="欧飞网充值卡寄售平台" />
	<title>我的账户 － 基本资料 - 欧飞网充值卡寄售平台</title>
    <script>
        $(document).ready(function () {
            // tab 高亮
            bindTab("account", "account_2");

            $("#edit").bind("click", function(){
                window.location.href = "/account/info/edit";
            });
        });
    </script>
</head>
<body>
	<div class="content">
		<div class="accInfo w1200">
			<h3>基本资料</h3>
			<p><label for="">登录账号：</label><span>${user.nickname}</span></p>
			<p><label for="">真实姓名：</label><span>${user.username}</span></p>
			<p><label for="">证件号码：</label><span>${user.idno}<span class="orange">（已通过实名认证）</span></span></p>
			<p><label for="">所在地区：</label><span>${user.prvcin}(省) ${user.cityin}</span></p>
			<h3>联系方式</h3>
			<p><label for="">手机号码：</label><span>${user.usercell}</span></p>
			<p><label for="">Email：</label><span>${user.email}</span></p>
			<p><label for="">联系电话：</label><span>${user.usertel}</span></p>
			<p><label for="">QQ号码：</label><span>${user.qq}</span></p>
			<h3>支付资料</h3>
			<p><label for="">公司地址：</label><span>${userAdd.company}</span></p>
			<p><label for="">网站地址：</label><span>${userAdd.siteurl}</span></p>
			<p><label for="">备案号：</label><span>${userAdd.siteicp}</span></p>
			<p><label for="">应用场合介绍：</label><span>${userAdd.companydes}</span></p>
			<p class="label">
				<button id="edit" class="btn_small">编辑</button>
				<%--<button type="submit" class="btn_small gray_small">返回</button>--%>
			</p>
		</div>
	</div>
</body>
</html>