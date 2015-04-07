<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: tsyd
  Date: 19.03.2015
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/parts/resources.jsp"/>
    <title></title>
</head>
<body>
<br>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form:form cssClass="form-horizontal" modelAttribute="user">
            <div>
                <form:errors cssStyle="color: red;" />
            </div>
            <div>
                <form:errors path="email" cssStyle="color: red;" />
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Email</label>
                <div class="col-sm-5">
                    <form:input path="email" cssClass="form-control" />
                </div>
            </div>
            <div>
                <form:errors path="password" cssStyle="color: red;" />
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Password</label>
                <div class="col-sm-5">
                    <form:password path="password" cssClass="form-control" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-5 col-sm-offset-5">

                    <button type="submit" class="btn btn-primary btn-lg btn-block href="<c:url value="/login"/>"">Enter</button>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-5 col-sm-offset-5">

                    <a href="<c:url value="/registration"/>"
                    class="btn btn-primary btn-lg btn-block">Registration</a>
                </div>
            </div>
        </form:form>
    </div>
    <div class="col-md-4"></div>
</div>
</body>
</html>
