<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 25.01.2018
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/page/header.jspf"%>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.label.registration" bundle="${lang}"/></title>
    <link rel="stylesheet" href="page/registrationCSS.css" media="screen" type="text/css" />
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
                    <%@ include file="/page/JSPF/registration.jspf"%>
                </form>
            </ul>
        </div>
    </div>

</div>
<div id="login">
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="registration">
        <fieldset class="clearfix">
            <p><span class="fontawesome-mail"></span><input type="email" name="email" value="user@gmail.com" onBlur="if(this.value == '') this.value = 'user@gmail.com'" onFocus="if(this.value == 'user@gmail.com') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><span class="fontawesome-user"></span><input type="text" name="login" value="Username" onBlur="if(this.value == '') this.value = 'Username'" onFocus="if(this.value == 'Username') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><span class="fontawesome-lock"></span><input type="password" name="password"  value="Password" onBlur="if(this.value == '') this.value = 'Password'" onFocus="if(this.value == 'Password') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Password" -->
            <img id="captcha" src="<c:url value="simpleCaptcha.jpg" />" width="150"/> <input type="text" name="captcha" placeholder="Enter captcha">
            <p><input type="submit" name="command" value="<fmt:message key="label.view.register" bundle="${lang}"/>"></p>
        </fieldset>
    </form>
    <p style="color: red"><c:out value="${errors}"/></p>
    <p><fmt:message key="registration.label.haveAnAccount" bundle="${lang}"/> &nbsp;&nbsp;<a href="login"><fmt:message key="page.label.login" bundle="${lang}"/></a><span class="fontawesome-arrow-right"></span></p>
</div>
</body>
</html>
