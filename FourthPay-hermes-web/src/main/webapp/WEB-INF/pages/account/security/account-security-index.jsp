<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="欧飞网充值卡寄售平台" />
	<meta name="keywords" content="欧飞网充值卡寄售平台" />
	<title>我的账户 － 安全设置 - 欧飞网充值卡寄售平台</title>
    <script>
        $(document).ready(function () {
            // tab 高亮
            bindTab("account", "account_3");
        });
    </script>
</head>
<body>
	<div class="content">
		<div class="accInfo w1200">
			<h3>保护资金安全</h3><!-- 未修改密码状态时，p加no_on的class，保护中hide，tip显示 -->
			<p class="security <c:if test="${user.logdays >= 30}">no_on</c:if>"><s></s>
                <c:choose>
                    <c:when test="${user.logdays >= 30}"><span class="tip">您已经${user.logdays}天未修改登录密码</span></c:when>
                    <c:otherwise><span class="on">保护中</span></c:otherwise>
                </c:choose>
                <strong>登录密码</strong>
                <span class="timeTip">上次登录时间：${user.lasttime}</span>
                <a href="/account/security/logpsw">修改</a>
            </p>
			<p class="security"><s></s>
                <span class="on">保护中</span><strong>提现密码</strong>
                <span class="timeTip">提现时账户信息时输入，保护账户资金安全</span>
                <a href="/account/security/paypsw">修改</a>
            </p>
			<p class="security <c:if test="${!empty user.userfax && user.busdays >= 30}">no_on</c:if> <c:if test="${empty user.userfax}">no_on</c:if>"><s></s>
                <c:choose>
                    <c:when test="${!empty user.userfax && user.busdays >= 30}"><span class="tip">您已经${user.busdays}天未修改交易密码</span></c:when>
                    <c:when test="${empty user.userfax}"><span class="tip">请设置交易密码</span></c:when>
                    <c:otherwise><span class="on">保护中</span></c:otherwise>
                </c:choose>
                <strong>交易密码</strong>
                <span class="timeTip">交易安全码，保护账户安全</span>
                <c:if test="${!empty user.userfax}"><a href="/account/security/buspsw">修改</a></c:if>
                <c:if test="${empty user.userfax}"><a href="/account/security/buspsw">添加</a></c:if>
            </p>
		</div>
	</div>
</body>
</html>