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
<%--    <script type="text/javascript" src="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/js/materialize.min.js"></script>--%>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var elems = document.querySelectorAll('select');
            var instances = M.FormSelect.init(elems, options);
        });
    </script>


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
                    <th>Датасет проверен</th>
                    <th>Датасет корректен</th>
                </tr>
            </thead>
            <tbody>
                        <c:forEach var="dataset" items="${datasets}">
                                 <tr>
                                    <td>${dataset.id}<input name="id" type="hidden" value="${dataset.id}" form="dataset_form_${dataset.id}">
                                        <input name="modelId" type="hidden" value="${dataset.id}" form="dataset_form_${dataset.id}"></td>
                                    <td><input name="title" type="text" value="${dataset.title}" form="dataset_form_${dataset.id}"></td>
                                    <td style="max-width: 15%;"><input name="filename" type="text" value="${dataset.filename}"></td>
                                     <td>${dataset.createdDate}</td>

                                     <td>
                                         <c:if test="${dataset.checking==true}">
                                             <label>
                                             <input name="visible" type="checkbox" checked value="${dataset.checking}"  style="opacity: 0.0; pointer-events: auto;">
                                             <span></span>
                                             </label>
                                         </c:if>
                                         <c:if test="${dataset.checking==false}">
                                             <label>
                                             <input name="visible" type="checkbox" value="${dataset.checking}"  style="opacity: 0.0; pointer-events: auto;">
                                             <span></span>
                                             </label>
                                         </c:if>
                                     </td>
                                     <td>
                                         <c:if test="${dataset.isCorrect==true}">
                                             <label>
                                             <input name="visible" type="checkbox" checked value="${dataset.isCorrect}"
                                                    style="opacity: 0.0; pointer-events: auto;">
                                             <span></span>
                                             </label>
                                         </c:if>
                                         <c:if test="${dataset.isCorrect==false}">
                                             <label>
                                             <input name="visible" type="checkbox" value="${dataset.isCorrect}"  style="opacity: 0.0; pointer-events: auto;">
                                             <span></span>
                                             </label>
                                         </c:if>
                                     </td>

                                     <td><input type="submit" class="waves-effect waves-light btn-small" name="update" value="Обновить название" form="dataset_form_${dataset.id}"></td>
                                     <c:if test="${dataset.checking==true}">
                                         <td><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/download/?id=${dataset.id}"
                                                class="waves-effect waves-light btn-small" >Скачать</a></td>
                                     </c:if>
                                     <c:if test="${dataset.checking==true}">
                                     <td><input type="submit" class="waves-effect waves-light btn-small" name="delete" value="Удалить" form="dataset_form_${dataset.id}"></td>
                                     </c:if>
                                     <input name="typeReq" type="hidden" value="update" form="dataset_form_${dataset.id}">
                            </tr>
                        </c:forEach>
            </tbody>
        </table>
        </div>



        <h3>Добавить новый датасет</h3>

        <h5>Добавить из файла</h5>
        <form action=""  enctype="multipart/form-data" id="new_form" > </form>

                <table>
                    <thead>
                    <tr>
                        <th>Название</th>
                        <th>Нормализовать</th>
                        <th>Файл</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>
                            <label>
                            <input name="title" type="text"  value="" form="new_form">
                            <span></span>
                            </label>
                        </td>
                        <td>
                            <label>
                            <input type="checkbox" name="normalize" value="normalize" style="opacity: 0.0; pointer-events: auto;" form="new_form"/>
                            <span></span>
                            </label>
                        </td>
                        <td><input name="datasetFile" type="file" form="new_form"></td>
                        <td>
                            <button class="waves-effect waves-light btn-small" onclick="onclickSubmit()">Сохранить</button>
<%--                            <input type="submit" class="waves-effect waves-light btn-small" name="save" value="Сохранить"--%>
<%--                                   form="new_form"></td>--%>
                        <td>
                            <input name="typeReq" type="hidden" value="save" form="new_form">
                        </td>
                    </tr>
                </table>
                <h5>Соеденить из нескольких датасетов</h5>
                <form action="" method="post" id="new_form1" > </form>
                <table>
                    <thead>
                    <tr>
                        <th>Название</th>
                        <th>Датасеты для объединения</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>
                            <input name="title" type="text"  value="" form="new_form1">
                        </td>
                        <td>

                        <div style="overflow-y: scroll; height:100px;">
                            <c:set var="countMerge" value="0" scope="page" />
                            <c:forEach var="dataset" items="${datasets}">

                                <c:if test="${dataset.isCorrect==true}">
                                    <c:set var="countMerge" value="${countMerge + 1}" scope="page"/>
                                    <p>
                                        <label>
                                            <input type="checkbox" name="dataset_${countMerge}" value="${dataset.id}"
                                                   form="new_form1"/>
                                            <span style="color: black;">${dataset.title}
                                        id = ${dataset.id}</span>
                                        </label>
                                    </p>
                                </c:if>

                            </c:forEach>
                            <input name="count" type="hidden" value="${countMerge}" form="new_form1">
                            <input name="typeReq" type="hidden" value="merge" form="new_form1">
                        </div>


                        </td>
                        <td>
                            <input type="submit" class="waves-effect waves-light btn-small" name="merge" value="Сохранить"
                                   form="new_form1"></td>
                        <td>
                            <%--                    <input name="typeReq" type="hidden" value="save" form="new_form">--%>
                        </td>
                    </tr>
                </table>

            </div>
        </div>


    <script>
        function onclickSubmit() {
            upload();
        }
        // document.forms.namedItem("new_form").onsubmit = function() {
        //     upload("123");
        //     // var input = this.elements.datasetFile;
        //     // var title = this.elements.title;
        //     // var file = input.files[0];
        //     // if (file) {
        //     //     upload(file);
        //     // }
        //     // return false;
        // }
        function upload() {

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
                    document.location.reload(true)
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