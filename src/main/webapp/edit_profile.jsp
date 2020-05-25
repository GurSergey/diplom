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
    <title>Редактирование профиля</title>
    <style>
        <%@include file="css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="user_nav.jsp" />
<div class="panel">
    <%@ page import="com.company.enums.EntityError" %>
    <c:if test="${error==EntityError.NO_ERROR_UPDATE}">
        <p style="color: green;">Запись успешно обновлена</p>
    </c:if>
    <c:if test="${error==EntityError.NO_ERROR_DELETE}">
        <p style="color: green;">Запись успешно удалена</p>
    </c:if>
    <c:if test="${error==EntityError.NO_ERROR_INSERT}">
        <p style="color: green;">Запись успешно добавлена</p>
    </c:if>
    <c:if test="${error==EntityError.INSERT}">
        <p style="color: red;">Возникла ошибка вставки новых записей. Повторите попытку позже</p>
    </c:if>
    <c:if test="${error==EntityError.SELECT}">
        <p style="color: red;">Возникла ошибка получение записей из БД. Повторите попытку позже</p>
    </c:if>
    <c:if test="${error==EntityError.UPDATE}">
        <p style="color: red;">Возникла ошибка обновления записей из БД. Повторите попытку позже</p>
    </c:if>
        <div class="row">
            <div class="col s12 m12">
                <div class="card">
                    <div class="card-content black-text">
                        <h4>Смена пароля</h4>
                        <form action="" method="post">
                            <p>Пароль:<input name="password" type="password" value=""> </p>
                            <p>Повторите пароль:<input name="passwordRepeat" type="password" value=""></p>
                            <p><input type="submit"></p>
                            <input type="hidden" name="id" value="${user.id}">
                        </form>
                    </div>
                    <div class="card-action">
                        <p>Вернитесь в главное меню <a href="${path}/user/menu/">Вход</a></p>
                    </div>
                </div>
            </div>
        </div>

</div>
</body>
</html>
