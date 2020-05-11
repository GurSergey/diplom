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
    <title>Выход пользователя</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="../nav.jsp" />

<p>Вы вышли из системы, можете перейти на главную страницу <a href="${path}">Главная страница</a></p>

<jsp:include page="../footer.jsp" />
</body>
</html>
