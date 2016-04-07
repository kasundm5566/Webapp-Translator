<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<html>
    <head>
        <title>Login Page</title>
        <link rel='shortcut icon' type='image/x-icon' href='images/bi.png' />
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <link rel="stylesheet" href="css/login.css">    
        <script type="text/javascript" src="js/validate.js"></script>
    </head>

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
                    <label id="name_error" style="color: red; font-weight: lighter; font-size: smaller;"></label>
                </div>
                <div>
                    <input type="text" id="name" class="form-control" name="name" placeholder="Enter your name" required="true" onblur="validateName(this.value);"/>
                </div>

                <div>
                    <label>User name:</label>
                    <label id="uname_error" style="color: red; font-weight: lighter; font-size: smaller;"></label>
                </div>
                <div>
                    <input type="text" id="username" class="form-control" name="username" placeholder="Enter user name" required="true" onblur="validateUserName(this.value);"/>
                </div>

                <div>
                    <label>Email:</label>
                    <label id="email_error" style="color: red; font-weight: lighter; font-size: smaller;"></label>
                </div>
                <div>
                    <input type="email" id="email" class="form-control" name="email" placeholder="Enter your email" required="true" onblur="validateEmail(this.value);"/>
                </div>

                <div>
                    <label>Telephone:</label>
                    <label id="tel_error" style="color: red; font-weight: lighter; font-size: smaller;"></label>
                </div>
                <div>
                    <input type="tel" id="tel" class="form-control" name="tel" placeholder="Enter your contact no" required="true" onblur="validateTel(this.value);"/>
                </div>

                <div>
                    <label>Enter password:</label>
                    <label id="pass_error" style="color: red; font-weight: lighter; font-size: smaller;"></label>
                </div>
                <div>
                    <input type="password" id="pass" class="form-control" name="pass" placeholder="Enter password" required="true"/>
                </div>

                <div>
                    <label>Retype password:</label>
                    <label id="repass_error" style="color: red; font-weight: lighter; font-size: smaller;"></label>
                </div>
                <div>
                    <input type="password" id="repass" class="form-control" name="repass" placeholder="Retype your password" required="true"/>
                </div>

                <div>    
                    <button type="submit" class="btn btn-default" id="buttons">
                        <span class="glyphicon glyphicon-user"></span>&nbsp;Register
                    </button>
                </div>

            </form>           
        </div>

    </body>
</html>
