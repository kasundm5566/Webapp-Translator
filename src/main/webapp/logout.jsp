<meta http-equiv="refresh" content="3; url=index.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login Page</title>
    <link rel='shortcut icon' type='image/x-icon' href='images/bi.png'/>
    <link rel="stylesheet" href="css/login.css"/>
</head>
<body>
<fmt:bundle basename="jstlmessages_en">
    <div id="logoutFrame">
        <center>
            <img height="200x" width="200px" src="images/logout-512.png"/>
        </center>
        <p><fmt:message key="logout.message"></fmt:message></p>
        <%
            Thread.sleep(1000);
        %>
    </div>
</fmt:bundle>
</body>
</html>