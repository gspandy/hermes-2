<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="欧飞网充值卡寄售平台" />
	<meta name="keywords" content="欧飞网充值卡寄售平台" />
	<title>我的账户 － API接口 - 欧飞网充值卡寄售平台</title>
    <script>
        $(document).ready(function () {
            // tab 高亮
            bindTab("account", "account_4");
        });
    </script>
</head>
<body>
	<div class="content">
		<div class="accInfo w1200">
			<h3>API接口</h3>
			<div class="api">
				<span class="joinStep joinStep01"><b>下载获取文档</b><i></i><samp class="tipAlert">下载获取接口文档提示：API接口欧飞网统一由商务提供，商户可与商务咨询索取欧飞API接口文档。<em class="to-bottom"></em></samp></span>
				<span class="joinStep joinStep02"><b>获取商户ID和安全码</b><i></i><samp class="tipAlert">获取商户ID和安全码提示：商户可与商务咨询索取安全码，为了接口提交卡的安全保证，安全码全方位保护您的卡在提交过程中的安全，保证您的安全无忧。<em class="to-bottom"></em></samp></span>
				<span class="joinStep joinStep03"><b>对接接口</b><i></i><samp class="tipAlert">对接接口提示：接口提供了提交卡的接口，以及结果回调接口和结果查询接口，在遇到问题时，我们的研发实时为你服务。<em class="to-bottom"></em></samp></span>
				<span class="joinStep joinStep04"><b>上线走量</b><i></i><samp class="tipAlert">上线走量提示：接口对接成功后，即可以自动接口提卡。真正做到线上无忧提卡。<em class="to-bottom"></em></samp></span>
				<h4>注意事项</h4>
				<p>1.API接口提交卡需要对接欧飞卡寄售接口</p>
				<p>2.安全码是对接唯一的加密密钥，请妥善保管</p>
              <p>3.再接口对接时，如果遇到问题可联系研发人员2355831750</p>
              <p>4.接口一旦接入成功，提交面值请按照真实面值提交</p>
              <p>5.在卡已经提交的情况下，充值未结束时请不要再次提交该卡</p>
              <p>6.提交卡是使用的商户订单号请不要重复</p>
			</div>
		</div>
	</div>
</body>
</html>