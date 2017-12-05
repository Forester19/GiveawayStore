<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 28.11.2017
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


</head>
<body>
<table>
    <tr>
        <td>
<a><h1>That's your page dear admin ${admin}</h1></a>
    </td>
        <td>
            ${successfulMuve}
        </td>
    </tr>
</table>
<div class="containerAddNewProduct">
    <img src="img/returns.jpg">
    <a><h1>Add new Product</h1></a>
    <form enctype="multipart/form-data" action="newProductForm"  method="post">
        <input type="hidden" name="command" value="ADD_NEW_PRODUCT_FORM">
        <div class="dws-input">
            <input type="text" name="title" placeholder="title">
        </div>
        <div class="dws-input">
            <input type="text" name="description" placeholder="description">
        </div>
        <div class="dws-input">
            <input type="number" name="price" placeholder="price">
        </div>
        <div class="dws-input">
            <input type="file" name="file" multiple accept="image/*">
        </div>

        <br/>
        <input class= "dws_submit" type="submit" name="submit" value="LogIn">
        <br/>

    </form>

</div>
</body>
</html>
