<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<nav>
    <div class="nav-wrapper orange darken-4">
        <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/menu" class="brand-logo " style="margin-left: 20px">ML</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/keys/">Управление ключами</a></li>
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/datasets/">Датасеты</a></li>
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/models/">Модели</a></li>
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/users/">Пользователи</a></li>
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/tasks/">Список задач в системе</a></li>
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/logout/"> Выйти из панели администратора</a></li>
        </ul>
    </div>
</nav>