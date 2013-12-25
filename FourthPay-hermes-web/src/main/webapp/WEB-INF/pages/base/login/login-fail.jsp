<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>登录失败</title>
    <link rel="stylesheet" type="text/css" href="/css/index.css"/>
    <style type="text/css">
        h1 {text-shadow:none;}
    </style>
</head>
<body>
<div id="login">
<h1>登录错误：</h1>
<p>${error_msg}</p>
<p id="actions">
<a href="javascript:window.history.back()">返回</a>
</p>
</div>
<script type="text/javascript">
    window.onload = function() {
        if (!-[1,]) { // only IE & ver lt 10
            document.write('<p style="text-align: center">抱歉，我们并不欢迎IE用户！</p>');
        }
    };
</script>
</body>
</html>