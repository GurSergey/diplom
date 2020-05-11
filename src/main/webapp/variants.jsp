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
    <jsp:include page="../admin_nav.jsp" />

        <h1> Список вариантов в голосовании c идентификатором ${questionId}  </h1>

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

        <c:forEach var="variant" items="${variants}">
            <form action="" method="post" id="variant_form_${variant.id}"></form>
        </c:forEach>
    <div class="row">
        <div class="col s12 m12">
            <div class="card-panel white">
                <a href="${path}/admin/questions/?pollId=${pollId}">Вернуться к списку вопросов</a>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>Текст</th>
                    <th>Cохранить</th>
                    <th>Удалить</th>
                </tr>
            </thead>
            <tbody>
                        <c:forEach var="variant" items="${variants}">
                                 <tr>
                                    <td>${variant.id}<input name="id" type="hidden" value="${variant.id}" form="variant_form_${variant.id}">
                                        <input name="questionId" type="hidden" value="${questionId}" form="variant_form_${variant.id}"></td>
                                    <td><input name="text" type="text" value="${variant.text}" form="variant_form_${variant.id}"></td>
                                    <td><input type="submit" name="update" class="waves-effect waves-light btn-small" value="Сохранить" form="variant_form_${variant.id}"></td>
                                    <td><input type="submit" name="delete" class="waves-effect waves-light btn-small" value="Удалить" form="variant_form_${variant.id}"></td>
                            </tr>
                        </c:forEach>
            </tbody>
        </table>



        <h3>Добавить новый вариант</h3>

        <form action="" method="post" id="new_form"></form>

        <table>
            <thead>
            <tr>
                <th>Текст</th>
                <th>Сохранить</th>
            </tr>
            </thead>
            <tr>
                <td>
                    <input name="text" type="text" value="" form="new_form">
                    <input name="questionId" type="hidden"  value="${questionId}" form="new_form">
<%--                    <input name="typeReq" type="hidden" value="save" form="new_form">--%>
                </td>
                <td><input type="submit" name="save" class="waves-effect waves-light btn-small" value="Сохранить" form="new_form"></td>
            </tr>
        </table>

        </div>
    </div>
    </div>
    <jsp:include page="../footer.jsp" />
    </body>
</html>