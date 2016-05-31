<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="error.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Translator</title>
    <link rel='shortcut icon' type='image/x-icon' href='images/bi.png'/>
    <link rel="stylesheet" href="css/translate.css"/>
    <link rel="stylesheet" href="css/login.css"/>
    <script type="text/javascript" src="js/operations.js"></script>
    <script type="text/javascript" src="js/validate.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="js/pace.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/3.3.6/yeti/bootstrap.min.css"/>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.css"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-3-typeahead/4.0.1/bootstrap3-typeahead.min.js"></script>

    <script src="js/simple-bootstrap-paginator.js"></script>
    <link rel="stylesheet" href="css/pace-theme-center-simple.css"/>
    <link rel="stylesheet" href="css/bootstrap-multiselect.css"/>
    <script src="js/bootstrap-multiselect.js"></script>
</head>

<body>

<%
    request.setAttribute("permissionList", request.getSession().getAttribute("permissions"));
    request.setAttribute("uname", session.getAttribute("username"));
    request.setAttribute("userAddPermission", "AddUser");
    request.setAttribute("translatePermission", "Translate");
    request.setAttribute("searchPermission", "SearchUser");
%>

<fmt:bundle basename="jstlmessages_en">
    <%@include file="header.jsp" %>
    <div id="sep">
    </div>

    <% session.setAttribute("user", session.getAttribute("username")); %>
    <div id="logoutDiv">
        <ul id="navi" class="nav navbar-nav">
            <input type="hidden" id="userName" value="${uname}"/>
            <li id="drp" class="dropdown">
                <a href="#" class="dropdown-toggle"
                   data-toggle="dropdown">Logged in as <strong><u><c:out value="${uname}"/></u></strong><span
                        class="glyphicon glyphicon-user pull-right"></span>
                </a>
                <ul id="drpmenu" class="dropdown-menu">
                    <li>
                        <a href="#acc">Account Settings <span class="glyphicon glyphicon-cog pull-right"></span></a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#fav">Favourites<span class="glyphicon glyphicon-heart pull-right"></span></a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#status">Status <span class="glyphicon glyphicon-stats pull-right"></span></a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a id="out" href="logout">Logout<span class="glyphicon glyphicon-log-out pull-right"></span></a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>

    <div id="sep">
    </div>
    <center>
        <div id="translate">
            <ul class="nav nav-pills">

                <c:forEach items="${permissionList}" var="permissions">
                    <c:choose>
                        <c:when test="${permissions eq translatePermission}">
                            <li class="active">
                                <a data-toggle="tab" href="#home"><span
                                        class="glyphicon glyphicon-list-alt"></span>
                                    Translate Text
                                </a>
                            </li>
                        </c:when>
                    </c:choose>
                </c:forEach>

                <li class="dropdown">
                    <c:forEach items="${permissionList}" var="permissions">
                    <c:choose>
                    <c:when test="${permissions eq userAddPermission}">
                        <c:set var="userMgt" value="1"/>
                    </c:when>
                    <c:when test="${permissions eq searchPermission}">
                        <c:set var="userMgt" value="1"/>
                    </c:when>
                    </c:choose>
                    </c:forEach>

                    <c:choose>
                    <c:when test="${userMgt == 1}">
                    <a class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
                        User
                        Management
                        <span class="caret"></span></a>
                    </c:when>
                    </c:choose>

                    <ul class="dropdown-menu">
                        <c:forEach items="${permissionList}" var="permissions">
                            <c:choose>
                                <c:when test="${permissions eq userAddPermission}">
                                    <li><a data-toggle="tab" href="#addUser"><span
                                            class="glyphicon glyphicon-plus"></span> &nbsp;Add
                                        user</a></li>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        <li><a id="tabSrch" data-toggle="tab" href="#searchUser"><span
                                class="glyphicon glyphicon-search"></span>
                            &nbsp;Search
                            user</a></li>
                    </ul>
            </ul>

            <div class="tab-content">
                <c:forEach items="${permissionList}" var="permissions">
                    <c:choose>
                        <c:when test="${permissions eq translatePermission}">
                            <div id="home" class="tab-pane fade in active">
                                <%@include file="translate.jsp" %>
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>

                <c:forEach items="${permissionList}" var="permissions">
                    <c:choose>
                        <c:when test="${permissions eq userAddPermission}">
                            <div id="addUser" class="tab-pane fade">
                                <%@include file="register.jsp" %>
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>

                <c:forEach items="${permissionList}" var="permissions">
                    <c:choose>
                        <c:when test="${permissions eq searchPermission}">
                            <div id="searchUser" class="tab-pane fade" style="width:1100px;">
                                <%@include file="search.jsp" %>
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
    </center>

    <script type="text/javascript" src="js/datepicker.js"></script>
    <script type="text/javascript" src="js/validate.js"></script>
    <script type="text/javascript" src="js/validateUpdate.js"></script>
    <script type="text/javascript" src="js/search.js"></script>
    <script type="text/javascript" src="js/permission.js"></script>
</fmt:bundle>
</body>
</html>