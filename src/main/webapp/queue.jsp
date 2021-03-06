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
    <title>Список задач</title>
    <style>
        <%@include file="css/style.css"%>
    </style>

</head>
    <body>
    <jsp:include page="admin_nav.jsp" />

        <h1> Список задач </h1>

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

    <c:set var="count" value="0" scope="page" />
        <div class="row">
            <div class="col s12 m12">
                <div class="card-panel white">
<%--Текущие задачи                    --%>
                    <h2>Текущие задачи исполняемые в системе</h2>
                         <c:forEach var="task" items="${taskMl}">
                             <c:if test="${task.inWork==true}">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <h6>Задача на обучение модели</h6>
                        <table>
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>Количество потоков</th>

                                <th>Название модели</th>
                                <th>Дата создания</th>
                            </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td>${task.id}</td>
                                    <td>${task.nWorker}</td>
                                    <td>${task.model.title}</td>
                                    <td>${task.createdDate}<td>
                                </tr>

                            </tbody>
                        </table>
                         </c:if>
                         </c:forEach>
                    <c:forEach var="task" items="${taskCheck}">
                    <c:if test="${task.inWork==true}">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <h6>Задача на проверку датасета</h6>
                    <table>
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>Нормализовать</th>
                            <th>Название датасета</th>
                            <th>Дата создания</th>
                        </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <td>${task.id}</td>
                                <td>${task.normalize}</td>
                                <td>${task.dataset.title}</td>


                                <td>${task.createdDate}<td>
                            </tr>

                        </tbody>
                    </table>
                    </c:if>
                    </c:forEach>
    <c:forEach var="task" items="${taskMerge}">
    <c:if test="${task.inWork==true}">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <h6>Задача на слияние датасетов</h6>
    <table>
        <thead>
        <tr>
            <th>id</th>

            <th>Название датасета</th>


            <th>Список датасетов для слияния</th>
            <th>Дата создания</th>
        </tr>
        </thead>
        <tbody>

            <tr>
                <td>${task.id}</td>
                <td>${task.dataset.title}</td>


                <td>
                        ${task.sourceDatasets}
                </td>
                <td>${task.createdDate}<td>
            </tr>

        </tbody>
    </table>
    </c:if>
    </c:forEach>
    <c:forEach var="task" items="${taskAdmin}">
    <c:if test="${task.inWork==true}">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <h6>Задачи на решение текстовых файлов администратора</h6>
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>Название задачи</th>
            <th>Название модели</th>
            <th>Дата создания</th>
        </tr>
        </thead>
        <tbody>

            <tr>
                <td>${task.id}</td>
                <td>${task.title}</td>
                <td>${task.model.title}</td>


                <td>${task.createdDate}<td>
            </tr>

        </tbody>
    </table>
    </c:if>
    </c:forEach>
    <c:forEach var="task" items="${taskUser}">
    <c:if test="${task.inWork==true}">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <h6>Задачи на решение текстовых файлов пользователей</h6>
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>Название задачи</th>
            <th>Название модели</th>
            <th>Логин пользователя</th>
            <th>Дата создания</th>
        </tr>
        </thead>
        <tbody>

            <tr>
                <td>${task.id}</td>
                <td>${task.title}</td>
                <td>${task.model.title}</td>
                <td>${task.user.login}</td>
                <td>${task.createdDate}<td>
            </tr>

        </tbody>
    </table>
    </c:if>
    </c:forEach>
    <c:if test="${count==0}">
        <span>Нет текущих задач</span>
    </c:if>
