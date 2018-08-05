<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
<br>
<div class="panel panel-default">
    <div class="panel-body">
        <a href="/billing" class="text-left">
            <img src="/resources/png/billing.png" width="35">
        </a>
        <sec:authorize var="loggedIn" access="isAuthenticated()"/>
        <c:if test="${not loggedIn}">
            <button formaction="/billing/login" style="float: right" type="submit" class="btn btn-info">
                <spring:message code="button.login.caption"/></button>
        </c:if>
        <c:if test="${loggedIn}">
            <sec:authentication var="username" property="principal.username"/>
            <button formaction="/billing/logout" type="submit" class="btn btn-danger" style="float: right">
                <spring:message code="button.logout.caption"/>&nbsp;${username}
            </button>
        </c:if>
    </div>
</div>
<br>
</body>
</html>
