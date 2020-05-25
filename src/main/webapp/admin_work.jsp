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
    <jsp:include page="admin_nav.jsp" />
    <div id ="idModel" hidden>${idModel}</div>
        <div class="row">
            <div class="col s12 m12">
                <div class="card-panel white">
<%--                    <form action="" method="post" id="work">--%>
                        <div class="row">
                            <div class="input-field col s12">
                                <span >Данные для классификации</span>
                                <textarea name="data" id="textarea1" class="materialize-textarea"> </textarea>

                            </div>
                        </div>
                        <button  class="waves-effect waves-light btn-small"  id="send" onclick="send()">Классифицировать текст!</button>

<%--                    </form>--%>
                    <h4>Результаты</h4>
    <span id = "answer"></span>
                 </div>
            </div>
        </div>
    <script>
        function send(){
            let text = document.getElementById("textarea1").value;
            let tasks1 = text.split("\n");
            let xhr = new XMLHttpRequest();
            let json = {id: document.getElementById("idModel").innerText,
                data: tasks1}
                json = JSON.stringify(json)
            // alert(json)
            var body = 'tasks=' + encodeURIComponent(json);

            xhr.open("POST", '', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            xhr.onreadystatechange = function() {
                // console.log(this.responseText );
                obj = JSON.parse(this.responseText )
                str = ""
                for (let i = 0; i < obj.answers.length; i++) {
                    str += obj.answers[i].text +" Метка: "+obj.answers[i].answer+"<br>";
                }
                document.getElementById("answer").innerHTML = str
            }

             xhr.send(body);
        }
    </script>
    <jsp:include page="footer.jsp" />
    </body>
</html>