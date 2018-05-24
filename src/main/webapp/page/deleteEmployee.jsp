<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 23.01.2018
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/page/header.jspf"%>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="FlightBundle" var="lang" />
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.label.administrator" bundle="${lang}"/></title>
    <link rel="stylesheet" href="page/administrator.CSS" media="screen" type="text/css" />
    <link href="http://fonts.googleapis.com/css?family=Oswald:400,300" rel="stylesheet" type="text/css" />

</head>
<body>
<my:my/>
<div id="wrapper">
    <div id="menu-wrapper">
        <div id="menu" class="container">
            <ul class="menu">
                <form action="Controller" name="command" method="post">
                    <input type="hidden" name="command">
                    <%@ include file="/page/JSPF/administrator(Empty).jspf"%>
                </form>
            </ul>
        </div>
    </div>
    <div>
        <table class="simple-little-table" cellspacing='0'>
            <tr>
                <th><fmt:message key="employee.label.firstName" bundle="${lang}"/></th>
                <th><fmt:message key="employee.label.lstName" bundle="${lang}"/></th>
                <th><fmt:message key="employee.label.age" bundle="${lang}"/></th>
                <th><fmt:message key="employee.label.role" bundle="${lang}"/></th>
            </tr><!-- Table Header -->

        <c:forEach var="item" items="${employeesList}">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="deleteEmployee">
            <tr>
                <td style="display: none"><input type="hidden" name="id" value="<c:out value="${item.id}"/>"><c:out value="${item.id}"/></td>
                <td><c:out value="${item.firstName}"/></td>
                <td><c:out value="${item.lastName}"/></td>
                <td><c:out value="${item.age}"/></td>
                <td><c:out value="${item.role}"/></td>
                <td><input type="submit" name="Delete" value="<fmt:message key="label.view.delete" bundle="${lang}"/>"/></td>
            </tr><!-- Table Row -->
            </form>
        </c:forEach>
        </table>
    </div>
</div>
<!-- end #footer -->
</body>
</html>
