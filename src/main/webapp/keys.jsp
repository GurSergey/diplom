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
    <title>Список голосований</title>
    <style>
        <%@include file="css/style.css"%>
    </style>
</head>
    <body>
    <jsp:include page="admin_nav.jsp" />

        <h1> Список голосований в системе </h1>
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
        <c:forEach var="poll" items="${polls}">
            <form action="" method="post" id="poll_form_${poll.id}"></form>
        </c:forEach>
        <div class="row">
            <div class="col s12 m12">
                <div class="card-panel white">
                <table>
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>Логин</th>
                            <th>Хеш пароля</th>
                            <th>Дата регистрации</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                                 <tr>
                                     <td>${user.id}<input name="id" type="text" value="${user.id}" form="poll_form_${user.id}">
                                        <input name="typeReq" type="hidden" value="update" form="poll_form_${user.id}"></td>
                                     <td><input name="title" type="text" value="${user.login}" form="poll_form_${user.login}"></td>
                                     <td>${user.password}</td>
                                    <td><input name="createDate" type="date" form="poll_form_${poll.id}"
                                               value="${poll.createDate}"></td>
                                    <td><input type="submit" name="delete" value="Удалить" class="waves-effect waves-light btn-small" form="poll_form_${poll.id}"></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


            <h3>Добавить нового пользователя</h3>

            <form action="" method="post" id="new_form"></form>

            <table>
                <thead>
                <tr>
                    <th>Логин</th>
                    <th>Пароль</th>

                </tr>
                </thead>
                <tr>
                    <td>
                        <input name="login" type="text" value="" form="new_form">
<%--                        <input name="typeReq" type="hidden" value="save" form="new_form">--%>
                    </td>
                    <td><input name="login" type="text" value="" form="new_form"></td>
                    <td><input type="submit" class="waves-effect waves-light btn-small"
                               name="save" value="Сохранить" form="new_form"></td>
                </tr>
            </table>

            </div>
            </div>
        </div>
    <jsp:include page="footer.jsp" />
    </body>
</html>