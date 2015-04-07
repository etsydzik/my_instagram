<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${photos}" var="photo">
    <div class="img-div">
        <a href="<c:url value="/photo/${photo.id}/comments"/>">
            <img src="<c:url value="/photo/${photo.id}"/>">
        </a>
    </div>
</c:forEach>
