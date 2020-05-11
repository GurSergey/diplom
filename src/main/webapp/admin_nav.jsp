<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<nav>
    <div class="nav-wrapper orange darken-4">
        <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" class="brand-logo " style="margin-left: 20px">ML</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/polls/edit/">Редактор голосований</a></li>
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/list_users/">Список пользователей</a></li>
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/polls/result/">Завершенные голосования</a></li>
            <li><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/polls/open/">Список текущих голосований со статистикой</a></li>
        </ul>
    </div>
</nav>