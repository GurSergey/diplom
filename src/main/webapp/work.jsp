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

</head>
    <body>
    <jsp:include page="user_nav.jsp" />
    <div id ="idModel" hidden>${idModel}</div>
        <div class="row">
            <div class="col s12 m12">
                <div class="card-panel white">
                    <form action="" method="post" id="work">
                        <div class="row">
                        <div class="input-field col s12">
                        <textarea name="data" id="textarea1" class="materialize-textarea"> </textarea>
                            <label for="textarea1">Данные для классификации</label>
                        </div>
                        </div>
                        <button  class="waves-effect waves-light btn-small"  id="send" onclick="send()">Классифицировать текст!</button>

                    </form>
                    <h4>Результаты</h4>
                    test - 1
                    test - 0
                 </div>
            </div>
        </div>
    <script>
        function send(){
            let text = document.getElementById("textarea1").value
            let tasks1 = text.split("\n");
            var xhr = new XMLHttpRequest();
            let json = {id: document.getElementById("idModel").innerText,
                tasks: tasks1}
            json = JSON.stringify(json)
            var body = 'tasks=' + encodeURIComponent(json);

            xhr.open("POST", '/', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            xhr.onreadystatechange = function() {
                alert( this.responseText );
            }

            xhr.send(body);
        }
    </script>
    <jsp:include page="footer.jsp" />
    </body>
</html>