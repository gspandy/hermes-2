<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="第四方支付" />
	<meta name="keywords" content="第四方支付" />
	<title>帮助中心 - 第四方支付</title>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="http://pic.ofcard.com/jslib/jquery/plugin/cycle/jquery.cycle.all.js"></script>
    <script src="/css/style.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/BigInt.js"></script>
    <script type="text/javascript" src="/js/RSA.js"></script>
    <script type="text/javascript" src="/js/Barrett.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#oufeihead ul li").removeClass("current");
            $("#li4").addClass("current");
        });
    </script>
</head>
<body>
    <jsp:include page="/static/inc/header.jsp" />
	<div class="content">
        <div class="banner">
            <a href="" class="ban ban03"></a>
        </div> <!-- end banner -->
		<div class="conInfo w1200 clearfix">
			<div class="infoLeft">
				<h3>帮助中心</h3>
				<ul class="helpList">
					<li class="on"><a href="">注册、登录与审核<em class="to-right"></em><em class="to-rightr"></em></a></li>
					<li><a href="">提交卡密流程<em class="to-right"></em><em class="to-rightr"></em></a></li>
					<li><a href="">提现申请流程<em class="to-right"></em><em class="to-rightr"></em></a></li>
					<li><a href="">常见问题及解答<em class="to-right"></em><em class="to-rightr"></em></a></li>
				</ul>
			</div>
			<div class="infoRight">
				<h3>注册、登录与审核</h3>
				<div class="block">
					<p>注册、登录与审核注册、登录与审核注册、登录与审核注册、登录与审核注册、登录与审核注册、登录与审核注册、登录与审核注册、登录与审核注册、登录与审核注册、登录与审核注册、登录与审核注册、登录与审核</p>
				</div>
			</div>
		</div>
	</div>
    <jsp:include page="inc/footer.jsp" />
</body>
</html>