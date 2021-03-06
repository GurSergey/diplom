<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<nav>
    <div class="nav-wrapper blue darken-4">
        <a href="#" class="brand-logo" style="margin-left: 20px">ML</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="${path}/user_auth/">Войти как пользователь</a></li>
            <li><a href="${path}/admin_auth/">Войти как администратор</a></li>
        </ul>
    </div>
</nav>
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
<jsp:include page="footer.jsp" />

</body>
</html>
