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
    <title>Архитектура</title>
    <style>
        <%@include file="css/style.css"%>
    </style>
</head>
<body>

<!-- Wide card with share menu button -->
<nav>
    <div class="nav-wrapper blue darken-4">
        <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" class="brand-logo" style="margin-left: 20px">ML</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="./user_auth/">Войти как пользователь</a></li>
            <li><a href="./admin_auth/">Войти как администратор</a></li>
        </ul>
    </div>
</nav>
<div class="row">
    <div class="col s12 m12">
        <div class="card" >
            <div class="card-image" style="padding: 50px;">
                <img src="${pageContext.request.contextPath}/img/architecture.png">
            </div>
            <div class="card-content">
                <span class="card-title black-text" >Примерная структурная схема системы </span>
                <p class="s12">Автоматизированная система определение тональности текстовых сообщений</p>
            </div>
            <!--                <div class="card-action">-->
            <!--                    <a href="./admin_auth/">Перейти панели администратора</a><br>-->
            <!--                    <a href="./user_auth/">Перейти к использованию</a><br>-->
            <!--                    <a href="./architecture.jsp">Перейти к описанию архитектуры проекта (в процесс создания)</a><br>-->
            <!--                </div>-->
        </div>
    </div>
</div>

<footer class="page-footer blue darken-3">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">ML - бинарный классифкатор</h5>
                <p class="grey-text text-lighten-4"></p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Перейти</h5>
            </div>
        </div>
    </div>
    <div class="footer-copyright blue darken-4">
        <div class="container">
            2020 Gurilev Sergey
            <a class="grey-text  text-lighten-4 right" href=""></a>
        </div>
    </div>
</footer>

</body>
</html>