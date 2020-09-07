<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>用户登录失败</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/vector.js"></script>
</head>
<body>
<div id="container">
    <div id="output">
        <div class="containerT">
            <div class="title"><span>登录失败，用户名或密码输入有误！3秒后返回登录……</span></div>
            <a href="../index.jsp">返回登录</a>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    $(function () {
        Victor("container", "output");   //登录背景函数
        delayURL("../index.jsp", 3000);
    });

    function delayURL(url, time) {
        setTimeout("location.href='" + url + "'", time);
    }
</script>
</html>


