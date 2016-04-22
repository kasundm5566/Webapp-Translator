<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error.jsp" %>

<html>
<head>
    <title>Login Page</title>
    <link rel='shortcut icon' type='image/x-icon' href='images/bi.png'/>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/login.css">
<body>
<%@include file="header.jsp" %>
<div id="login">
    <form name="Login" method="post" action="login">
        <h1 id="title">Enter details to login</h1>

        <div class="progress">
            <div class="progress-bar" role="progressbar" aria-valuenow="70"
                 aria-valuemin="0" aria-valuemax="100" style="width:100%">
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
                <input type="password" class="form-control" name="pass" placeholder="Enter password" required="true"/>
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