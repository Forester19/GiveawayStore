<%@include file="viewPublic/utils/header.jsp"%>
<%@ page import="ua.com.company.store.model.entity.additional.ProductImage" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.com.company.store.service.ProductService" %>
<%@ page import="ua.com.company.store.service.ProductImageService" %>
<% List list = ProductImageService.getInstance().getAllProducts();
     if (request.getAttribute("list") != null){
    list = (List) request.getAttribute("list");
}%>

<div class="container_info">
<div class="container">
    <div class="row">
    <div class="col-lg-6">
        <div class="offer">
            <H1 class="offer_title">
                This store so useful!!
            </H1>
            <div class="offer_intro">
                Store brands are a line of products strategically branded by a retailer within a single brand identity. They bear a similarity to the concept of house brands, private label brands (PLBs) in the United States, own brands in the UK, and home brands in Australia and generic brands.
            </div>
            <p class="offer_text">
                The retailer will design the manufacturing, packaging and marketing of the goods in order to build on the relationship between the products and the store's customer base.
            </p>
            <ul class="icons d-flex">
                <li class="icons_item">
                    <a href="">
                        <i class="fa fa-apple"></i>
                    </a>
                </li>
                <li class="icons_item">
                    <a href="">
                        <i class="fa fa-android"></i>
                    </a>
                </li>
                <li class="icons_item">
                    <a href="">
                        <i class="fa fa-windows"></i>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="col-lg-5 ml-auto">
        <img src="resources/images/ipads.png" class="ipad">
    </div>
</div>
</div>
</div>



<section id="products" class="products">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="title">
            <h1>MainPage</h1>
                </div>
            </div>
        </div>
<div class="input-group mb-3">

    <div class="input-group-prepend">

        <fmt:message key="store.product.sorting" bundle="${rb}"/>
        <button class="btn btn-outline-secondary" type="button" onclick="location.href= '${pageContext.request.contextPath}/store/sortingProducts?v=1'">
            <fmt:message key="store.product.descendingPrice" bundle="${rb}"/>
        </button>

        <button class="btn btn-outline-secondary" type="button" onclick="location.href= '${pageContext.request.contextPath}/store/sortingProducts?v=0'">
            <fmt:message key="store.product.ascendingPrice" bundle="${rb}"/>
        </button>
    </div>


    <input type="text" id="your_search_text"  name="searchText" class="form-control" placeholder="<fmt:message key="store.product.search" bundle="${rb}"/>" aria-label="" aria-describedby="basic-addon1">

    <div class="input-group-append">
    <%--<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    --%>    <button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="location.href= '${pageContext.request.contextPath}/store/searchProduct?searchText=' + document.getElementById('your_search_text').value;"><fmt:message key="store.product.search" bundle="${rb}"/></button>
          </div>


</div>


<table class="table table-hover" width="600">
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
        <tr onclick="location.href='${pageContext.request.contextPath}/store/productPage?id=${prod.id}'">
            <th scope="row">
            ${prod.id}</th>
            <td><p class="product_title">${prod.title}</p></td>
            <td>${prod.description}</td>
            <td>${prod.price}</td>
            <td>
                <c:if test="${image eq '/resources/images/'}">
                    <div class="col-lg-3">
                <img src="${pageContext.request.contextPath}/resources/images/no-image-83a2b680abc7af87cfff7777d0756fadb9f9aecd5ebda5d34f8139668e0fc842.png" class="img-fluid">
                    </div>
                        </c:if>
                <c:if test="${image ne '/resources/images/'}">
                <div class="col-lg-3">
                    <img src="${image}" class="img-fluid">
                </div>
                    </c:if>
            </td>


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
    </div>
</section>

<section id="touch" class="touch">
    <div class="container">
<div class="row">
    <div class="col-lg-12">
        <div class="title">
            <h2 class="title_main yellow">
            Keep in touch with us
            </h2>
            <p class="title_text white">
                Leaving your mail you subscribe to our updates. We promise not to pass on your personal data to anyone.
            </p>
        </div>

    </div>
</div>
        <div class="row">
            <div class="col-lg-10 m-auto">
            <form action="#" class="form" method="get">
                <input type="email" placeholder="Enter your email" class="form_input" required>
                <button type="submit" class="form_btn">SUBMIT</button>
            </form>
            </div>
        </div>
    </div>
</section>


<%@include file="viewPublic/utils/footer.jsp"%>