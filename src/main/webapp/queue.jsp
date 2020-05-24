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
                    <h4>Текущие задачи исполняемые в системе</h4>
                    <c:if test="${empty currentMl}">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <h6>Задача на обучение модели</h6>
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

                                <tr>
                                    <td>${currentMl.id}</td>
                                    <td>${currentMl.nWorker}</td>
                                    <td>
                                        <c:if test="${currentMl.inWork==true}">
                                            <input name="visible" type="checkbox" checked value="${currentMl.inWork}" style="opacity: 1.0; pointer-events: auto;">
                                        </c:if>
                                        <c:if test="${currentMl.inWork==false}">
                                            <input name="visible" type="checkbox" value="${currentMl.inWork}" style="opacity: 1.0; pointer-events: auto;">
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${currentMl.completedTask==true}">
                                            <input name="visible" type="checkbox" checked value="${currentMl.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                                        </c:if>
                                        <c:if test="${currentMl.completedTask==false}">
                                            <input name="visible" type="checkbox" value="${currentMl.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                                        </c:if>
                                    </td>
                                    <td>${currentMl.model.title}</td>
                                    <td>${currentMl.createDate}<td>
                                </tr>

                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty currentCheck}">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <h6>Задача на проверку датасета</h6>
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

                            <tr>
                                <td>${currentCheck.id}</td>
                                <td>${currentCheck.normalize}</td>
                                <td>${currentCheck.dataset.title}</td>
                                <td>
                                    <c:if test="${currentCheck.inWork==true}">
                                        <input name="visible" type="checkbox" checked value="${currentCheck.inWork}" style="opacity: 1.0; pointer-events: auto;">
                                    </c:if>
                                    <c:if test="${currentCheck.inWork==false}">
                                        <input name="visible" type="checkbox" value="${currentCheck.inWork}" style="opacity: 1.0; pointer-events: auto;">
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${currentCheck.completedTask==true}">
                                        <input name="visible" type="checkbox" checked value="${currentCheck.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                                    </c:if>
                                    <c:if test="${currentCheck.completedTask==false}">
                                        <input name="visible" type="checkbox" value="${currentCheck.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${currentCheck.completedTask==true}">
                                        <input name="visible" type="checkbox" checked value="${currentCheck.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                                    </c:if>
                                    <c:if test="${currentCheck.completedTask==false}">
                                        <input name="visible" type="checkbox" value="${currentCheck.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                                    </c:if>
                                </td>
                                <td>${currentCheck.createDate}<td>
                            </tr>

                        </tbody>
                    </table>
                    </c:if>
    <c:if test="${empty currentMerge}">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <h5>Задача на слияние датасетов</h5>
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

            <tr>
                <td>${currentMerge.id}</td>
                <td>${currentMerge.dataset.title}</td>

                <td>
                    <c:if test="${currentMerge.inWork==true}">
                        <input name="visible" type="checkbox" checked value="${currentMerge.inWork}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                    <c:if test="${currentMerge.inWork==false}">
                        <input name="visible" type="checkbox" value="${currentMerge.inWork}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                </td>
                <td>
                    <c:if test="${currentMerge.completedTask==true}">
                        <input name="visible" type="checkbox" checked value="${currentMerge.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                    <c:if test="${currentMerge.completedTask==false}">
                        <input name="visible" type="checkbox" value="${currentMerge.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                </td>
                <td>
                        ${currentMerge.sourceDatasets}
                </td>
                <td>${currentMerge.createDate}<td>
            </tr>

        </tbody>
    </table>
    </c:if>
    <c:if test="${empty currentAdmin}">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <h5>Задачи на решение текстовых файлов администратора</h5>
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

            <tr>
                <td>${currentAdmin.id}</td>
                <td>${currentAdmin.title}</td>
                <td>${currentAdmin.model.title}</td>
                <td>
                    <c:if test="${currentAdmin.inWork==true}">
                        <input name="visible" type="checkbox" checked value="${currentAdmin.inWork}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                    <c:if test="${currentAdmin.inWork==false}">
                        <input name="visible" type="checkbox" value="${currentAdmin.inWork}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                </td>
                <td>
                    <c:if test="${currentAdmin.completedTask==true}">
                        <input name="visible" type="checkbox" checked value="${currentAdmin.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                    <c:if test="${currentAdmin.completedTask==false}">
                        <input name="visible" type="checkbox" value="${currentAdmin.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                </td>
                <td>${currentAdmin.createDate}<td>
            </tr>

        </tbody>
    </table>
    </c:if>
    <c:if test="${empty currentUser}">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <h5>Задачи на решение текстовых файлов пользователей</h5>
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

            <tr>
                <td>${currentUser.id}</td>
                <td>${currentUser.title}</td>
                <td>${currentUser.model.title}</td>
                <td>${currentUser.user.login}</td>
                <td>
                    <c:if test="${currentUser.inWork==true}">
                        <input name="visible" type="checkbox" checked value="${currentUser.inWork}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                    <c:if test="${currentUser.inWork==false}">
                        <input name="visible" type="checkbox" value="${currentUser.inWork}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                </td>
                <td>
                    <c:if test="${currentUser.completedTask==true}">
                        <input name="visible" type="checkbox" checked value="${currentUser.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                    <c:if test="${currentUser.completedTask==false}">
                        <input name="visible" type="checkbox" value="${currentUser.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                    </c:if>
                </td>
                <td>${currentUser.sourceDatasets}
                </td>
                <td>${currentUser.createDate}<td>
            </tr>

        </tbody>
    </table>
    </c:if>
    <c:if test="${count==0}">
        <span>Нет текущих задач</span>
    </c:if>
<%-- Список задач--%>
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
                                <td>${task.model.title}</td>
                                <td>${task.createDate}<td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
        </div>
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
                                            <c:if test="${task.completedTask==true}">
                                                <input name="visible" type="checkbox" checked value="${task.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                                            </c:if>
                                            <c:if test="${task.completedTask==false}">
                                                <input name="visible" type="checkbox" value="${task.completedTask}" style="opacity: 1.0; pointer-events: auto;">
                                            </c:if>
                                        </td>
                                        <td>${task.createDate}<td>
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
                            <td>${task.sourceDatasets}
                            </td>
                            <td>${task.createDate}<td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    <jsp:include page="footer.jsp" />
    </body>
</html>