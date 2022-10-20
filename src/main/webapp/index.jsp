<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />

<ul class="list-group">
    <li class="list-group-item active">Menu Options</li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/products">List Products</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/cart">See Cart</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/login">Login</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
</ul>

<jsp:include page="layout/footer.jsp" />