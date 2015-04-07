<%--
  Created by IntelliJ IDEA.
  User: tsyd
  Date: 18.03.2015
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/parts/resources.jsp"/>
    <title></title>

</head>
<body>
<jsp:include page="../parts/navbar.jsp"/>
<div class="container">
    <jsp:include page="../parts/header.jsp"/>
    <div class="comments-container">
        <a href="<c:url value="/photo/${photoId}/avatar"/>" class="btn btn-primary btn-lg btn-block">
            Сделать аватаркой
        </a>
        <div class="padding-top-15">
            <img src="<c:url value="/photo/${photoId}?size=big"/>">
        </div>
        <c:forEach items="${comments}" var="comment">
            <div class="row padding-top-15" >
                <div class="comment-img">
                    <a href="<c:url value="/user/${comment.user.id}"/>">
                        <img src="<c:url value="/user/${comment.user.id}/showAvatar"/>">
                    </a>
                </div>
                <div class="comment-content">
                    <div><c:out value="${comment.user.name}"/></div>
                    <div><c:out value="${comment.text}"/></div>
                    <div><c:out value="Добавлен ${comment.date}"/></div>
                </div>
            </div>
        </c:forEach>
        <div class="padding-top-15">
            <form action="<c:url value="/photo/${photoId}/comments"/>" method="POST">
                <textarea rows="3" class="form-control" name="comment"></textarea>
                <button type="submit" class="btn">Отправить</button>
            </form>
        </div>
    </div>

</div>
</body>
</html>
