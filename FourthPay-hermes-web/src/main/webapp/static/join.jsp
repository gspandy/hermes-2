<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="第四方支付" />
	<meta name="keywords" content="第四方支付" />
	<title>我要加盟 - 第四方支付</title>
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
            $("#li3").addClass("current");
        });
    </script>
</head>
<body>
    <jsp:include page="/static/inc/header.jsp" />
	<div class="content">
        <div class="banner">
            <a href="" class="ban ban02"></a>
        </div> <!-- end banner -->
		<div class="conInfo joinInfo w1200">
			<h1>商户加盟<em class="to-bottom"></em><em class="to-bottomb"></em></h1>
			<dl>
				<dt>商户加盟</dt>
				<dd>
					<p>加盟QQ <a target="_blank" href="tencent://message/?uin=2355617715"><img border="0" src="http://wpa.qq.com/pa?p=2:2355617715:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></p>
					<p>座机：025-68208058-6107</p>
				</dd>
			</dl>
			<dl>
				<dt>毕先生</dt>
				<dd>
					<p>加盟QQ <a target="_blank" href="tencent://message/?uin=2355617743"><img border="0" src="http://wpa.qq.com/pa?p=2:2355617743:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></p>
					<p>手机号码：13916914157</p>
				</dd>
			</dl>
			<dl>
				<dt>曹先生</dt>
				<dd>
					<p>加盟QQ <a target="_blank" href="tencent://message/?uin=2355617622"><img border="0" src="http://wpa.qq.com/pa?p=2:2355617622:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></p>
					<p>座机：025-68208058-6119
                    </p>
				</dd>
			</dl>
			<dl>
				<dt>张先生</dt>
				<dd>
					<p>加盟QQ <a target="_blank" href="tencent://message/?uin=2355831738"><img border="0" src="http://wpa.qq.com/pa?p=2:2355831738:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></p>
					<p>座机：025-68208058-6108</p>
				</dd>
			</dl>
			<dl class="last">
				<dt>王先生</dt>
				<dd>
					<p>加盟QQ <a target="_blank" href="tencent://message/?uin=2355831736"><img border="0" src="http://wpa.qq.com/pa?p=2:2355831736:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></p>
					<p>座机：025-68208058-6107</p>
				</dd>
			</dl>
			<h1>加盟流程<em class="to-bottom"></em><em class="to-bottomb"></em></h1> 
			<span class="joinStep joinStep01"><b>合作意向</b><i></i><samp class="tipAlert">您可以通过联系我们商务的加盟QQ，洽谈相关业务，并注册个人/企业用户，商务审核后成为我们的合作商户。<em class="to-bottom"></em></samp></span>
			<span class="joinStep joinStep02"><b>签署协议</b><i></i><samp class="tipAlert">为了成为您最信赖的合作伙伴，我们将与您签署合作协议，保证您的利益不受损害。<em class="to-bottom"></em></samp></span>
			<span class="joinStep joinStep03"><b>对接接口</b><i></i><samp class="tipAlert">商务会将接口文档发送给您，您在对接中出现任何问题，都可联系商务进行协调解决。<em class="to-bottom"></em></samp></span>
			<span class="joinStep joinStep04"><b>接口测试</b><i></i><samp class="tipAlert">接口开通后，我们会协助您进行线上测试，确保您这边的体验，如有问题会及时解决。<em class="to-bottom"></em></samp></span>
			<span class="joinStep joinStep05"><b>上线走量</b><i></i><samp class="tipAlert">测试成功后，您可以大胆地放量了，7*24小时的贴心服务让您有最好的体验。<em class="to-bottom"></em></samp></span>
		</div>
	</div>
    <jsp:include page="inc/footer.jsp" />
</body>
</html>