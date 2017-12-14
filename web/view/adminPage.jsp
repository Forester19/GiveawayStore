<%@include file="header.jsp"%>

<div class="container">
   <div class="row">
    <div class="col-sm">
        <form name="LoginForm" action="/store/addNewProduct" method="post" enctype="multipart/form-data">
            <table class="table" bgcolor="#ffe4c4" border="1">
               <thead>
                <tr>
                   <th scope="col">
                <div class="form-group">
                       <h3><fmt:message key="store.admin.addNewProduct" bundle="${rb}"/> </h3>
                   </div>
                   </th>
               </tr>
               </thead>
                <tbody>
            <tr><td>
                <div class="form-group">
                <label for="exampleInputEmail1"><fmt:message key="store.admin.Title" bundle="${rb}"/> </label>
                <input type="text" name="title" class="form-control" id="exampleInputEmail1" aria-describedby="nicknameHelp" placeholder="<fmt:message key="store.admin.enterTitle" bundle="${rb}"/>">
            </div>
            </td>
            </tr>
                <tr><td>
            <div class="form-group">
                <label for="exampleInputPassword1"><fmt:message key="store.admin.description" bundle="${rb}"/> </label>
                <input  type="text" name="description" class="form-control" id="exampleInputPassword1" placeholder="<fmt:message key="store.admin.enterDescription" bundle="${rb}"/> ">
            </div>
                </td>
                </tr>
                <tr><td>
                    <div class="form-group">
                        <label for="exampleInputPassword4"><fmt:message key="store.admin.Price" bundle="${rb}"/> </label>
                        <input type="number" name="price" class="form-control" id="exampleInputPassword4" placeholder="<fmt:message key="store.admin.enterPrice" bundle="${rb}"/> ">
                    </div>
                </td>
                </tr>
                <tr><td>
                    <div class="form-group">
                        <label for="exampleInputPassword5"><fmt:message key="store.admin.File" bundle="${rb}"/> </label>
                        <input type="file" name="file" class="form-control" id="exampleInputPassword5"/>
                    </div>
                </td>
                </tr>

                <tr><td>
            <button type="submit" class="btn btn-primary"><fmt:message key="store.submit" bundle="${rb}"/> </button>
                </td>
                </tr>
                <tr><td>
                   ${successful}
                </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>


    <div class="col-sm">

        <table class="table table-bordered table-dark">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="store.user.id" bundle="${rb}"/> </th>
                <th scope="col"><fmt:message key="store.user.nickname" bundle="${rb}"/> </th>
                <th scope="col"><fmt:message key="store.user.password" bundle="${rb}"/></th>
                <th scope="col"><fmt:message key="store.user.email" bundle="${rb}"/></th>
                <th scope="col"><fmt:message key="store.user.role" bundle="${rb}"/></th>
                <th scope="col"><fmt:message key="store.user.defaulter" bundle="${rb}"/></th>
                <th scope="col"><fmt:message key="store.admin.deleteUser" bundle="${rb}"/></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${listOfUsers}" var="user">
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.nickname}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${user.defaulter}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/store/deleteUser?id=${user.id}">
                    <fmt:message key="store.admin.deleteUser" bundle="${rb}"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</div>


<%@include file="footer.jsp"%>
