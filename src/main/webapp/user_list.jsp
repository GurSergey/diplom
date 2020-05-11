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
    <title>Список пользователей в системе</title>
    <style>
        <%@include file="css/style.css"%>
    </style>
</head>
    <body>

    <jsp:include page="../admin_nav.jsp" />
        <h1> Список пользователей в системе </h1>
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
<%--        <jsp:useBean id="polls" scope="request" type="java.util.List"/>--%>

        <div class="row">
            <div class="col s12 m12">
                <div class="card-panel white">
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>Имя</th>
                    <th>Логин</th>
                    <th>Дата создания аккаунта</th>
                    <th>Хеш пароля</th>
                    <th>Телефон </th>
                </tr>
            </thead>
            <tbody>
                        <c:forEach var="user" items="${users}">
                             <tr>
                                    <td>${user.id}</td>
                                    <td>${user.name}</td>
                                    <td>${user.login}</td>
                                    <td>${user.registrationDate}</td>
                                    <td>${user.password}</td>
                                    <td>${user.phone}</td>
                            </tr>
                        </c:forEach>
            </tbody>
        </table>
                </div>
            </div>
        </div>

    <jsp:include page="../footer.jsp" />
    </body>
</html>