<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>board</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<jsp:include page="include/nav.jsp"></jsp:include>
    <h2>게시물 읽기</h2>
    <form action="" id="form">
        <input type="hidden" name="bno" value="${boardDto.bno}"/>
        <input type="text" name="title" value="${boardDto.title}" readonly>
        <textarea name="content" id="" cols="30" rows="10" readonly>${boardDto.content}</textarea>
        <button type="button" id="writeBtn" class="btn">글쓰기</button>
        <button type="button" id="modifyBtn" class="btn">수정</button>
        <button type="button" id="removeBtn" class="btn">삭제</button>
        <button type="button" id="listBtn" class="btn">목록</button>
    </form>
    <script>
        $(document).ready(function () {
            $('#listBtn').on("click", function () {
                location.href='<c:url value="/board/list" />?page=${page}&pageSize=${pageSize}';
            });

            $('#removeBtn').on("click", function () {
                if(!confirm("정말로 삭제하시겠습니까?")) return;

                let form = $('#form');
                form.attr("action", "<c:url value='/board/remove'/>?page=${page}&pageSize=${pageSize}")
                form.attr("method", "post");
                form.submit();
            });
        });
    </script>
</body>
</html>