<%-- Список задач--%>
    <h2>Архив всех задач в системе</h2>
                    <h4>Задачи на обучение моделей</h4>
                    <table>
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>Количество потоков</th>
                            <th>Исполняется</th>
                            <th>Завершенная задача</th>
                            <th>Название модели</th>
                            <th>Дата создания</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="task" items="${taskMl}">
                            <tr>
                                <td>${task.id}</td>
                                <td>${task.nWorker}</td>
                                <td>
                                    <c:if test="${task.inWork==true}">
                                        <label>
                                        <input name="visible" type="checkbox" checked value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                                        <span></span>
                                        </label>
                                    </c:if>
                                    <c:if test="${task.inWork==false}">
                                        <label>
                                        <input name="visible" type="checkbox" value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                                        <span></span>
                                        </label>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${task.completedTask==true}">
                                        <label>
                                        <input name="visible" type="checkbox" checked value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                                        <span></span>
                                        </label>
                                    </c:if>
                                    <c:if test="${task.completedTask==false}">
                                        <label>
                                        <input name="visible" type="checkbox" value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                                        <span></span>
                                        </label>
                                    </c:if>
                                </td>
                                <td>${task.model.title}</td>
                                <td>${task.createdDate}<td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
    <br>
    <h4>Задачи на валидацию новых датасетов</h4>

    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>Нормализовать</th>
            <th>Название датасета</th>
            <th>Исполняется</th>
            <th>Задача завершена</th>
            <th>Корректность</th>
            <th>Дата создания</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${taskCheck}">
            <tr>
                <td>${task.id}</td>
                <td>${task.normalize}</td>
                <td>${task.dataset.title}</td>
                <td>
                    <c:if test="${task.inWork==true}">
                        <label>
                        <input name="visible" type="checkbox" checked value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                    <c:if test="${task.inWork==false}">
                        <label>
                        <input name="visible" type="checkbox" value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                </td>
                <td>
                    <c:if test="${task.completedTask==true}">
                        <label>
                        <input name="visible" type="checkbox" checked value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                    <c:if test="${task.completedTask==false}">
                        <label>
                        <input name="visible" type="checkbox" value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                </td>
                <td>
                    <c:if test="${task.completedTask==true}">
                        <label>
                        <input name="visible" type="checkbox" checked value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                    <c:if test="${task.completedTask==false}">
                        <label>
                        <input name="visible" type="checkbox" value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                </td>
                <td>${task.createdDate}<td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <h4>Задачи на слияние датасетов</h4>
    <table>
        <thead>
        <tr>
            <th>id</th>

            <th>Название датасета</th>
            <th>Исполняется</th>
            <th>Задача завершена</th>
            <th>Список датасетов для слияния</th>
            <th>Дата создания</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${taskMerge}">
            <tr>
                <td>${task.id}</td>
                <td>${task.dataset.title}</td>

                <td>
                    <c:if test="${task.inWork==true}">
                        <label>
                        <input name="visible" type="checkbox" checked value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                    <c:if test="${task.inWork==false}">
                        <label>
                        <input name="visible" type="checkbox" value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                </td>
                <td>
                    <c:if test="${task.completedTask==true}">
                        <label>
                        <input name="visible" type="checkbox" checked value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                    <c:if test="${task.completedTask==false}">
                        <label>
                        <input name="visible" type="checkbox" value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                </td>
                <td>
                        ${task.sourceDatasets}
                </td>
                <td>${task.createdDate}<td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h4>Задачи на решение текстовых файлов администратора</h4>
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
        <c:forEach var="task" items="${taskAdmin}">
            <tr>
                <td>${task.id}</td>
                <td>${task.title}</td>
                <td>${task.model.title}</td>
                <td>
                    <c:if test="${task.inWork==true}">
                        <label>
                        <input name="visible" type="checkbox" checked value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                    <c:if test="${task.inWork==false}">
                        <label>
                        <input name="visible" type="checkbox" value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                </td>
                <td>
                    <c:if test="${task.completedTask==true}">
                        <label>
                        <input name="visible" type="checkbox" checked value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                    <c:if test="${task.completedTask==false}">
                        <label>
                        <input name="visible" type="checkbox" value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                </td>

                <td>${task.createdDate}<td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h4>Задачи на решение текстовых файлов пользователей</h4>
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>Название задачи</th>
            <th>Название модели</th>
            <th>Логин пользователя</th>
            <th>Исполняется</th>
            <th>Задача завершена</th>
            <th>Дата создания</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${taskUser}">
            <tr>
                <td>${task.id}</td>
                <td>${task.title}</td>
                <td>${task.model.title}</td>
                <td>${task.user.login}</td>
                <td>
                    <c:if test="${task.inWork==true}">
                        <label>
                        <input name="visible" type="checkbox" checked value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                    <c:if test="${task.inWork==false}">
                        <label>
                        <input name="visible" type="checkbox" value="${task.inWork}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                </td>
                <td>
                    <c:if test="${task.completedTask==true}">
                        <label>
                        <input name="visible" type="checkbox" checked value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>
                    <c:if test="${task.completedTask==false}">
                        <label>
                        <input name="visible" type="checkbox" value="${task.completedTask}" style="opacity: 0.0; pointer-events: auto;">
                        <span></span>
                        </label>
                    </c:if>

                <td>${task.createdDate}<td>
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