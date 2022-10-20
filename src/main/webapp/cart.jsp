<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />

<h1>Cart</h1>
<c:choose>
    <c:when test="${sessionScope.cart == null || sessionScope.cart.items.isEmpty()}">
        <div class="alert alert-warning">Cart is empty.</div>
    </c:when>

    <c:otherwise>
        <table class="table table-hover table-striped">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
            </tr>

            <c:forEach items="${sessionScope.cart.items}" var="item">
                <tr>
                    <td>${item.product.id}</td>
                    <td>${item.product.name}</td>
                    <td>${item.product.price}</td>
                    <td>${item.quantity}</td>
                    <td>${item.totalPrice}</td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="4" style="text-align: right;">Total:</td>
                <td>${sessionScope.cart.total}</td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>

<div class="my-2">
    <a class="btn btn-success" href="${pageContext.request.contextPath}"><-- Back</a>
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/products">Keep Buying --></a>
</div>

<jsp:include page="layout/footer.jsp" />