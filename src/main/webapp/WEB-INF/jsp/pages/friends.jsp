<%--
  Created by IntelliJ IDEA.
  User: tsyd
  Date: 29.03.2015
  Time: 10:49
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
        <jsp:include page="../parts/userSideBar.jsp"/>
       <jsp:include page="../parts/friendsList.jsp"/>
    </div>

</div>
</body>
</html>
