<%--
  Created by IntelliJ IDEA.
  User: tsyd
  Date: 28.03.2015
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="../parts/resources.jsp"/>
    <title></title>
</head>
<body>
<jsp:include page="../parts/navbar.jsp"/>
<div class="container">
    <jsp:include page="../parts/header.jsp"/>
    <div class="row">
        <jsp:include page="../parts/mySideBar.jsp"/>

        <form action="<c:url value="/user/search"/>">
            <input type="text" name="q"/>
            <button type="submit">Поиск</button>
        </form>


        <c:forEach items="${users}" var="user">
            <div class="padding-top-15">
                    <a href="<c:url value="/user/${user.id}"/>">
                        <img src="<c:url value="/user/${user.id}/showAvatar"/>">
                    </a>
                    <c:out value="${user.name}"/>
            </div>
        </c:forEach>
    </div>

</div>
</body>
</html>
