<%@include file="view/header.jsp"%>
<%@ page import="ua.com.company.store.model.entity.additional.ProductImage" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.com.company.store.service.ProductService" %>
<%@ page import="ua.com.company.store.service.ProductImageService" %>
<% List<ProductImage> list = ProductImageService.getInstance().getAllProducts(); %>


<h1>MainPage</h1>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col"><fmt:message key="store.product.id" bundle="${rb}"/> </th>
        <th scope="col"><fmt:message key="store.product.title" bundle="${rb}"/> </th>
        <th scope="col"><fmt:message key="store.product.description" bundle="${rb}"/> </th>
        <th scope="col"><fmt:message key="store.product.price" bundle="${rb}"/> </th>
        <th scope="col"><fmt:message key="store.product.image" bundle="${rb}"/> </th>
        <c:if test="${user.isRole() eq false}">
            <th scope="col"> <fmt:message key="store.product.buy" bundle="${rb}"/></th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="prod" items="<%=list%>">
        <c:url var="image" value="${prod.pathImage}"/>
        <tr>
            <th scope="row">${prod.id}</th>
            <td>${prod.title}</td>
            <td>${prod.description}</td>
            <td>${prod.price}</td>
            <td><img src="${image}" width="100px" height="50px"></td>

            <c:if test="${user.isRole() eq false}">
                <td scope="col"> <a href="${pageContext.request.contextPath}/store/createOrder?id=${prod.id}">
                    <fmt:message key="store.product.buy" bundle="${rb}"/>
                </a>
                </td>

                </c:if>


        </tr>

    </c:forEach>

    </tbody>
</table>


<%@include file="view/footer.jsp"%>