<%@include file="utils/header.jsp"%>

<div class="media">
    <img class="img-fluid img-thumbnail" width="25%" height="25%" src="${product.pathImage}" alt="Generic placeholder image">
    <div class="media-body">
        <h5 class="mt-0">${product.title}</h5>
        ${product.description}
    </div>
</div>
<button type="button"  class="btn btn-secondary btn-lg btn-block"
        <c:if test="${user.nickname == null || user.role eq true}">
            onclick="this.disabled=true;"
        </c:if>
        <c:if test="${user.id != 0}">
            onclick="location.href= '${pageContext.request.contextPath}/store/createOrder?id=${product.id}'"
        </c:if>
><fmt:message key="store.product.buy" bundle="${rb}"/> </button>
<%@include file="utils/footer.jsp"%>
