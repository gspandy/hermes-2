<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<style type="text/css">
.bodyshouye{
            width:1200px;
            margin:0 auto;
            padding:0;
        }
</style>
</head>
<body>
<div class="bodyshouye">
    <jsp:include page="../base/module/inc/hdader.jsp"/>
	<div style="margin-left:100px">
		<h2>支付费率</h2>
	</div>
	<hr>
	<div>
        <table cellpadding="0" cellspacing="0" border="1" width="60%" align="center">
			<tr>
				<td align="center" width="50%">支付类型</td>
				<td align="center">费率</td>
			</tr>
            <c:forEach var="rate" items="${rates}">
                <tr>
                    <td align="center">${rate.cardName}</td>
                    <td align="center">${rate.rate}</td>
                </tr>
            </c:forEach>
        </table>
	</div>
</div>
</body>
</html>