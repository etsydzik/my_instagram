<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-md-3" align="left">
        <%--<a href="<c:url value="/registration"/>"><h2>Log in</h2></a>--%>
    </div>
    <div class="col-md-6" align="center">
        <a href="<c:url value="/home"/>">
            <h2>My Project</h2>
        </a>
    </div>
    <div class="col-md-3" align="right">
        <a href="<c:url value="/logOut"/>"><h2>Log out</h2></a>
    </div>
</div>
