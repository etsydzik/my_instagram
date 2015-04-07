<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav side-menu">

    <li><img src="<c:url value="/user/${user.id}/showAvatar?size=big"/>"></li>
    <li><div align="center"><h2><c:out value="${user.name}"/></h2></div></li>
    <li><a href="<c:url value="/home"/>">Моя страница</a></li>
    <li><a href="<c:url value="/${user.id}/friends"/>">Мои друзья</a></li>
    <li><a href="<c:url value="/photo/${user.id}/upload"/>">Добавить фото</a></li>
    <li><a href="<c:url value="/user/search"/>">Поиск</a></li>
</ul>
