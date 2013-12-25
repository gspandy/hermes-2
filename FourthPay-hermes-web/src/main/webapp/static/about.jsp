<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="第四方支付" />
	<meta name="keywords" content="第四方支付" />
	<title>关于我们 - 第四方支付</title>
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
            $("#li5").addClass("current");
        });
    </script>
</head>
<body>
    <jsp:include page="/static/inc/header.jsp" />
	<div class="content">
        <div class="banner">
            <a href="" class="ban ban03"></a>
        </div> <!-- end banner -->
		<div class="conInfo aboutInfo w1200">
			<h3>关于我们</h3>
			<div class="block">
				<h4>最专业的卡寄售平台</h4>
				<p>江苏欧飞电子商务有限公司卡类寄售平台（pay.ofpay.com），致力于为企业/个人商户提供专业、稳定、安全、便捷的卡类寄售服务。</p>
              <p>以兑换比例高、资金结算快为目标，结合稳定的平台服务、高效的客服服务、切实满足广大商户的不同需求。</p>
			    <br /><hr />
			    <h4>如果您是供货商（消耗接口），欧飞可以：</h4>
				<p>1、 增加您的分销渠道，完善销售点；</p>
			    <p>2、 增加您的出货量，帮助您尽快回笼资金； </p>
			    <p>3、 帮助您积累人脉关系，寻求更多的商机；</p>
			    <p>4、 欧飞为您宣传品牌，进一步的扩大您的知名度。</p>
			    <br /><hr />
			    <h4>如果您是进货商（充值接口），欧飞可以：</h4>
				<p>1、 帮助您建立发展思路，不至于使您一筹莫展；</p>
			    <p>2、 合理安排资金，把钱都用在刀刃上；</p>
			    <p>3、 减少您的交易风险，以免上当受骗；</p>
			    <p>4、 使您手上不再有一堆"死货"，让您真正做到零库存！</p>
			    <br /><hr />
				<h4>欧飞接口消耗平台优势：</h4>
				<p>1、即充即结，资金零风险，结算不限金额；</p>
			    <p>2、业内返点比率最高，维护商户利益最大化，7*24小时全天侯服务；</p>
			    <p>3、个性化设计，智能化管理，方便快捷；</p>
               <p>4、实时查询，价格自由设定，满足供货商需求；</p>
               <p>5、安全可靠，多重帐户保护措施；</p>
               <p>6、品牌支持，多方位宣传。</p>
			    <br /><hr />
			</div>
		</div>
	</div>
    <jsp:include page="inc/footer.jsp" />
</body>
</html>