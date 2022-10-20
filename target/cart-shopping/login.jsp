<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />

<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="row mb-2">
        <label>Username</label>
        <input class="form-control" type="text" name="username" id="username">
    </div>

    <div class="row mb-2">
        <label>Password</label>
        <input class="form-control" type="password" name="password" id="password">
    </div>

    <div class="row mb-2">
        <input class="btn btn-primary" type="submit" value="Submit">
    </div>
</form>

<jsp:include page="layout/footer.jsp" />