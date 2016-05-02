<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error.jsp" %>
<%@ page import="java.util.Vector" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Translator</title>
    <link rel='shortcut icon' type='image/x-icon' href='images/bi.png'/>
    <link rel="stylesheet" href="css/translate.css">
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="js/operations.js"></script>
    <script type="text/javascript" src="js/validate.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-3-typeahead/4.0.1/bootstrap3-typeahead.min.js"></script>

</head>

<body>
<%@include file="header.jsp" %>
<div id="sep">
</div>
<%--<%
    session.setAttribute("user", session.getAttribute("username"));
    out.println("<div id=\"session\" class=\"alert alert-info\" role=\"alert\">");
    out.println("Logged in as <strong><u>" + session.getAttribute("username") + "</u></strong>");
    out.println("<br><form action=\"logout\" method=\"post\"><table><tr><button type=\"submit\" class=\"btn btn-info btn-xs\" id=\"logout\"><span class=\"glyphicon glyphicon-off\"></span>&nbsp;&nbsp;&nbsp;Logout</button></tr></table></form></div>");
%>--%>
<% session.setAttribute("user", session.getAttribute("username")); %>
<div class="container" style="position: fixed; right: 0; padding: 20px; display: block;">
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user"></span>
                    <% out.println("Logged in as <strong><u>" + session.getAttribute("username") + "</u></strong>");%>
                    <span class="glyphicon glyphicon-chevron-down"></span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <div class="navbar-login">
                            <div class="row">
                                <div class="col-lg-4">
                                    <p class="text-center">
                                        <span class="glyphicon glyphicon-user icon-size"></span>
                                    </p>
                                </div>
                                <div class="col-lg-8">
                                    <p class="text-left"><% out.println("<strong><u>" + session.getAttribute("username") + "</u></strong>");%></p>
                                    <p class="text-left">
                                        <a href="#" class="btn btn-primary btn-block btn-sm">Profile</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="divider navbar-login-session-bg"></li>
                    <li><a href="#">Account Settings <span class="glyphicon glyphicon-cog pull-right"></span></a></li>
                    <li class="divider"></li>
                    <li><a href="#">User stats <span class="glyphicon glyphicon-stats pull-right"></span></a></li>
                    <li class="divider"></li>
                    <li class="divider"></li>
                    <li><a href="/logout">Sign Out <span class="glyphicon glyphicon-log-out pull-right"></span></a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>


<div id="sep">
</div>
<center>
    <div id="translate">
        <ul class="nav nav-pills">
            <li class="active"><a data-toggle="tab" href="#home"><span class="glyphicon glyphicon-list-alt"></span>
                Translate Text</a></li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> User
                    Management
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a data-toggle="tab" href="#addUser">Add user</a></li>
                    <li><a data-toggle="tab" href="#searchUser">Search user</a></li>
                </ul>
        </ul>

        <div class="tab-content">
            <div id="home" class="tab-pane fade in active">
                <%@include file="translate.jsp" %>
            </div>
            <div id="addUser" class="tab-pane fade">
                <%@include file="register.jsp" %>
            </div>
            <div id="searchUser" class="tab-pane fade">
                <%@include file="search.jsp" %>
            </div>
        </div>
    </div>
</center>
<script type="text/javascript" src="js/datepicker.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/validateUpdate.js"></script>
<script type="text/javascript" src="js/search.js"></script>
</body>
</html>