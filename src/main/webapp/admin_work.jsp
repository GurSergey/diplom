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
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <title>Список моделей</title>
    <style>
        <%@include file="css/style.css"%>
    </style>

    <script>
        var recognition = new webkitSpeechRecognition();
        recognition.lang = 'ru-RU'
        recognition.continuous = true;
        let started = false;
        //recognition.interimResults = true;
        function start(){
            started = !started;
            if(started){
                document.getElementById("bStart").innerHTML = '<i style="font-size: 3rem;" class="material-icons">mic_off</i>'
                recognition.onresult = function(event) {
                    console.log(event);
                    // output.innerHTML = "";
                    for(var i=0; i<event.results.length; i++){
                        document.getElementById("textarea1").value = document.getElementById("textarea1").value
                            + event.results[i][0].transcript;
                    }
                }
                recognition.start();
            } else{
                document.getElementById("bStart").innerHTML = '<i style="font-size: 3rem;" class="material-icons">mic</i>'
                document.getElementById("textarea1").value += "\n"
                recognition.stop();
            }
        }
    </script>

</head>
<body>
<jsp:include page="user_nav.jsp" />
<div id ="idModel" hidden>${idModel}</div>
<div class="row">
    <div class="col s12 m12">
        <div class="card-panel white">
            <h5>Преобразовать голос в текст</h5>
            <button id ="bStart" class="waves-effect waves-light" onclick = "start();">
                <i style="font-size: 3rem;" class="material-icons">mic</i></button>
            <span>(работает только в браузерах на движке WebKit)</span>

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