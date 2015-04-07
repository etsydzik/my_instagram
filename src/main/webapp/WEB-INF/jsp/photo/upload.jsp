<%--
  Created by IntelliJ IDEA.
  User: tsyd
  Date: 28.03.2015
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <form action="<c:url value="/photo/upload"/>" method="POST" enctype="multipart/form-data">
            <input type="file" name="photofile">
            <button type="submit">Добавить</button>
        </form>
    </div>

</div>
</body>
</html>
