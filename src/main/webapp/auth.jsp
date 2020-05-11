<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="uri" value="${pageContext.request.requestURI}" />
<c:set var="path" value="${fn:substring(url, 0, fn:length(url) -
 fn:length(uri))}${req.contextPath}" />

<html>
<head>
    <title>Авторизация администратора</title>
    <style>
        <%@include file="css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="../admin_nav.jsp" />
<div class="row">
    <div class="col s12 m12">
        <div class="card-panel white">
                <form action="" method="post">
                    <p>Вход в Панель администратора</p>
                    <p>Логин: <input name="login" type="text" value=""></p>
                    <p>Пароль: <input name="password" type="password" value=""> </p>
                    <p><input type="submit" name="submit" class="waves-effect waves-light btn-small"></p>
                </form>
                <p> Секретный логин и пароль для входа в систему <span style="color: red"> admin 123 </span>
                    Никому не рассказывайте о них </p>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" />

</body>
</html>
