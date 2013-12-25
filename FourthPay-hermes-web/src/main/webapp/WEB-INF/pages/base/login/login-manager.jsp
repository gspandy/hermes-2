<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="欧飞网充值卡寄售平台" />
	<meta name="keywords" content="欧飞网充值卡寄售平台" />
	<title>已登录首页 - 欧飞网充值卡寄售平台</title>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="http://pic.ofcard.com/jslib/jquery/plugin/cycle/jquery.cycle.all.js"></script>
    <script src="/css/style.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <script type="text/javascript">
        $(document).ready(function(){
            $("#oufeihead ul li").removeClass("current");
            $("#li1").addClass("current");
        });
    </script>
</head>
<body>
    <jsp:include page="/static/inc/header.jsp" />
	<div class="content">
		<div class="slides">
			<div class="slideCon">
				<a href="" style="background-image:url(${ctx}/images/banner01.jpg)"><span></span></a>
				<a href="" style="background-image:url(${ctx}/images/banner02.jpg)"><span></span></a>
				<a href="" style="background-image:url(${ctx}/images/banner03.jpg)"><span></span></a>
			</div>
		</div> <!-- end slides -->
        <div class="loginCon w1200">
            <form action="" id="loginform" class="transparent-white">
                <p class="loginInfo">尊敬的<span class="blue">${username}</span>,您好 <a href="/logout">注销</a></p>
                <p><button type="button" class="loginBtn loginBtn01" onclick="window.location.href='/account'">进入账户中心</button></p>
                <c:if test="${!empty userflag && userflag== 1}"><p><button type="button" class="loginBtn loginBtn02" onclick="window.location.href='/card/single'">寄售充值卡</button></p></c:if>
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