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
            <form:form action="/billing/authorized/user" method="post" modelAttribute="userTransaction">
                <div class="row">
                    <div class="col-sm-4">
                        <img class="left img-responsive align-middle center-block" src="/resources/png/billing.png">
                    </div>
                    <div class="col-sm-8">
                        <form:hidden path="sender.id"/>
                        <form:hidden path="sender.account.debit"/>

                        <spring:message code="authorized.user.description"/><br>

                        <label for="reciver.account.debit">
                                ${userTransaction.sender.username}
                            <spring:message code="authorized.user.description.send.from.account"/>
                                ${userTransaction.sender.account.id} (${userTransaction.sender.account.debit})
                        </label>
                        <spring:bind path="reciver.account.debit">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="text" path="reciver.account.debit" class="form-control"
                                            value="${userTransaction.sender.account.debit}"
                                            autofocus="true"></form:input>
                                <form:errors path="reciver.account.debit" cssStyle="color: red"></form:errors>
                            </div>
                        </spring:bind>
                        <spring:bind path="reciver.account.id">
                            <label for="reciver.account.id">
                                <spring:message code="authorized.user.description.to.account"/>
                            </label>
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="text" path="reciver.account.id" class="form-control"></form:input>
                                <form:errors path="reciver.account.id" cssStyle="color: red"></form:errors>
                            </div>
                        </spring:bind>

                        <button class="btn btn-primary" type="submit">
                            <spring:message code="authorized.user.send.form.button.send"/>
                        </button>
                        <button formaction="/billing" class="btn btn-primary">
                            <spring:message code="authorized.user.send.form.button.cancel"/>
                        </button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
