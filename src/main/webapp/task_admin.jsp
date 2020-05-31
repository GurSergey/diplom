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
    <title>Список задач</title>
    <style>
        <%@include file="css/style.css"%>
    </style>

</head>
    <body>
    <jsp:include page="admin_nav.jsp" />

        <h1> Список задач </h1>

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

    <c:set var="count" value="0" scope="page" />
        <div class="row">
            <div class="col s12 m12">
                <div class="card-panel white">
<%--Текущие задачи --%>
                    <h4>Текущая задача</h4>


        <c:forEach var="task" items="${taskAdmin}">
        <c:if test="${task.inWork==true}">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <h6>Задачи на решение текстовых файлов администратора</h6>
        <table>
            <thead>
            <tr>
                <th>id</th>
                <th>Название задачи</th>
                <th>Название модели</th>
                <th>Дата создания</th>
            </tr>
            </thead>
            <tbody>

                <tr>
                    <td>${task.id}</td>
                    <td>${task.title}</td>
                    <td>${task.model.title}</td>
                    <td>${task.createdDate}<td>
                </tr>

            </tbody>
        </table>
        </c:if>

        <h4>Задачи из файла</h4>
        <table>
            <thead>
            <tr>
                <th>id</th>
                <th>Название задачи</th>
                <th>Название модели</th>
                <th>Исполняется</th>
                <th>Задача завершена</th>
                <th>Дата создания</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="task" items="${taskAdmin}">
                <tr>
                    <td>${task.id}</td>
                    <td>${task.title}</td>
                    <td>${task.model.title}</td>
                    <td>
                        <c:if test="${task.inWork==true}">
                            <label>
                            <input name="visible" type="checkbox" checked value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                            <span></span>
                            </label>
                        </c:if>
                        <c:if test="${task.inWork==false}">
                            <label>
                            <input name="visible" type="checkbox" value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                            <span></span>
                            </label>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${task.completedTask==true}">
                            <label>
                            <input name="visible" type="checkbox" checked value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                            <span></span>
                            </label>
                        </c:if>
                        <c:if test="${task.completedTask==false}">
                            <label>
                            <input name="visible" type="checkbox" value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                            <span></span>
                            </label>
                        </c:if>
                    </td>

                    <td>${task.createdDate}<td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        </div>

            </div>
        </div>
    <jsp:include page="footer.jsp" />
    </body>
</html>