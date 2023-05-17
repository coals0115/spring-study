<%--
  Created by IntelliJ IDEA.
  User: coals0115
  Date: 2023/05/07
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        img {
            width: 120px;
            height: 180px;
            cursor: pointer;

            transition: transform 1s ease;
        }

        img:hover {
            transform: translateY(-30px);
            outline: black solid 1px;
        }

        #pairDiv, span {
            background-color: greenyellow;
            width: 30%;
            margin-top: 10px;
            font-size: 20px;
        }

        .matched {
            opacity: 0.3;
        }
    </style>

</head>
<body>
<jsp:include page="include/nav.jsp"></jsp:include>
<div>
    <button id="btnShow">show</button>
    <button id="btnHide">hide</button>
    <button id="btnShuffle">shuffle</button>
    <button id="hint">힌트</button>
</div>
<div id="pairDiv">
    남은 pair의 개수 : <span id="pairsLeft"></span>
</div>
<br><br>
<div id="cardGameBoard">
    <!-- 카드를 보여줄 곳 -->
</div>
<script src="/static/js/cardGame.js"></script>
</body>

</html>