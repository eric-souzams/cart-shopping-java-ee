<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />

<h1>List Products</h1>

<c:if test="${username.present}">
    <div class="alert alert-info">Hello, ${username.get()}</div>
    <div class="alert alert-info">Hello, ${requestScope.username.get()}</div>
    <p><a class="btn btn-primary" href="${pageContext.request.contextPath}/products/form">Create Product [+]</a></p>
</c:if>

<table class="table table-hover table-striped">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Type</th>

        <c:if test="${username.present}">
            <th>Price</th>
            <th>Add +</th>
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
    </tr>

    <c:forEach items="${products}" var="prod">
        <tr>
            <td>${prod.id}</td>
            <td><c:out value="${prod.name}" /></td>
            <td>${prod.category.name}</td>
            <c:if test="${username.present}">
                <td>${prod.price}</td>
                <td><a class="btn btn-sm btn-info" href="${pageContext.request.contextPath}/add-cart?id=${prod.id}">Add to cart</a></td>
                <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/products/form?id=${prod.id}">Edit</a></td>
                <td><a class="btn btn-sm btn-danger" onclick="return confirm('Have sure?');" href="${pageContext.request.contextPath}/products/delete?id=${prod.id}">Delete</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>

<jsp:include page="layout/footer.jsp" />