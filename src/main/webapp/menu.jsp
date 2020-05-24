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
<jsp:include page="admin_nav.jsp" />
<div class="row">
    <div class="col s12 m12">
      <div class="row">
            <div class="col s6 m6">
                <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/keys/">
                    <div class="card">
                        <div class="card-image" style="margin-left:auto; margin-right:auto; height: 300px; width: 300px;">
                            <img src="${pageContext.request.contextPath}/img/key_icon.png">
                            <span class="card-title"></span>
                        </div>
                        <div class="card-content">
                            <p>Добавить ключ доступа API</p>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col s6 m6">
                <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/datasets/">
                    <div class="card">
                        <div class="card-image" style="margin-left:auto; margin-right:auto; height: 300px; width: 300px;">
                            <img src="${pageContext.request.contextPath}/img/dataset_icon.png">
                            <span class="card-title"></span>
                        </div>
                        <div class="card-content">
                            <p>Добавить датасет</p>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col s6 m6">
                <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/models/">
                    <div class="card">
                        <div class="card-image" style="margin-left:auto; margin-right:auto; height: 300px; width: 300px;">
                            <img src="${pageContext.request.contextPath}/img/list_icon.png">
                            <span class="card-title"></span>
                        </div>
                        <div class="card-content">
                            <p>Список моделей</p>
                        </div>
                    </div>
                </a>
            </div>
<%--            <div class="col s6 m6">--%>
<%--                <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/model/">--%>
<%--                    <div class="card">--%>
<%--                        <div class="card-image" style="margin-left:auto; margin-right:auto; height: 300px; width: 300px;">--%>
<%--                            <img src="${pageContext.request.contextPath}/img/add_icon.png">--%>
<%--                            <span class="card-title"></span>--%>
<%--                        </div>--%>
<%--                        <div class="card-content">--%>
<%--                            <p>Создать модель</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </a>--%>
<%--            </div>--%>
            <div class="col s6 m6">
                <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/users/">
                    <div class="card">
                        <div class="card-image" style="margin-left:auto; margin-right:auto; height: 300px; width: 300px;">
                            <img src="${pageContext.request.contextPath}/img/user_icon.png">
                            <span class="card-title"></span>
                        </div>
                        <div class="card-content">
                            <p>Управление списком пользователей</p>
                        </div>
                    </div>
                </a>
            </div>
          <div class="col s6 m6">
              <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/te">
                  <div class="card">
                      <div class="card-image" style="margin-left:auto; margin-right:auto; height: 300px; width: 300px;">
                          <img src="${pageContext.request.contextPath}/img/task_text_icon.png">
                          <span class="card-title"></span>
                      </div>
                      <div class="card-content">
                          <p>Добавить задачу в текстовом файле</p>
                      </div>
                  </div>
              </a>
          </div>
            <div class="col s6 m6">
                <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/tasks/">
                    <div class="card">
                        <div class="card-image" style="margin-left:auto; margin-right:auto; height: 300px; width: 300px;">
                            <img src="${pageContext.request.contextPath}/img/task_icon.png">
                            <span class="card-title"></span>
                        </div>
                        <div class="card-content">
                            <p>Просмотреть список задач в системе</p>
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
