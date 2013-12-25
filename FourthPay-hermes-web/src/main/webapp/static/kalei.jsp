<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="第四方支付" />
	<meta name="keywords" content="第四方支付" />
	<title>支持卡类 - 第四方支付</title>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="http://pic.ofcard.com/jslib/jquery/plugin/cycle/jquery.cycle.all.js"></script>
    <script src="/css/style.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/BigInt.js"></script>
    <script type="text/javascript" src="/js/RSA.js"></script>
    <script type="text/javascript" src="/js/Barrett.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#oufeihead ul li").removeClass("current");
            $("#li2").addClass("current");
        });
    </script>
</head>
<body>
   <jsp:include page="/static/inc/header.jsp" />
	<div class="content">
        <div class="banner">
            <a href="/register" class="ban ban01"></a>
        </div> <!-- end banner -->
		<div class="conInfo w1200">
			<h1>手机充值系列<em class="to-bottom"></em><em class="to-bottomb"></em></h1>
			<dl class="card card01">
				<dt class="dt_card01"></dt>
				<dd>
					<p class="strong">移动充值卡</p>
					<p>面值：10元、20元、30元、50元、100元、200元、300元、500元</p>
				</dd>
			</dl>
			<dl class="card card02">
				<dt class="dt_card02"></dt>
				<dd>
					<p class="strong">联通充值卡</p>
					<p>面值：10元、20元、30元、50元、100元、300元、500元</p>
				</dd>
			</dl>
			<dl class="card card03">
				<dt class="dt_card03"></dt>
				<dd>
					<p class="strong">电信充值卡</p>
					<p>面值：10元、20元、30元、50元、100元、200元、300元、500元</p>
				</dd>
			</dl>
			<h1>一卡通系列<em class="to-bottom"></em><em class="to-bottomb"></em></h1>
			<dl class="card card04">
				<dt class="dt_card04"></dt>
				<dd>
					<p class="strong">俊网一卡通</p>
					<p>面值：5元、6元、10元、15元、20元、30元、50元、100元、120元、200元、500元、1000元</p>
				</dd>
			</dl>
			<dl class="card card05">
				<dt class="dt_card05"></dt>
				<dd>
					<p class="strong">盛大一卡通</p>
					<p>面值：1元、2元、3元、5元、10元、15元、25元、30元、35元、45元、50元、100元、300元、350元、1000元</p>
				</dd>
			</dl>
			<dl class="card card04">
				<dt class="dt_card06"></dt>
				<dd>
					<p class="strong">Q币</p>
					<p>面值：5元、10元、15元、30元、60元、100元、200元</p>
				</dd>
			</dl>
			<dl class="card card02">
				<dt class="dt_card07"></dt>
				<dd>
					<p class="strong">完美一卡通</p>
					<p>面值：15元、30元、50元、100元</p>
				</dd>
			</dl>
			<dl class="card card04 last">
				<dt class="dt_card08"></dt>
				<dd>
					<p class="strong">征途一卡通</p>
					<p>面值：10元、15元、20元、25元、30元、50元、60元、100元、300元、500元、1000元</p>
				</dd>
			</dl>
			<dl class="card card03">
				<dt class="dt_card09"></dt>
				<dd>
					<p class="strong">网易一卡通</p>
					<p>面值：5元、10元、15元、20元、30元、50元</p>
				</dd>
			</dl>
			<dl class="card card01">
				<dt class="dt_card10"></dt>
				<dd>
					<p class="strong">搜狐一卡通</p>
					<p>面值：5元、10元、15元、30元、40元、100元</p>
				</dd>
			</dl>
			<dl class="card card01">
				<dt class="dt_card11"></dt>
				<dd>
					<p class="strong">久游一卡通</p>
					<p>面值：5元、10元、20元、25元、30元、50元、100元</p>
				</dd>
			</dl>
			<dl class="card card04">
				<dt class="dt_card12"></dt>
				<dd>
					<p class="strong">天宏一卡通</p>
					<p>面值：5元、10元、15元、30元、50元、100元</p>
				</dd>
			</dl>
			<dl class="card card05 last">
				<dt class="dt_card13"></dt>
				<dd>
					<p class="strong">天下专项一卡通</p>
					<p>面值：10元、20元、30元、40元、50元、60元、70元、80元、90元、100元</p>
				</dd>
			</dl>
			<dl class="card card05">
				<dt class="dt_card14"></dt>
				<dd>
					<p class="strong">天下通用一卡通</p>
					<p>面值：10元、20元、30元、40元、50元、60元、70元、80元、90元、100元</p>
				</dd>
			</dl>
			<dl class="card card04">
				<dt class="dt_card15"></dt>
				<dd>
					<p class="strong">光宇一卡通</p>
					<p>面值：10元、20元、30元、50元、100元</p>
				</dd>
			</dl>
			<dl class="card card04">
				<dt class="dt_card16"></dt>
				<dd>
					<p class="strong">纵游一卡通</p>
					<p>面值：10元、15元、30元、50元、100元</p>
				</dd>
			</dl>
			<dl class="card card01">
				<dt class="dt_card17"></dt>
				<dd>
					<p class="strong">盛付通</p>
					<p>面值：1元、2元、3元、5元、10元、15元、25元、30元、35元、45元、50元、100元、300元、350元、1000元</p>
				</dd>
			</dl>
		</div>
	</div>
    <jsp:include page="inc/footer.jsp" />
</body>
</html>