<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<nav>
    <div class="nav-wrapper blue darken-4">
        <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" class="brand-logo " style="margin-left: 20px">ML</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
<%--            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/user/menu">Меню пользователя</a></li>--%>
<%--            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/user_registration">Зарегестрироваться</a></li>--%>
<%--            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/user_auth">Авторизоваться</a></li>--%>
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/user/models/">Список обученных моделей</a></li>
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/user/logout/"> Выйти из профиля, ${userName}</a></li>

        </ul>
    </div>
</nav>