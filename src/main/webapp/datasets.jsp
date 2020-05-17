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
    <title>Список датасетов</title>
    <style>
        <%@include file="css/style.css"%>
    </style>



</head>
    <body>
    <div id="preloader" style =
                 "position: fixed; display: none; width: 100%; height: 100%;
                  background-color: rgba(0,0,0,0.5); z-index:999999;">
        <div class="card-panel white" style="position: absolute; margin: 5%; top: 30%; opacity: 100%;">
            <h4>Идет процесс загрузки датасета на сервер. Подождите пожалуйста.</h4>
            <span id = "progress_text">0%</span>
            <div class="progress">
                <div class="determinate" id = "progress_bar" style="width: 0;"></div>
            </div>
        </div>
    </div>
    <jsp:include page="admin_nav.jsp" />

        <h1> Список датасетов </h1>

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

        <c:forEach var="dataset" items="${datasets}">
            <form action="" method="post" id="dataset_form_${dataset.id}"></form>
        </c:forEach>
        <div class="row">
            <div class="col s12 m12">
                <div class="card-panel white">

        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>Название</th>
                    <th>Имя файла</th>
                    <th>Дата создания</th>
                </tr>
            </thead>
            <tbody>
                        <c:forEach var="dataset" items="${datasets}">
                                 <tr>
                                    <td>${dataset.id}<input name="id" type="hidden" value="${dataset.id}" form="dataset_form_${dataset.id}">
                                        <input name="modelId" type="hidden" value="${dataset.id}" form="dataset_form_${dataset.id}"></td>
                                    <td><input name="title" type="text" value="${dataset.title}" form="dataset_form_${dataset.id}"></td>
                                    <td>${dataset.filename}</td>
                                     <td>${dataset.createdDate}</td>
                                    <td><input type="submit" class="waves-effect waves-light btn-small" name="???" value="Скачать" form="dataset_form_${dataset.id}"></td>
                                    <td><input type="submit" class="waves-effect waves-light btn-small" name="delete" value="Удалить" form="dataset_form_${dataset.id}"></td>

                            </tr>
                        </c:forEach>
            </tbody>
        </table>
        </div>



        <h3>Добавить новый датасет</h3>
<%--   --%>
        <form action="" method="post" enctype="multipart/form-data" id="new_form" > </form>

                <table>
            <thead>
            <tr>
                <th>Название</th>
                <th>Файл</th>
            </tr>
            </thead>
            <tr>
                <td>
                    <input name="title" type="text"  value="" form="new_form">
                </td>
                <td><input name="datasetFile" type="file" form="new_form"></td>
                <td>
                    <input type="submit" class="waves-effect waves-light btn-small" name="save" value="Сохранить"
                           form="new_form"></td>
                <td>
                    <%--                    <input name="typeReq" type="hidden" value="save" form="new_form">--%>
                </td>
            </tr>
        </table>


            </div>
        </div>


    <script>
        document.forms.namedItem("new_form").onsubmit = function() {
            upload("123");
            // var input = this.elements.datasetFile;
            // var title = this.elements.title;
            // var file = input.files[0];
            // if (file) {
            //     upload(file);
            // }
            // return false;
        }
        function upload(file) {

            var formData = new FormData(document.forms.new_form);


            // отослать
            var xhr = new XMLHttpRequest();
            xhr.upload.onprogress = function(event) {
                document.getElementById("preloader").style.display = "block";
                document.getElementById("progress_text").textContent = (event.loaded / event.total * 100).toFixed(1) + " %";
                document.getElementById("progress_bar").style.width = event.loaded / event.total * 100 + "%";
                console.log(event.loaded + ' / ' + event.total);
            }
            // обработчики успеха и ошибки
            // если status == 200, то это успех, иначе ошибка
            xhr.onload = xhr.onerror = function() {
                if (this.status === 200) {
                    console.log("success");
                    document.getElementById("preloader").style.display = "none";
                } else {
                    console.log("error " + this.status);
                }
            };
            xhr.open("POST", "upload", true);
            xhr.send(formData);

            // var xhr = new XMLHttpRequest();
            // // обработчик для отправки

            // xhr.open("POST", "upload", true);
            // xhr.setRequestHeader('Content-Type', 'multipart/form-data');
            // xhr.send(file);

        }
    </script>

    <jsp:include page="footer.jsp" />
    </body>
</html>