<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta  name="description" content="欧飞网充值卡寄售平台" />
    <meta name="keywords" content="欧飞网充值卡寄售平台付" />
    <title>支付费率 - 欧飞网充值卡寄售平台</title>
    <script type="text/javascript">
        $(document).ready(function () {
            // tab 高亮
            bindTab("rate", "");
        });
    </script>
</head>
<body>
<div class="content">
    <div class="accInfo w1200">
        <h3>支付费率</h3>
        <table class="border_table rate_table">
            <tr class="table_title">
                <th>支付类型</th>
                <th>费率</th>
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