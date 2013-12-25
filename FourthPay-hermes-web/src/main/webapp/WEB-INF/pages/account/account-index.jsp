<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="欧飞网充值卡寄售平台" />
	<meta name="keywords" content="欧飞网充值卡寄售平台" />
	<title>我的账户 － 账户首页 - 欧飞网充值卡寄售平台</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/datatable.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.init.js"></script>
    <script>
        $(document).ready(function () {
            // tab 高亮
            bindTab("account", "account_1");

            // 提现按钮
            $("#cash").bind("click", function(){
                window.location.href = "/cash/apply";
            });

            // 刷新
            $("#refresh").bind("click", function(){
                window.location.href = "/account";
            });

            // 最新公告
            $.get( "/account/announce/latest", function( json ) {
                if( json.result === "success") {
                    $("#title").html(json.data.title);
                    $("#createtime").html(json.data.createtime);
                    $("#content").html(json.data.content);
                }
                else {
                    $("#title").html("暂无公告信息");
                }
            });
            // 今日统计
            $.get( "/account/stat", function( json ) {
                if( json.result === "success") {

                    $("#ordercount").html(json.data.ordercount);
                    $("#totalmoney").html(json.data.totalmoney);
                    $("#succordercount").html(json.data.succordercount);
                    $("#succtotalmoney").html(json.data.succtotalmoney);
                }
                else {
                    $("#stat").html("暂无今日统计信息");
                }
            });

            // 查询ip
            $.getJSON( "/account/location/"+'${lastip}', function( json ) {
                if( json.success && json.data.region != "") {
                    $("#location").html(json.data.region);
                }
                else {
                    $("#location").html("未知区域");
                }
            });

            // 最近交易记录
            $('#dataTable').dataTables({
                "aaSorting": [
                    [ 2, "desc" ]
                ],
                "sAjaxSource": "${ctx}/account/order/list",
                "fnServerData": function (sSource, aoData, fnCallback) {
                    var postData = aoData;
                    $.post(sSource, postData, function (json) {
                        fnCallback(json.data);
                    }, "json");
                },
                "aoColumns": [
                    {"sName": "merorderno", "mDataProp": "merorderno"},
                    {"sName": "billid", "mDataProp": "billid"},
                    {"sName": "ordertime", "mDataProp": "ordertime"},
                    {"sName": "cardno", "mDataProp": "cardno"},
                    {"sName": "cardname", "mDataProp": "cardname"},
                    {"sName": "faceval", "mDataProp": "faceval"},
                    {"sName": "realval", "mDataProp": "realval"},
                    {"sName": "retresultmsg", "mDataProp": "retresultmsg", "bSortable": false}
                ]
            });
        });
    </script>
</head>
<body>
	<div class="content">
		<div class="accInfo w1200 clearfix">
			<div class="welAcc">
				<div class="welAcc01">				
					<h3>下午好，<span>${user.username}</span><i class="accUser"></i></h3>
                    <c:if test="${empty user.userStat || user.userStat == 0 || user.merpayflag == 0}">
                        <p class="tip"><i class="radius"></i>您当前的平台帐号状态：审核中</p>
                    </c:if>
					<p>会员编号：
                        <span class="orange">${user.usercode}</span> ｜ 您的积分：
                        <span class="orange"><c:out value="${usercount.leftcredit}" default="0"></c:out></span></p>
                    <c:if test="${user.logdays >= 30}">
                        <p class="tip"><i class="radius"></i>您已${user.logdays}天未修改密码！</p>
                    </c:if>
					<p class="gray">上次登录时间：<span>${lasttime}</span>&nbsp;&nbsp;&nbsp;于&nbsp;<span id="location"></span></p>
				</div>
				<div class="welAcc02">
					<p>账户余额：</p>
					<p>
						<span class="orange"><c:out value="${usercount.merleftmoney}" default="0"></c:out></span>元
						<button id="refresh" class="btn_small">刷新</button>
						<button id="cash" class="btn_small gray_small">提现</button>
					</p>
				</div>
				<div class="welAcc03">
					<h3 id="title"></h3>
					<p id="createtime"></p>
					<p id="content"></p>
				</div>
			</div>
			<div id="stat" class="infoLeft">
				<table>
					<h3 class="table_title">今日统计</h3>
					<tr>
						<th>交易笔数</th>
						<td id="ordercount" class="orange"></td>
					</tr>
					<tr class="even">
						<th>交易金额（元）</th>
						<td id="totalmoney" class="orange"></td>
					</tr>
					<tr>
						<th>成功笔数</th>
						<td id="succordercount" class="orange"></td>
					</tr>
					<tr class="even">
						<th>成功金额（元）</th>
						<td id="succtotalmoney" class="orange"></td>
					</tr>
				</table>
			</div>
			<div class="infoRight">
                <h3 class="table_title">最近交易记录</h3>
				<table id="dataTable">
                    <thead>
                    <tr>
                        <th>商户订单号</th>
                        <th>欧飞流水号</th>
                        <th>订单时间</th>
                        <th>充值卡号</th>
                        <th>运营商</th>
                        <th>声明面值</th>
                        <th>实际面值</th>
                        <th>订单状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>