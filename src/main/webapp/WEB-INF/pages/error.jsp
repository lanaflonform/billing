<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Error</title>
    <%@ include file="include-meta.jsp" %>
    <%@ include file="include-bootstrap.jsp" %>
</head>

<body>
<div class="container">
    <h1><spring:message code="error.message.XXX.header"/></h1>
    <div class="panel panel-danger">
        <div class="panel-heading">
            <spring:message code="error.message.XXX.caption"/>
        </div>
        <div class="panel-body">
            <spring:message code="error.message.XXX.body"/>
            <br>
            <br>
            <form:form method="post" action="/billing">
                <button formaction="/billing" type="submit" class="btn btn-info">
                    <spring:message code="error.button.caption"/>
                </button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
