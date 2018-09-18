<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <%@ include file="include-meta.jsp" %>
    <%@ include file="include-bootstrap.jsp" %>
</head>
<body>
<div class="container">
    <h1><spring:message code="login.form.hrader"/></h1>
    <div class="panel panel-primary">
        <div class="panel-body">
            <form action="/billing/login" method="post" class="form-signin">
                <div class="alert alert-info" role="alert"><spring:message code="login.form.please.signin"/>
                    <a href="/billing/registration"><spring:message code="login.form.signout"/></a></div>

                <div class="form-group">
                    <label for="username"><spring:message code="login.form.username"/></label>
                    <input id="username" type="text" class="form-control" name="username" placeholder="Username"
                           autofocus="true"/>
                </div>
                <div class="form-group">
                    <label for="password"><spring:message code="login.form.password"/></label>
                    <input id="password" type="password" class="form-control" name="password" placeholder="Password">
                    <span style="color: red">${error}</span>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>

                <button class="btn btn-primary" type="submit">
                    <spring:message code="login.form.button.ok"/>
                </button>
                <button formaction="/billing/login/cancel" class="btn btn-primary">
                    <spring:message code="login.form.button.cancel"/>
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
