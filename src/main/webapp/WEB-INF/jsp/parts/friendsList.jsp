<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${friends}" var="friend">
        <div class="padding-top-15">

            <a href="<c:url value="/user/${friend.id}"/>">
                <img src="<c:url value="/user/${friend.id}/showAvatar"/>">
            </a>

            <c:out value="${friend.name}"/>
        </div>
</c:forEach>