<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<footer class="page-footer blue darken-3">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">OSES</h5>
                <p class="grey-text text-lighten-4"></p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Перейти</h5>
                <ul>
                    <li><span class="red-text">Сверхсекретно!</span> <a class="grey-text text-lighten-3" href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/admin/menu">К админке</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright blue darken-4">
        <div class="container">
            2020 Gurilev Sergey
            <a class="grey-text  text-lighten-4 right" href=""></a>
        </div>
    </div>
</footer>