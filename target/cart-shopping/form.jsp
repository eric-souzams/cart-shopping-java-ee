<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />

<h1>Product Form</h1>
<form action="${pageContext.request.contextPath}/products/form" method="post">
    <div class="row mb-2">
        <label for="name" class="col-form-label col-sm-2">Name</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" name="name" id="name" value="${product.name != null ? product.name : ""}">
        </div>
        <c:if test="${errors != null && errors.containsKey('name')}">
            <span style="color: red;"><${errors.get("name")}></span>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="price" class="col-form-label col-sm-2">Price</label>
        <div class="col-sm-4">
            <input class="form-control" type="number" name="price" id="price" value="${product.price > 0 ? product.price : ""}">
        </div>
        <c:if test="${errors != null && errors.containsKey('price')}">
            <span style="color: red;"><${errors.get("price")}></span>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="sku" class="col-form-label col-sm-2">Sku</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" name="sku" id="sku" value="${product.sku != null ? product.sku : ""}">
        </div>
        <c:if test="${errors != null && errors.containsKey('sku')}">
            <span style="color: red;"><${errors.get("sku")}></span>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="created_at" class="col-form-label col-sm-2">Register Date</label>
        <div class="col-sm-4">
            <input class="form-control" type="date" name="created_at" id="created_at" value="${product.createdAt != null ? product.createdAt.format(DateTimeFormatter.ofPattern('yyyy-MM-dd')) : ""}">
        </div>
        <c:if test="${errors != null && errors.containsKey('created_at')}">
            <span style="color: red;"><${errors.get("created_at")}></span>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="category" class="col-form-label col-sm-2">Category</label>
        <div class="col-sm-4">
            <select class="form-select" name="category" id="category">
                <option value="">-- select --</option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}" ${category.id.equals(product.category.id) ? "selected" : ""}>${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <c:if test="${errors != null && errors.containsKey('category')}">
            <span style="color: red;"><${errors.get("category")}></span>
        </c:if>
    </div>

    <div class="row mb-2">
        <div class="col-sm-4">
            <input class="btn btn-primary" type="submit" value="${(product.id != null && product.id > 0) ? "Update" : "Save"}">
        </div>
    </div>

    <input type="hidden" name="id" value="${product.id}">
</form>

<jsp:include page="layout/footer.jsp" />