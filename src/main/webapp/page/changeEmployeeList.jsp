<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 23.01.2018
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/page/header.jspf"%>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.label.administrator" bundle="${lang}"/></title>
    <link rel="stylesheet" href="page/loginCSS.css" media="screen" type="text/css" />
    <link rel="stylesheet" href="page/flightsCSS.css" type="text/css"  />
    <link href="http://fonts.googleapis.com/css?family=Oswald:400,300" rel="stylesheet" type="text/css" />
</head>
<body>
<my:my/>
<div id="wrapper">
    <div id="menu-wrapper">
        <div id="menu" class="container">
            <ul>
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
                <th><fmt:message key="employee.label.permission" bundle="${lang}"/>n</th>
                <th><fmt:message key="employee.label.role" bundle="${lang}"/></th>
            </tr><!-- Table Header -->

            <c:forEach var="item" items="${employeeList}">
                <form action="Controller?command=changeThisEmployee" method="post">
                    <tr>
                        <td style="display: none"><input type="hidden" name="id" value="<c:out value="${item.id}"/>"><c:out value="${item.id}"/></td>
                        <td><input type="hidden" name="name" value="${item.firstName}"><c:out value="${item.firstName}"/></td>
                        <td><input type="hidden" name="surName" value="${item.lastName}"><c:out value="${item.lastName}"/></td>
                        <td><input type="hidden" name="age" value="${item.age}"><c:out value="${item.age}"/></td>
                        <td><input type="hidden" name="permission" value="${item.isPermittedView}"><c:out value="${item.isPermittedView}"/></td>
                        <td><input type="hidden" name="role" value="${item.role}"><c:out value="${item.role}"/></td>
                        <td><input type="submit" value="<fmt:message key="flight.label.changeFlight" bundle="${lang}"/>"></td>
                    </tr><!-- Table Row -->
                </form>
            </c:forEach>


        </table>
    </div>
</div>
<!-- end #footer -->
</body>
</html>
