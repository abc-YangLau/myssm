<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AverageCaptialInfo</title>
</head>
<body>
<center>
    <h3>等额本金还款详情</h3>
    <table border="2">
        <thead>
        <tr>
            <th>期数</th>
            <th>每月本息</th>
            <th>每月本金</th>
            <th>每月利息</th>
        </tr>
        </thead>
        <c:forEach items="${averageCapitalList}" var="AverageCaptial" varStatus="status">
        <tr target="sid_user" rel="${AverageCaptial.numMonth}">
            <td align="center" width="200" height="30">${AverageCaptial.numMonth}</td>
            <td align="center" width="200" height="30">${AverageCaptial.monthRate}</td>
            <td align="center" width="200" height="30">${AverageCaptial.benjin}</td>
            <td align="center" width="200" height="30">${AverageCaptial.interest}</td>
        </tr>
        </c:forEach>
</center>
</table>
<div class="paging">
    <a class="button" href="">上一页</a> <a href="#">1</a> <a href="#">2</a>
    <a href="#">3</a> <a href="#">4</a> <a href="#">5</a>
    <a class="button" href="">下一页</a>
</div>
</body>
</html>
