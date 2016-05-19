<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login Page</title>
    <link rel='shortcut icon' type='image/x-icon' href='images/bi.png'/>
    <link rel="stylesheet" href="css/login.css"/>
    <link rel="stylesheet" href="css/translate.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="js/pace.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/3.3.6/yeti/bootstrap.min.css"/>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/pace-theme-center-simple.css"/>
</head>
<body>
<fmt:bundle basename="jstlmessages_en">
    <%@include file="header.jsp" %>
    <div id="login">
        <form name="Login" method="post" action="login">
            <h1 id="title"><fmt:message key="login.title"/></h1>

            <div class="progress" style="height: 5px;">
                <div class="progress-bar" role="progressbar" aria-valuenow="70"
                     aria-valuemin="0" aria-valuemax="100" style="width:100%;">
                    <span class="sr-only">70% Complete</span>
                </div>
            </div>
            <center>
                <div><img src="images/user_icon.png" height="150" width="150"
                          style="margin-bottom: 20px; margin-top: -20px;"></div>
            </center>
            <div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input id="loginusername" type="text" class="form-control " name="uname" placeholder="Enter user name"
                           required="true"/>
                </div>
                <div class="input-group" style="margin-top: 10px;">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input type="password" class="form-control" name="loginpass" placeholder="Enter password"
                           required="true"/>
                </div>
                <div style="margin-top: 10px;">
                    <button type="submit" class="btn btn-default" id="loginButton">
                        <span class="glyphicon glyphicon-send"></span>&nbsp;<fmt:message key="login.submit.text"/>
                    </button>
                </div>
            </div>
        </form>

        <%
            request.setAttribute("error", request.getAttribute("error_msg"));
        %>
        <c:if test="${not empty error}">
            <div id="error" class="alert alert-danger">${error}</div>
        </c:if>
    </div>
</fmt:bundle>

<%--<script src="js/permission.js"></script>--%>
</body>
</html>