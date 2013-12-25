<%@ page errorPage="/error.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="ctx" value="" />
<c:set var="usercode" value="${sessionScope.usercode}" />
<c:set var="username" value="${sessionScope.username}" />
<c:set var="userflag" value="${sessionScope.userflag}" />