<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>房贷登录系统</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/vector.js"></script>

</head>
<body>
<div id="container">
    <div id="output">
        <div class="containerT">
            <h1 style="color:white">用户登录</h1>
            <form name="form1" id="entry_form" method="post" action="">
                <input type="text" placeholder="用户名" id="entry_name" name="username">
                <input type="password" placeholder="密码" id="entry_password" name="password">
                <button style="width:66px;height:40px;" type="button" id="entry_btn" onClick="login()">登录</button>
                <button style="width:66px;height:40px;" type="button" id="register_btn" onClick="register()">注册</button>
                <button style="width:66px;height:40px;" type="reset">重置</button>
                <div id="prompt" class="prompt"></div>
            </form>
            <a href="users/findUsers">所有用户信息查询</a><br><br>
            <a href="https://voice.baidu.com/act/newpneumonia/newpneumonia/?from=osari_pc_3">全国疫情最新情况</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        Victor("container", "output");   //登录背景函数
        $("#entry_name").focus();
        $(document).keydown(function (event) {
            if (event.keyCode == 13) {
                $("#entry_btn").click();
            }
        });
    });
</script>
<div style="text-align:center;">

</div>

</body>
<Script>
    function login() {
        if (isValid()) {
            document.form1.action = "users/login";
            document.form1.submit();
        }
    }

    function register() {
        document.form1.action = "users/register";
        document.form1.submit();
    }


    function isValid() {
        if (form1.username.value == "") {
            alert("用户名不能为空！");
            document.form1.username.focus();
            return false;
        }
        if (form1.password.value == "") {
            alert("密码不能为空！");
            document.form1.password.focus();
            return false;
        }

        return true;
    }
</Script>

</html>
