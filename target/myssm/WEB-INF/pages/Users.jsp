<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UsersInfo</title>
</head>
<body>

<center>
    <h3>所有用户信息</h3>
    <table border="2">
        <thead>
        <tr>
            <th></th>
            <th>ID</th>
            <th>客戶名</th>
            <th>密码</th>
            <th>手机号</th>
            <th>邮箱</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userlist}" var="user" varStatus="status">
            <tr target="sid_user" rel="${user.id}">
                <form id="form${user.id}" name="form${user.id}" method="post" action="">
                        <%-- 					<input type="hidden" name="id" >${user.id}</td> --%>
                    <td><input type="button" value="删除" onClick="deletebyId(${user.id})"></td>
                    <td align="center" width="50" height="30"><input type="hidden" name="id"
                                                                     value="${user.id}"/>${user.id}</td>
                    <td align="center" width="200" height="30"><input type="hidden" name="username"/>${user.username}
                    </td>
                    <td align="center" width="200" height="30"><input type="hidden" name="PASSWORD"/>${user.PASSWORD}
                    </td>
                    <td align="center" width="200" height="30"><input type="hidden" name="PASSWORD"/>${user.phoneNum}
                    </td>
                    <td align="center" width="200" height="30"><input type="hidden" name="PASSWORD"/>${user.email}</td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</center>
</body>
<Script Language="JavaScript">
    function deletebyId(id) {
        var form = "form" + id;
        document.getElementById(form).action = "./delete";
        document.getElementById(form).submit();
    }

</Script>
</html>
