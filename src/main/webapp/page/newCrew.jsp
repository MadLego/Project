<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 27.01.2018
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/page/header.jspf"%>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.label.dispatcher" bundle="${lang}"/></title>
    <link rel="stylesheet" href="page/administrator.CSS" media="screen" type="text/css" />
    <link rel="stylesheet" href="limk.css" media="screen" type="text/css" />
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
                    <c:choose>
                        <c:when test="${userRoleName eq 'dispatcher'}">
                            <%@ include file="/page/JSPF/crew.jspf"%>
                        </c:when>
                        <c:otherwise>
                            <%@ include file="/page/JSPF/crew.jspf"%>
                        </c:otherwise>
                    </c:choose>
                </form>
            </ul>
        </div>
    </div>
    <div>
        <table class="simple-little-table" cellspacing='0'>
            <tr>
                <th><fmt:message key="flight.label.number" bundle="${lang}"/></th>
                <th><fmt:message key="crewAdmission.label.pilot" bundle="${lang}"/></th>
                <th><fmt:message key="crewAdmission.label.navigator" bundle="${lang}"/> </th>
                <th><fmt:message key="crewAdmission.label.radioOperator" bundle="${lang}"/></th>
                <th><fmt:message key="crewAdmission.label.firstStewardess" bundle="${lang}"/></th>
                <th><fmt:message key="crewAdmission.label.secondStewardess" bundle="${lang}"/></th>
                <th><fmt:message key="crewAdmission.label.apply" bundle="${lang}"/></th>
            </tr><!-- Table Header -->
                <tr>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="newCrew">
                        <input type="hidden" name="flightId" value="${flightId}">
                    <td><c:out value="${flightName}"/></td>
                    <td><select name="pilot">
                        <c:forEach var="item" items="${employees}">
                        <c:if test="${item.role eq 'pilot'}">
                        <option value="${item.id}"><c:out value="${item.firstName} ${item.lastName}"/></option>
                        </c:if>
                        </c:forEach>
                    </select></td>
                    <td><select name="navigator">
                        <c:forEach var="item" items="${employees}">
                        <c:if test="${item.role eq 'navigator'}">
                            <option value="${item.id}"><c:out value="${item.firstName} ${item.lastName}"/></option>
                        </c:if>
                        </c:forEach>
                    </select></td>
                    <td><select name="radio_operator">
                        <c:forEach var="item" items="${employees}">
                            <c:if test="${item.role eq 'radio_operator'}">
                                <option value="${item.id}"><c:out value="${item.firstName} ${item.lastName}"/></option>
                            </c:if>
                        </c:forEach>
                    </select></td>
                    <td><select name="fConductor">
                        <<c:forEach var="item" items="${employees}">
                        <c:if test="${item.role eq 'conductor'}">
                            <option value="${item.id}"><c:out value="${item.firstName} ${item.lastName}"/></option>
                        </c:if>
                    </c:forEach>
                    </select></td>
                        <td><select name="sConductor">
                            <<c:forEach var="item" items="${employees}">
                            <c:if test="${item.role eq 'conductor'}">
                                <option  value="${item.id}"><c:out value="${item.firstName} ${item.lastName}"/></option>
                            </c:if>
                        </c:forEach>
                        </select></td>
                    <td>
                        <input type="submit" name="command" value="<fmt:message key="label.view.new" bundle="${lang}"/>"/>
                    </td>
            </form>
            </tr><!-- Table Row -->
        </table>
        <p style="color: red" align="center"><c:out value="${errors}"/></p>
    </div>
</div>
</body>
</html>
