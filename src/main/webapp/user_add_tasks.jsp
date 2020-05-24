<%--
  Created by IntelliJ IDEA.
  User: serge
  Date: 23.05.2020
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<div class="row">
    <div class="col s12 m12">
        <div class="card-panel white">
        <table>
            <thead>
            <tr>
                <th>id</th>
                <th>Название задачи</th>
                <th>Название модели</th>
                <th>Исполняется</th>
                <th>Задача завершена</th>
                <th>Дата создания</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td>${task.id}</td>
                    <td>${task.title}</td>
                    <td>${task.model.title}</td>
                    <td>
                        <c:if test="${task.inWork==true}">
                            <input name="visible" type="checkbox" checked value="${task.inWork}" style="opacity: 1.0; pointer-events: auto;">
                        </c:if>
                        <c:if test="${task.inWork==false}">
                            <input name="visible" type="checkbox" value="${task.inWork}" style="opacity: 1.0; pointer-events: auto;">
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${task.completedTask==true}">
                            <input name="visible" type="checkbox" checked value="${task.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                        </c:if>
                        <c:if test="${task.completedTask==false}">
                            <input name="visible" type="checkbox" value="${task.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                        </c:if>
                    </td>
                    <td>
                            ${task.sourceDatasets}
                    </td>
                    <td>${task.createDate}<td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</div>
<h5>Добавить задание из файла</h5>
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
        <td> <div class="input-field col s12">
            <select name ="modelId" style = "display: block;" form="new_form">
                <option value="" disabled selected>Выберете модель</option>
                <c:forEach var="model" items="${models}">
                        <option value="${model.id}">${model.title} - ID ${model.id}</option>
                </c:forEach>
            </select>
        </div></td>
        <td><input name="taskFile" type="file" form="new_form"></td>
        <td>
            <input type="submit" class="waves-effect waves-light btn-small" name="save" value="Сохранить"
                   form="new_form"></td>
        <td>
            <%--                    <input name="typeReq" type="hidden" value="save" form="new_form">--%>
        </td>
    </tr>
</table>

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
</body>
</html>
