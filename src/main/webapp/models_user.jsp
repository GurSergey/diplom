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

        <h1> Список моделей </h1>


        <div class="row">
            <div class="col s12 m12">
                <div class="card-panel white">
        <table>
            <thead>
                <tr>
                    <th>Название</th>
                    <th>Название датасета обучения</th>
                    <th>Положительная метка</th>
                    <th>Негативная метка</th>
                    <th>Точность на тестовых данных </th>
                    <th>Дата создания</th>
                </tr>
            </thead>
            <tbody>
                        <c:forEach var="model" items="${models}">
                                 <tr>

                                    <td>${model.title}</td>

                                    <td>${model.progress}</td>
                                     <td>${model.datasetName}</td>
                                     <td>${model.positiveLabel}</td>
                                     <td>${model.negativeLabel}</td>
                                     <td>${model.testAccuracy}</td>
                                     <td>${model.createDate}<td>
                                    <td><a class="waves-effect waves-light btn-small"
                                           href="${pageContext.request.contextPath}/user/work?id=${model.id}">Перейти к работе с моделью </a></td>


                            </tr>
                        </c:forEach>
            </tbody>
        </table>
        </div>



            </div>
        </div>
    <jsp:include page="footer.jsp" />
    </body>
</html>