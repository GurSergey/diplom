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
    <title>Меню пользователя</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>


<jsp:include page="../user_nav.jsp" />
<div class="row">
    <div class="col s12 m12">
        <div class="card-panel white">
            <p><a href="${path}/user/open/">Перейти к списку открытых голосований</a></p>
            <p><a href="${path}/polls/result/">К результатам завершенных голосований</a></p>
            <p><a href="${path}/user/polls/">К списку голосований, в которях я участвовал</a></p>
            <p><a href="${path}/user/edit_profile/">К странице редактирования профиля</a></p>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>
