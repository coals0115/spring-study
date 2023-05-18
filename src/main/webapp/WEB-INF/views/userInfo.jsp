<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="include/nav.jsp"></jsp:include>
<p>여기는 userInfo</p>
<p>ID : ${sessionScope.user.id}</p>
<p>Password : ${sessionScope.user.pwd}</p>
</body>
</html>
