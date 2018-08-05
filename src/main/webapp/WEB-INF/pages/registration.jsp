<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registraion</title>
    <%@ include file="include-meta.jsp" %>
    <%@ include file="include-bootstrap.jsp" %>
</head>
<body>
<div class="container">
    <h1><spring:message code="registration.form.header"/></h1>
    <form:form action="/billing/registration" method="post" modelAttribute="userForm">
        <div class="panel panel-primary">
            <div class="panel-body">
                <div class="alert alert-info" role="alert">
                    <spring:message code="registration.form.caption"/>
                </div>
                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <spring:message code="registration.form.username.placeholder" var="usernamePlaceholder"/>
                        <form:input type="text" path="username" class="form-control"
                                    placeholder="${usernamePlaceholder}"
                                    autofocus="true"></form:input>
                        <form:errors path="username" cssStyle="color: red"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="email">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <spring:message code="registration.form.email.placeholder" var="emailPlaceholder"/>
                        <form:input type="text" path="email" class="form-control" placeholder="${emailPlaceholder}"
                                    autofocus="true"></form:input>
                        <form:errors path="email" cssStyle="color: red"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <spring:message code="registration.form.password.placeholder" var="passwordPlaceholder"/>
                        <form:input type="password" path="password" class="form-control"
                                    placeholder="${passwordPlaceholder}"></form:input>
                        <form:errors path="password" cssStyle="color: red"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="confirmPassword">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <spring:message code="registration.form.confirm.placeholder" var="confirmPlaceholder"/>
                        <form:input type="password" path="confirmPassword" class="form-control"
                                    placeholder="${confirmPlaceholder}"></form:input>
                        <form:errors path="confirmPassword" cssStyle="color: red"></form:errors>
                    </div>
                </spring:bind>

                <button class="btn btn-primary" type="submit">
                    <spring:message code="registration.form.button.ok"/>
                </button>
                <button formaction="/billing" class="btn btn-primary">
                    <spring:message code="registration.form.button.cancel"/>
                </button>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
