<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>BoardList</title>
</head>
<body>
<jsp:include page="include/nav.jsp" />
<form action="">
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>이름</th>
            <th>등록일</th>
            <th>조회수</th>
        </tr>
        <c:forEach var="board" items="${boardList}">
            <tr>
                <td><a href="<c:url value="/board/read?bno=${board.bno}&page=${p.curPage}&pageSize=${p.pageSize}"/>">${board.bno}</a></td>
<%--                <td><a href="<c:url value="/board/read?bno=${board.bno}"/>">${board.bno}</a></td>--%>
                <td>${board.title}</td>
                <td>${board.writer}</td>
                <td>${board.reg_date}</td>
                <td>${board.view_cnt}</td>
            </tr>
        </c:forEach>
    </table>
</form>

<c:if test="${p.showPrev}"> <a href="/board/list?page=${p.beginPage-1}&pageSize=${p.pageSize}">&lt;</a> </c:if>
<c:forEach var="i" begin="${p.beginPage}" end="${p.engPage}">
    <a href="/board/list?page=${i}&pageSize=${p.pageSize}">${i}</a>
</c:forEach>
<c:if test="${p.showNext}"> <a href="/board/list?page=${p.engPage+1}&pageSize=${p.pageSize}">&gt;</a> </c:if>
</body>
</html>
