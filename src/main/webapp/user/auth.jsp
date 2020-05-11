<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="uri" value="${pageContext.request.requestURI}" />
<c:set var="path" value="${fn:substring(url, 0, fn:length(url) -
 fn:length(uri))}${req.contextPath}" />

<html>


<head>
    <title>Авторизация пользователя</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>

<jsp:include page="../nav.jsp" />
<div class="row">
    <div class="col s12 m12">
        <div class="card">
            <div class="card-content black-text">
                <span class="card-title">Вход в систему</span>
                <c:if test="${!authPassed}">
                    <form action="" method="post">
                        <p>Для того чтобы продолжить пользоваться вохможностями OSES авторизируйтесь пожалуйста</p>
                        <p>Вход для пользователей OSES</p>
                        <p>Логин: <input name="login" type="text" value=""></p>
                        <p>Пароль: <input name="password" type="password" value=""> </p>
                        <p><input type="submit" ></p>
                    </form>
                </c:if>
                <c:if test="${authPassed}">
                    <p>Вы уже прошли авторизацию перейдите в главное меню <a href="${path}/user/menu"> Ссылка на меню </a></p>
                </c:if>
            </div>
            <div class="card-action">
                Либо пройдите процедуру регистрации <a href="${path}/user_registration">Регистрация</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>
