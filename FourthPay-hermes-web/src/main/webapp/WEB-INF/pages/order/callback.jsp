<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="欧飞网充值卡寄售平台" />
	<meta name="keywords" content="欧飞网充值卡寄售平台" />
	<title>欧飞网充值卡寄售平台</title>
</head>
<body>
	<div class="content">
		<div class="accInfo w1200">
            <c:if test="${result eq 'success'}">
                <div class="successPage">
                    <i class="success"></i>
                    <b>结果：${result}</b>
                    <b>信息：${msg}</b>
                </div>
            </c:if>

            <c:if test="${result eq 'fail'}">
                <div class="failurePage">
                    <i class="failure"></i>
                    <b>结果：${result}</b>
                    <b>信息：${msg}</b>
                </div>
            </c:if>

            <c:if test="${result != 'fail' and result != 'success'}">
                <div class="failurePage">
                    <i class="failure"></i>
                    <b>结果：${result}</b>
                    <b>信息：${msg}</b>
                </div>
            </c:if>
		</div>
	</div>
</body>
</html>