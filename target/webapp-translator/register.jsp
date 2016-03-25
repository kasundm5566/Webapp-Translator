<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<html>
    <head>
        <title>Login Page</title>
        <link rel='shortcut icon' type='image/x-icon' href='images/bi.png' />
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <link rel="stylesheet" href="css/login.css">    
    <body>
        <div id="header">
            <p class="header-text">Welcome to Translator</p>
        </div>
        <div id="sep">
        </div>

        <div id="register">
            <form name="Register" method="post" action="register">
                <h1 id="title">Enter details to Register</h1>

                <div class="progress">
                    <div class="progress-bar" role="progressbar" aria-valuenow="70"
                         aria-valuemin="0" aria-valuemax="100" style="width:100%">
                        <span class="sr-only">70% Complete</span>
                    </div>
                </div>

                <div>
                    <label>Name:</label>
                </div>
                <div>
                    <input type="text" class="form-control" name="uname" placeholder="Enter your name" required="true"/>
                </div>

                <div>
                    <label>User name:</label>
                </div>
                <div>
                    <input type="text" class="form-control" name="uname" placeholder="Enter user name" required="true"/>
                </div>

                <div>
                    <label>Email:</label>
                </div>
                <div>
                    <input type="email" class="form-control" name="uname" placeholder="Enter your email" required="true"/>
                </div>
                
                <div>
                    <label>Telephone:</label>
                </div>
                <div>
                    <input type="tel" class="form-control" name="uname" placeholder="Enter your contact no" required="true"/>
                </div>

                <div>
                    <label>Enter password:</label>
                </div>
                <div>
                    <input type="password" class="form-control" name="pass" placeholder="Enter password" required="true"/>
                </div>

                <div>
                    <label>Retype password:</label>
                </div>
                <div>
                    <input type="password" class="form-control" name="pass" placeholder="Retype your password" required="true"/>
                </div>


                <div>    
                    <button type="submit" class="btn btn-default" id="buttons">
                        <span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;&nbsp;Register
                    </button>
                </div>

            </form>

            <%
                if (request.getAttribute("error_msg") != null) {
                    out.println("<div id=\"error\" class=\"alert alert-danger\">");
                    out.println(request.getSession().getAttribute("error_msg"));
                    out.println("</div>");
                }
            %>
        </div>

    </body>
</html>
