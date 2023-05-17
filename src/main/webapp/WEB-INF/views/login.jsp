<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<%--<c:set var="cookieId" value="${cookie.get('id').value}"></c:set>--%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/static/css/login.css?ver=1'/>"> <!-- c:url context-root를 포함한 경로를 추가해준다. -->
</head>
<body>
    <jsp:include page="include/nav.jsp"></jsp:include>
    <div id="login-content">
        <form class="login-form" action="/login/login" method="post">
            <input type="hidden" id="toURL" name="toURL" value="${param.toURL}">
            <div class="imgcontainer">
                <img src="/static/img/bear2.png" alt="농담곰ㅎ">
            </div>

            <div class="container">
                <label for="id"><b>id</b></label>
                <input type="text" id="id" name="id" value="${cookie.id.value}" placeholder="Enter ID">
<%--                <input type="text" id="id" name="id" value="${empty cookieId ? "" : cookieId}" placeholder="Enter ID">--%>

                <label for="pwd"><b>Password</b></label>
                <input type="text" id="pwd" name="pwd" placeholder="Enter Password">

                <label>
                    <input type="checkbox" checked="checked" name="remember" ${empty cookie.id.value ? "" : "checked"}> Remember me
                </label>
                <button type="submit">로그인</button>
            </div>
        </form>
    </div>
</body>
</html>
