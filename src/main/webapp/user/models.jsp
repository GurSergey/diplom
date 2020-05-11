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
    <title>Список моих голосований</title>
    <style>
        <%@include file="/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="../user_nav.jsp" />
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
<h3>Список моих голосований</h3>
<c:forEach var="poll" items="${polls}">
<div class="row">
    <div class="col s12 m12">
        <div class="card-panel white">
            <h6>Название</h6> <br> <p> ${poll.title} </p>
            <p>Дата начала голосования <br>${poll.startDate}</p>
            <p>Дата завершения <br> ${poll.dateTo}</p>
            <p><a href="${path}/user/answers?pollId=${poll.id}">Перейти к мои ответам</a></p>
        </div>
    </div>
</div>
</c:forEach>
<jsp:include page="../footer.jsp" />
</body>
</html>
