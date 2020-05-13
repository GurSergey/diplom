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
    <title>Список моделей</title>
    <style>
        <%@include file="css/style.css"%>
    </style>
</head>
    <body>
    <jsp:include page="admin_nav.jsp" />

        <h1> Список вопросов в голосовании c идентификатором ${pollId}  </h1>

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

        <c:forEach var="question" items="${questions}">
            <form action="" method="post" id="question_form_${question.id}"></form>
        </c:forEach>
        <div class="row">
            <div class="col s12 m12">
                <div class="card-panel white">

        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>Вопрос</th>
                    <th>Дата создания</th>
                </tr>
            </thead>
            <tbody>
                        <c:forEach var="question" items="${questions}">
                                 <tr>
                                    <td>${question.id}<input name="id" type="hidden" value="${question.id}" form="question_form_${question.id}">
                                        <input name="pollId" type="hidden" value="${pollId}" form="question_form_${question.id}"></td>
                                    <td><input name="question" type="text" value="${question.question}" form="question_form_${question.id}"></td>
                                    <td><input name="createDate" type="date" form="question_form_${question.id}"
                                               value="${question.createdDate}"></td>
                                    <td><input type="submit" class="waves-effect waves-light btn-small" name="update" value="Сохранить" form="question_form_${question.id}"></td>
                                    <td><input type="submit" class="waves-effect waves-light btn-small" name="delete" value="Удалить" form="question_form_${question.id}"></td>
                                    <td><a class="waves-effect waves-light btn-small" href="${path}/admin/variants/?questionId=${question.id}&pollId=${pollId}">
                                        Перейти к редактированию вариантов</a></td>
                            </tr>
                        </c:forEach>
            </tbody>
        </table>
        </div>



        <h3>Добавить новый вопрос</h3>

        <form action="" method="post" id="new_form"></form>

        <table>
            <thead>
            <tr>
                <th>Вопрос</th>
            </tr>
            </thead>
            <tr>
                <td>
                    <input name="question" type="text" value="" form="new_form">
                    <input name="pollId" type="hidden" value="${pollId}" form="new_form">
<%--                    <input name="typeReq" type="hidden" value="save" form="new_form">--%>
                </td>
                <td>
                    <input type="submit" class="waves-effect waves-light btn-small" name="save" value="Сохранить" form="new_form"></td>
            </tr>
        </table>


            </div>
        </div>
    <jsp:include page="footer.jsp" />
    </body>
</html>