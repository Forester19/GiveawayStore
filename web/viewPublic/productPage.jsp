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



<nav>
    <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Send review</a>
        <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Show review</a>
        <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">Contact</a>
    </div>
</nav>
<div class="tab-content" id="nav-tabContent">
    <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
        <form action="${pageContext.request.contextPath}/store/createReview" method="post">

            <div class="form-group">
                <label for="exampleInputEmail1">Your review: ${user.nickname}</label>
                <textarea type="text" name="review" rows="10" cols="45" id="exampleInputEmail1" placeholder="review"></textarea>
                <small id="emailHelp" class="form-text text-muted">Please write you review here!</small>
            </div>

            <button type="submit"  class="btn btn-primary"
                    <c:if test="${user.nickname == null || user.role eq true}">
                      disabled</c:if>>
                <fmt:message key="store.submit" bundle="${rb}"/> </button>

        </form>
    </div>
    <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">
                    #
                </th>
                <th scope="col">
                    <fmt:message bundle="${rb}" key="store.nickname"/>
                </th>
                <th scope="col">
                    <fmt:message bundle="${rb}" key="store.review"/>
                </th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">...</div>
</div>







<%@include file="utils/footer.jsp"%>
