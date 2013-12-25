<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title><sitemesh:title/></title>
    <meta name="description" content="" />
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="http://pic.ofcard.com/jslib/jquery/plugin/cycle/jquery.cycle.all.js"></script>
    <script type="text/javascript" src="/js/hermes.js"></script>
    <script type="text/javascript" src="/css/style.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <sitemesh:head />
</head>

<body>
    <!-- header -->
    <c:if test="${empty usercode}">
       <jsp:include page="/static/inc/header.jsp" />
    </c:if>
    <c:if test="${!empty usercode}">
       <jsp:include page="header.jsp" />
    </c:if>
    <!-- content -->
    <sitemesh:body />
    <!-- footer -->
    <jsp:include page="/static/inc/footer.jsp" />
    <div class="online">
        <s></s><a target="_blank" href="tencent://message/?uin=2355617715&amp;Site=在线咨询&amp;Menu=yes">在线咨询</a>
    </div>
</body>
</html>