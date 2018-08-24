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
                        <br>
                        <br>sender id - ${userTransaction.sender.id}
                        <br>sender account debit - ${userTransaction.sender.account.debit}
                        <br>reciver account id - ${userTransaction.reciver.account.id}
                        <br>amount - ${userTransaction.reciver.account.debit}
                        <br>

                            <%--button class="btn btn-primary" type="submit">
                                <spring:message code="authorized.user.send.form.button.send"/>
                            </button--%>
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
