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
    <title>Список моделей</title>
    <style>
        <%@include file="css/style.css"%>
    </style>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var elems = document.querySelectorAll('select');
            var instances = M.FormSelect.init(elems, options);
        });

        // Or with jQuery

        $(document).ready(function(){
            $('select').formSelect();
        });
        </script>
</head>
    <body>
    <jsp:include page="admin_nav.jsp" />

        <h1> Список моделей </h1>

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

        <c:forEach var="model" items="${models}">
            <form action="" method="post" id="model_form_${model.id}"></form>
        </c:forEach>
        <div class="row">
            <div class="col s12 m12">
                <div class="card-panel white">
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>Название</th>
                    <th>Флаг заврешения</th>
<%--                    <th>Прогресс обучения</th>--%>
                    <th>Название датасета обучения</th>
                    <th>Точность на тестовых данных </th>
                    <th>Дата создания</th>
                </tr>
            </thead>
            <tbody>
                        <c:forEach var="model" items="${models}">
                                 <tr>
                                    <td>${model.id}<input name="id" type="hidden" value="${model.id}" form="model_form_${model.id}">
                                        <input name="modelId" type="hidden" value="${model.id}" form="model_form_${model.id}"></td>
                                    <td><input name="title" type="text" value="${model.title}" form="model_form_${model.id}"></td>
                                    <td>
                                         <c:if test="${model.completedLearn==true}">
                                             <input name="visible" type="checkbox" checked value="${model.completedLearn}" form="model_form_${model.id}" style="opacity: 1.0; pointer-events: auto;">
                                         </c:if>
                                         <c:if test="${model.completedLearn==false}">
                                             <input name="visible" type="checkbox" value="${model.completedLearn}" form="model_form_${model.id}" style="opacity: 1.0; pointer-events: auto;">
                                         </c:if>
                                    </td>
<%--                                    <td>${model.progress}</td>--%>
                                     <td>${model.datasetName}</td>
                                     <th>${model.testAccuracy}</th>
                                     <td>${model.createDate}<td>
                                    <td><input type="submit" class="waves-effect waves-light btn-small" name="update" value="Обновить название" form="model_form_${model.id}"></td>
                                    <td><input type="submit" class="waves-effect waves-light btn-small" name="delete" value="Удалить" form="model_form_${model.id}"></td>
                                     <c:if test="${model.completedLearn==true}"><td><a class="waves-effect waves-light btn-small"
                                       href="${pageContext.request.contextPath}/admin/model?id=${model.id}">Перейти к модели  </a></td></c:if>

                            </tr>
                        </c:forEach>
            </tbody>
        </table>
        </div>



        <h3>Добавить новую модель</h3>

        <form action="" method="post" id="new_form"></form>

        <table>
            <thead>
            <tr>
                <th>Название</th>
                <th>Датасет для обучения</th>
            </tr>
            </thead>
            <tr>
                <td>
                    <input name="title" type="text" value="" form="new_form">

<%--                    <input name="typeReq" type="hidden" value="save" form="new_form">--%>
                </td>
                <td>
                    <div class="input-field col s12">
                    <select name ="datasetId" style = "display: block;" form="new_form">
                        <option value="" disabled selected>Выберете датасет</option>
                        <c:forEach var="dataset" items="${datasets}">
                            <c:if test="${dataset.isCorrect==true}">
                                <option value="${dataset.id}">${dataset.title} - ID ${dataset.id}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    </div>
                </td>
                <td>
                    <input type="submit" class="waves-effect waves-light btn-small" name="save" value="Сохранить" form="new_form"></td>
            </tr>
        </table>


            </div>
        </div>
    <jsp:include page="footer.jsp" />
    </body>
</html>