<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="欧飞网充值卡寄售平台" />
	<meta name="keywords" content="欧飞网充值卡寄售平台" />
	<title>提现管理 － 提现管理 - 欧飞网充值卡寄售平台</title>
    <script type="text/javascript" src="${ctx}/js/jquery.artDialog.js?skin=twitter"></script>
    <script>
        $(document).ready(function () {
            // tab 高亮
            bindTab("apply", "apply_3");

            // 错误消息
            if('${errMsg}'){
                <%--alert('${errMsg}');--%>
                // 显示成功信息
                art.dialog({
                    icon: 'error',
                    title: '卡密提交结果',
                    content: '${errMsg}',
                    cancelVal: '关闭',
                    cancel: true,
                    lock: true
                });
            }
        });
    </script>
</head>
<body>
	<div class="content">
		<div class="accInfo w1200">
			<div class="cashManage clearfix">
                <c:forEach var="i" items="${binds}">
                    <div class="bankInfo">
                        <div class="btitle">
                            <span class="bankName">${i.bankname}</span>
                            <span class="bankNum">尾号：<c:out
                                    value="${fn:substring(i.accountno, fn:length(i.accountno)-4 , fn:length(i.accountno))}"/></span>
                        </div>
                        <div class="bbody">
                            <p class="widthdraw">
                                <b class="orange">
                                    ${i.statinfo}
                                </b>
                                <a href="/cash/bind/delete/${i.accountflowid}"
                                   onclick="return confirm('确定删除这张银行卡吗？')" class="right">删除</a>
                            </p>
                            <p><label for="">开户名：</label><span>${i.accountusername}</span></p>
                            <p><label for="">费率：</label><span>0%</span></p>
                            <p><label for="">备注：</label><span>${i.remarkinfo}</span></p>
                        </div>
                    </div>
                </c:forEach>
				<div class="bankInfo addBank">
					<a href="/cash/bind/new"><s></s>添加银行卡</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>