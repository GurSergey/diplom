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
                                    <td>${dataset.filename}</td>
                                     <td>${dataset.createdDate}</td>

                                     <td>
                                         <c:if test="${dataset.checking==true}">
                                             <input name="visible" type="checkbox" checked value="${dataset.checking}"  style="opacity: 1.0; pointer-events: auto;">
                                         </c:if>
                                         <c:if test="${dataset.checking==false}">
                                             <input name="visible" type="checkbox" value="${dataset.checking}"  style="opacity: 1.0; pointer-events: auto;">
                                         </c:if>
                                     </td>
                                     <td>
                                         <c:if test="${dataset.isCorrect==true}">
                                             <input name="visible" type="checkbox" checked value="${dataset.isCorrect}"
                                                    style="opacity: 1.0; pointer-events: auto;">
                                         </c:if>
                                         <c:if test="${dataset.isCorrect==false}">
                                             <input name="visible" type="checkbox" value="${dataset.isCorrect}"  style="opacity: 1.0; pointer-events: auto;">
                                         </c:if>
                                     </td>
                                     <td><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/download/?id=${dataset.id}"
                                            class="waves-effect waves-light btn-small" >Скачать</a></td>
                                     <td><input type="submit" class="waves-effect waves-light btn-small" name="update" value="Обновить название" form="dataset_form_${dataset.id}"></td>
                                     <td><input type="submit" class="waves-effect waves-light btn-small" name="delete" value="Удалить" form="dataset_form_${dataset.id}"></td>

                            </tr>
                        </c:forEach>
            </tbody>
        </table>
        </div>



        <h3>Добавить новый датасет</h3>

        <h5>Добавить из файла</h5>
        <form action="" method="post" enctype="multipart/form-data" id="new_form" > </form>

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
                            <input name="title" type="text"  value="" form="new_form">
                        </td>
                        <td><input type="checkbox" value="normalize" /></td>
                        <td><input name="datasetFile" type="file" form="new_form"></td>
                        <td>
                            <input type="submit" class="waves-effect waves-light btn-small" name="save" value="Сохранить"
                                   form="new_form"></td>
                        <td>
                            <%--                    <input name="typeReq" type="hidden" value="save" form="new_form">--%>
                        </td>
                    </tr>
                </table>
                <h5>Соеденить из нескольких датасетов</h5>
                <form action="" method="post" enctype="multipart/form-data" id="new_form1" > </form>
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
<%--                        <div class="input-field col s12">--%>
<%--                            <div class="select-wrapper"><input class="select-dropdown dropdown-trigger" type="text" readonly="true" data-target="select-options-996ff613-8974-c5fe-7f2a-3625147d1531"><ul id="select-options-996ff613-8974-c5fe-7f2a-3625147d1531" class="dropdown-content select-dropdown multiple-select-dropdown" tabindex="0" style=""><li class="disabled selected" id="select-options-996ff613-8974-c5fe-7f2a-3625147d15310" tabindex="0"><span><label><input type="checkbox" disabled="" "=""><span>Choose your option</span></label></span></li><li id="select-options-996ff613-8974-c5fe-7f2a-3625147d15311" tabindex="0"><span><label><input type="checkbox" "=""><span>Option 1</span></label></span></li><li id="select-options-996ff613-8974-c5fe-7f2a-3625147d15312" tabindex="0"><span><label><input type="checkbox" "=""><span>Option 2</span></label></span></li><li id="select-options-996ff613-8974-c5fe-7f2a-3625147d15313" tabindex="0"><span><label><input type="checkbox" "=""><span>Option 3</span></label></span></li></ul><svg class="caret" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"></path><path d="M0 0h24v24H0z" fill="none"></path></svg><select multiple="" tabindex="-1">--%>
<%--                                <option value="" disabled="" selected="">Choose your option</option>--%>
<%--                                <option value="1">Option 1</option>--%>
<%--                                <option value="2">Option 2</option>--%>
<%--                                <option value="3">Option 3</option>--%>
<%--                            </select></div>--%>
                        <div style="overflow-y: scroll; height:100px;">
                            <c:forEach var="dataset" items="${datasets}">

                                <c:if test="${dataset.isCorrect==true}">

                                    <p>
                                        <label>
                                            <input type="checkbox" value="${dataset.id}" />
                                            <span style="color: black;">${dataset.title}
                                        id = ${dataset.id}</span>
                                        </label>
                                    </p>
                                </c:if>

                            </c:forEach>
                        </div>
<%--                                <select multiple name ="datasetId" style = "display: block;">--%>
<%--                                    <option value="" disabled selected>Выбрать датасеты</option>--%>
<%--                                    <c:forEach var="dataset" items="${datasets}">--%>



<%--                                        <c:if test="${dataset.isCorrect==true}">--%>
<%--                                            <option value="${dataset.id}">${dataset.title}--%>
<%--                                                id = ${dataset.id} </option>--%>
<%--                                        </c:if>--%>
<%--                                    </c:forEach>--%>
<%--                                </select>--%>
<%--                            <label>Выбрать несколько датасетов</label>--%>

                        </td>
                        <td>
                            <input type="submit" class="waves-effect waves-light btn-small" name="save" value="Сохранить"
                                   form="new_form1"></td>
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