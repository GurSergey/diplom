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
    <title>Меню администратора</title>
    <style>
        <%@include file="css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="user_nav.jsp" />
<div class="row">
    <div class="col s12 m12">
    <div class="row">
            <div class="col s6 m6">
                <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/user/">
                    <div class="card">
                        <div class="card-image" style="margin-left:auto; margin-right:auto; height: 300px; width: 300px;">
                            <img src="${pageContext.request.contextPath}/img/task_text_icon.png">
                            <span class="card-title"></span>
                        </div>
                        <div class="card-content">
                            <p>Добавить текстовое задание</p>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col s6 m6">
                <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/user/">
                    <div class="card">
                        <div class="card-image" style="margin-left:auto; margin-right:auto; height: 300px; width: 300px;">
                            <img src="${pageContext.request.contextPath}/img/classification_icon.png">
                            <span class="card-title"></span>
                        </div>
                        <div class="card-content">
                            <p>Классификация в реальном времени</p>
                        </div>
                    </div>
                </a>
            </div>
        <div class="col s6 m6">
            <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/user/">
                <div class="card">
                    <div class="card-image" style="margin-left:auto; margin-right:auto; height: 300px; width: 300px;">
                        <img src="${pageContext.request.contextPath}/img/password_icon.png">
                        <span class="card-title"></span>
                    </div>
                    <div class="card-content">
                        <p>Сменить пароль</p>
                    </div>
                </div>
            </a>
        </div>


        </div>


    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
