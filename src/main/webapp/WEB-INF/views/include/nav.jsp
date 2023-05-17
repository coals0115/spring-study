<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> <!-- TODO jsp inclue하면 얘 중복으로 안 써도 되는지? -->
<c:set var="loginOutLink" value="${sessionScope.id != null ? '/login/logout' : '/login/login'}"></c:set>
<c:set var="loginOutText" value="${sessionScope.id != null ? 'Logout' : 'Login'}"></c:set>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/static/css/nav.css'/>"> <!-- c:url context-root를 포함한 경로를 추가해준다. -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/static/css/font-awesome.min.css">
</head>
<body>
<div class="topnav">
    <!-- 으으음 url뒤에 쿼리스트링으로 active = className 붙여서 보내서 nav에서는  -->
<%--    <a id="home" href="#" onclick="location.href='/?active=' + this.id"><i class="fa fa-home"></i></a>--%>
    <a id="home" href="#" onclick="location.href='/?active=' + this.id">Home</a>
    <a id="board" href="#" onclick="location.href='/board/list?active=' + this.id">게시판</a>
    <a id="loginOut" href="#" onclick="location.href='${loginOutLink}?active=' + this.id">${loginOutText}</a>
    <a id="cardGame" href="#" onclick="location.href='/board/cardGame?active=' + this.id">카드 게임adf</a>
</div>
</body>
    <!-- 커서 -->
    <script>
        let active = '${param.active}';
        document.getElementById(active).classList.add("active");
        // 여기서 board에 클릭 이벤트가 발생되어서 active 클래스가 먹힌 뒤에 /board/list로 이동해서 다시 초기화됨
        // 그럼? href 뒤에 쿼리 스트링으로 avtive = this.id 보내서
        // ${param.active}를 가져오고
        // document.getElementById(${param.active})이거인 요소의 classList.toggle('active')추가

    </script>
</html>
