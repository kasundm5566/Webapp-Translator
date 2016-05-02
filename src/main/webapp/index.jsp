<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error.jsp" %>

<html>
<head>
    <title>Login Page</title>
    <link rel='shortcut icon' type='image/x-icon' href='images/bi.png'/>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/translate.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<body>
<%@include file="header.jsp" %>
<div id="login">
    <form name="Login" method="post" action="login">
        <h1 id="title">Enter details to login</h1>

        <div class="progress" style="height: 5px;">
            <div class="progress-bar" role="progressbar" aria-valuenow="70"
                 aria-valuemin="0" aria-valuemax="100" style="width:100%;">
                <span class="sr-only">70% Complete</span>
            </div>
        </div>

        <div>
            <div class="input-group">
                <span class="input-group-addon">User name</span>
                <input type="text" class="form-control" name="uname" placeholder="Enter user name" required="true"/>
            </div>
            <div class="input-group" style="margin-top: 10px;">
                <span class="input-group-addon">Password&nbsp;</span>
                <input type="password" class="form-control" name="loginpass" placeholder="Enter password" required="true"/>
            </div>
            <div style="margin-top: 10px;">
                <button type="submit" class="btn btn-default" id="buttons">
                    <span class="glyphicon glyphicon-send"></span>&nbsp;Login
                </button>
            </div>
        </div>
    </form>

    <%
        if (request.getAttribute("error_msg") != null) {
            out.println("<div id=\"error\" class=\"alert alert-danger\">");
            out.println(request.getAttribute("error_msg"));
            out.println("</div>");
        }
    %>
</div>
</body>
</html>