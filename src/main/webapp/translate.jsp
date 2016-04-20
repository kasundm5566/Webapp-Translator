<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error.jsp" %>
<%@ page import="java.util.Vector" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Translator</title>
    <link rel='shortcut icon' type='image/x-icon' href='images/bi.png'/>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/translate.css">
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="js/operations.js"></script>
    <script type="text/javascript" src="js/validate.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<body>

<div id="header">
    <p class="header-text">Translator</p>
</div>

<%
    session.setAttribute("user", session.getAttribute("username"));
    out.println("<div id=\"session\" class=\"alert alert-info\" role=\"alert\">");
    out.println("Logged in as <strong><u>" + session.getAttribute("username") + "</u></strong>");
    out.println("<br><form action=\"logout\" method=\"post\"><table><tr><button type=\"submit\" class=\"btn btn-info btn-xs\" id=\"logout\"><span class=\"glyphicon glyphicon-off\"></span>&nbsp;&nbsp;&nbsp;Logout</button></tr></table></form></div>");
%>

<div id="sep">
</div>

<div id="translate">
    <ul class="nav nav-pills">
        <li class="active"><a data-toggle="tab" href="#home">Translate Text</a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown">User Management
                <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a data-toggle="tab" href="#addUser">Add user</a></li>
                <li><a data-toggle="tab" href="#searchUser">Search user</a></li>
            </ul>
    </ul>

    <div class="tab-content">
        <div id="home" class="tab-pane fade in active">
            <h1 id="title">Translate text</h1>

            <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuenow="70"
                     aria-valuemin="0" aria-valuemax="100" style="width:100%">
                    <span class="sr-only"></span>
                </div>
            </div>
            <center>
                <form name="translate_form" method="post" action="translator">

                    <div style="float: left;">
                        <%
                            if (request.getSession().getAttribute("fromtext") == null) {
                                out.println("<textarea name=fromtext id=\"fromtext\" class=\"form-control\" rows=\"6\" cols=\"50\" placeholder=\"Enter text to translate\" required=\"true\"></textarea>");
                            } else {
                                out.println("<textarea name=fromtext id=\"fromtext\" class=\"form-control\" rows=\"6\" cols=\"50\">" + request.getSession().getAttribute("fromtext") + "</textarea>");
                            }
                        %>
                    </div>
                    <div style="float: right;">
                        <select name="fromlang" class="form-control" data-toggle="tooltip"
                                title="Select language of your text">
                            <%
                                Vector<String> ar2 = new Vector<String>();
                                ar2 = (Vector<String>) request.getSession().getAttribute("langs");
                                for (int i = 0; i < ar2.size(); i++) {
                                    if (ar2.get(i).equals(request.getSession().getAttribute("fromlang"))) {
                                        out.println("<option selected>" + ar2.get(i) + "</option>");
                                    } else {
                                        out.println("<option>" + ar2.get(i) + "</option>");
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div>
                        <label class="checkbox-inline"><input type="checkbox" name="autodetect" checked value="1"/>Auto
                            detect
                            language</label>
                        <button type="button" id="btnswap" class="btn btn-default btn-xs" onclick="swap();">
                            <span class="glyphicon glyphicon-resize-small"></span>Swap
                        </button>
                    </div>
                    <div id="sep3">
                    </div>
                    <div style="float: left;">
                        <%
                            if (request.getSession().getAttribute("final_result") == null) {
                                out.println("<textarea name=totext id=\"totext\" class=\"form-control\" rows=\"6\" cols=\"50\" placeholder=\"Translated text\" disabled></textarea>");
                            } else {
                                response.setCharacterEncoding("UTF-8");
                                out.println("<textarea name=totext id=\"totext\" class=\"form-control\" rows=\"6\" cols=\"50\" disabled>" + request.getSession().getAttribute("final_result") + "</textarea>");
                            }
                        %>
                    </div>
                    <div id="sep2">
                        <div style="float: right;">
                            <select name="tolang" class="form-control" data-toggle="tooltip"
                                    title="Select language to translate">
                                <%
                                    Vector<String> ar = new Vector<String>();
                                    ar = (Vector<String>) request.getSession().getAttribute("langs");
                                    for (int i = 0; i < ar.size(); i++) {
                                        if (ar.get(i).equals(request.getSession().getAttribute("tolang"))) {
                                            out.println("<option selected>" + ar.get(i) + "</option>");
                                        } else {
                                            out.println("<option>" + ar.get(i) + "</option>");
                                        }
                                    }
                                %>
                            </select>
                        </div>
                        <div>
                            <button type="submit" class="btn btn-default" id="buttons">
                                <span class="glyphicon glyphicon-play-circle"></span>&nbsp;&nbsp;&nbsp;Translate
                            </button>
                        </div>
                    </div>
                </form>
                <div style="margin-top: 0.5em;">
                    <p id="yandex"><a href="http://translate.yandex.com/" target="_blank">Powered by
                        Yandex.Translate</a></p>
                </div>

            </center>
        </div>

        <div id="addUser" class="tab-pane fade">
            <h1 id="title">Enter details to add a user</h1>

            <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuenow="70"
                     aria-valuemin="0" aria-valuemax="100" style="width:100%">
                    <span class="sr-only">70% Complete</span>
                </div>
            </div>
            <form name="Register" method="post" action="register" onsubmit="return validateForm();">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                                    User Details</a>
                            </h4>
                        </div>
                        <div id="collapse1" class="panel-collapse collapse in">
                            <label id="fname_error"
                                   style="color: red; font-weight: lighter; font-size: smaller;"></label>
                            <div class="input-group">
                                <span class="input-group-addon">First name</span>
                                <input type="text" id="fname" class="form-control" name="fname"
                                       placeholder="Enter first name" required="true"/>
                            </div>

                            <label id="lname_error"
                                   style="color: red; font-weight: lighter; font-size: smaller;"></label>

                            <div class="input-group">
                                <span class="input-group-addon">Last name</span>
                                <input type="text" id="lname" class="form-control inp" name="lname"
                                       placeholder="Enter your last ame"/>
                            </div>
                            <div><label></label></div>
                            <div style="height: 40px;" class="input-group">
                                <span class="input-group-addon">Country&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <select id="countrySelect" class="form-control">
                                    <option selected>Sri Lanka</option>
                                    <option>Australia</option>
                                    <option>China</option>
                                    <option>Japan</option>
                                    <option>USA</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                                    Account Details</a>
                            </h4>
                        </div>
                        <div id="collapse2" class="panel-collapse collapse">

                            <label id="uname_error"
                                   style="color: red; font-weight: lighter; font-size: smaller;"></label>

                            <div class="input-group">
                                <span class="input-group-addon">User name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <input type="text" id="username" class="form-control" name="username"
                                       placeholder="Enter user name" required="true"/>
                            </div>

                            <label id="pass_error"
                                   style="color: red; font-weight: lighter; font-size: smaller;"></label>

                            <div class="input-group">
                                <span class="input-group-addon">Password&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <input type="password" id="pass" class="form-control" name="pass"
                                       placeholder="Enter password" required="true"/>
                            </div>

                            <label id="repass_error"
                                   style="color: red; font-weight: lighter; font-size: smaller;"></label>

                            <div class="input-group">
                                <span class="input-group-addon">Retype password</span>
                                <input type="password" id="repass" class="form-control" name="repass"
                                       placeholder="Retype your password" required="true"/>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
                                    Contact Details</a>
                            </h4>
                        </div>
                        <div id="collapse3" class="panel-collapse collapse">

                            <label id="email_error"
                                   style="color: red; font-weight: lighter; font-size: smaller;"></label>

                            <div class="input-group">
                                <span class="input-group-addon">Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <input type="text" id="email" class="form-control" name="email"
                                       placeholder="Enter your email" required="true"/>
                            </div>

                            <label id="tel_error"
                                   style="color: red; font-weight: lighter; font-size: smaller;"></label>

                            <div class="input-group">
                                <span class="input-group-addon">Contact no</span>
                                <input type="tel" id="tel" class="form-control" name="tel"
                                       placeholder="Enter your contact no" required="true"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="submit" class="btn btn-default" id="buttons">
                        <span class="glyphicon glyphicon-user"></span>&nbsp;Register
                    </button>
                </div>

            </form>
        </div>
        <div id="searchUser" class="tab-pane fade">
            <h3>Menu 2</h3>

            <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                consequat.</p>
        </div>
    </div>
</div>


</body>
</html>