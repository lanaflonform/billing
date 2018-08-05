<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Billing</title>
    <%@ include file="include-meta.jsp" %>
    <%@ include file="include-bootstrap.jsp" %>
</head>

<body>
<div class="container">
    <form:form method="get" action="/login">
        <jsp:include page="header.jsp"/>
    </form:form>

    <div class="panel panel-info">
        <div class="panel-heading">
            <spring:message code="billing.header"/>
        </div>
        <div class="panel-body">
            <spring:message code="authorized.admin.description"/>
            <br>
            <img class="center-block img-responsive" src="/resources/png/billing.png">
        </div>
    </div>
</div>
</body>
</html>
