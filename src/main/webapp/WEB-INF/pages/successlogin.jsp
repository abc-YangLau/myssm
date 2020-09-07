<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>贷款利率计算</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/vector.js"></script>

</head>
<body>

<div id="container">
    <div id="output">
        <div class="containerT">
            <form name="form2" method="post" action="" onSubmit="return isValid();">
                <h1 style="color:white">贷款金额（万元）</h1>
                <input type="text" id="invest" name="invest">
                <h1 style="color:white">贷款期数（年）</h1>
                <input type="text" id="month" name="month">
                <h1 style="color:white">贷款年利率</h1>
                <input type="text" id="yearRate" name="yearRate">
                <button style="width:90px;height:35px;color:balck" type="button" onClick="averageCapital()"><h4>
                    等额本金</h4></button>
                <button style="width:90px;height:35px;color:balck" type="button" onClick="averageCapitalPlusInterest()">
                    <h4>等额本息</h4></button>
                <!-- 						<input value="重置" style="color:green" type="reset" > -->
            </form>
            <input type="button" value="等额本金导出" onclick="exportExcelOfAverageCapital()"/>
            <input type="button" value="等额本息导出" onclick="exportExcelOfAverageCapitalPlusInterest()"/>
        </div>
    </div>

</div>

</body>

<script type="text/javascript">

    function exportExcelOfAverageCapitalPlusInterest() {
        if (isValid()) {
            document.form2.action = "./downloadAverageCapitalPlusInterest";
            document.form2.submit();
        }
    }

    function exportExcelOfAverageCapital() {
        if (isValid()) {
            document.form2.action = "./downloadAverageCapital";
            document.form2.submit();
        }
    }

    function isValid() {
        if (form2.invest.value == "") {
            alert("贷款金额不能为空！");
            document.form2.invest.focus();
            return false;
        }
        if (form2.month.value == "") {
            alert("贷款期数不能为空！");
            document.form2.month.focus();
            return false;
        }
        if (form2.yearRate.value == "") {
            alert("贷款年利率不能为空！");
            document.form2.yearRate.focus();
            return false;
        }
        return true;
    }

    function averageCapital() {
        if (isValid()) {
            document.form2.action = "./averageCapital";
            document.form2.submit();
        }
    }

    function averageCapitalPlusInterest() {
        if (isValid()) {
            document.form2.action = "./averageCapitalPlusInterest";
            document.form2.submit();
        }
    }

    $(function () {
        Victor("container", "output");   //登录背景函数
        $("#entry_name").focus();
        $(document).keydown(function (event) {
            if (event.keyCode == 13) {
                $("#entry_btn").click();
            }
        });
    });

</Script>
</html>
