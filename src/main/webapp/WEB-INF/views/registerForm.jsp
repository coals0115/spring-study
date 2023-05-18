<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        * {
            box-sizing: border-box
        }

        input[type=text],
        input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus,
        input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

        button {
            background-color: #04AA6D;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        button:hover {
            opacity: 1;
        }

        .cancelbtn {
            padding: 14px 20px;
            background-color: #f44336;
        }

        .cancelbtn,
        .signupbtn {
            /* float를 사용할 경우 컨테이너에서 떠있게되며 일반적인 흐름에서 벗어나게 된다. 따라서, 부모 요소가 자식 요소의 높이를 인식하지 못하게된다.
            이 문제를 해결하려면 부모 요소에 clearfix를 적용해야 한다. */
            float: left;
            width: 50%;
        }

        .container {
            padding: 16px;
        }

        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: #474e5d;
            padding-top: 50px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto 15% auto;
            border: 1px solid #888;
            width: 80%;
        }

        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

        .close {
            position: absolute;
            right: 35px;
            top: 15px;
            font-size: 40px;
            font-weight: bold;
            color: #f1f1f1;
        }

        .close:hover,
        .close:focus {
            color: #f44336;
            cursor: pointer;
        }

        /* <요약> : 부모 요소가 float된 자식 요소의 높이를 올바르게 인식 가능
        1. .clearfix 클래스가 적용된 요소의 내용 바로 뒤에 빈 가상 요소를 생성
        2. 양쪽에서 float된 요소를 무시하도록 설정
        3. 테이블 디스플레이 유형을 사용하여 요소를 스타일링
        */
        .clearfix::after {
            content: "";
            /* 가상 요소 내용 설정 */
            /* 요소의 양쪽에서 float된 요소를 무시하도록 지시한다. 즉, 가상 요소가 float된 요소 아래에 배치되어 부모 요소가 float된 자식 요소의 높이를 올바르게 인식하도록 돕는다. */
            clear: both;
            /* 가상 요소는 테이블과 유사한 박스 모델을 따른다(?) float된 요소의 높이를 인식하는데 필요한 올바른 박스 모델을 설정하는데 도움이 된다고함.. */
            display: table;
        }

        /* 최대 넓이가 300px이하인 화면에 버튼의 넓이 100%로 지정 */
        @media screen and (max-width: 300px) {

            .cancelbtn,
            .signupbtn {
                width: 100%;
            }
        }
    </style>
</head>

<body>
<jsp:include page="include/nav.jsp" />
<h2>Modal Signup Form</h2>

<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Sign Up</button>

<div id="id01" class="modal">
        <span onclick="document.getElementById('id01').style.display='none'" class="close"
              title="Close Modal">&times;</span>
    <form class="modal-content" action="" onsubmit="return formCheck(this);">
        <div class="container">
            <h1>Sign Up</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>

            <label for="id"><b>ID</b></label>
            <input type="text" placeholder="Enter ID" name="id" id="id" required>

            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" id="password" required>

            <label for="password-repeat"><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" name="password-repeat" id="password-repeat" required>

            <label for="email"><b>Email</b></label>
            <input type="text" placeholder="Enter Email" name="email" id="email" required>
            <label>
                <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
            </label>

            <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

            <div class="clearfix">
                <button type="button" onclick="document.getElementById('id01').style.display='none'"
                        class="cancelbtn">Cancel</button>
                <button type="submit" class="signupbtn">Sign Up</button>
            </div>
        </div>
    </form>
</div>

<script>
    // Get the modal
    var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }

    function formCheck(form) {
        let regex = new RegExp("([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\"\(\[\]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\[[\t -Z^-~]*])");

        if (!regex.test(form.email.value)) {
            alert("이메일을 올바르게 입력해주세요");
            form.email.focus();
            return false;
        }

        return true;
    }
</script>
</body>
</html>
