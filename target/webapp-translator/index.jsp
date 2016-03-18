<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<html>
    <head>
        <title>Login Page</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <style>
            input{
                border: 2px solid #dadada;
                height: 35px;
                width: 100%;
                /*border-radius: 7px;*/
            }
            input:focus{
                outline: none;
                border-color: #9ecaed;
                box-shadow: 0 0 10px #9ecaed;
            }
            #title{
                font-weight: 100;
                text-align: center;
                font-size: 1.3em;
                font-family: "Lucida Console", Monaco, monospace;
            }
            body{
                background-size: cover;
                font-family: 'Roboto', sans-serif;
                background-color: darkgrey;
                background-image: url(shopping-cart-online.jpg);
                background-size: cover;
            }
            #login{
                padding: 25px;
                width: 374px;
                background-color: #F7F7F7;
                margin: 0 auto 10px;
                border-radius: 2px;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                overflow: hidden;
            }
            #header{
                height: 60px;
                background-color: #111;
                opacity: 0.5;
                font-family: "Lucida Console", Monaco, monospace;
            }
            div#sep{
                height: 25px;
            }
            .header-text{
                text-align: center;
                font-size: 32px;
                padding-top: 10px;
                color: #fff;
            }
            #buttons{
                border: 0px;
                color: #fff;
                text-shadow: 0 1px rgba(0,0,0,0.1);
                background-color: #4d90fe;
                font-size: 20px;
            }
            input[type=text], input[type=password]{
                height: 44px;
                font-size: 16px;
                width: 100%;
                margin-bottom: 10px;
                background: #fff;
                border: 1px solid #d9d9d9;
                border-top: 1px solid #c0c0c0;
                padding: 0 8px;
                box-sizing: border-box;
            }
            .progress{
                height: 5px;
            }
        </style>        
    <body>
        <div id="header">
            <p class="header-text">Welcome to Translator</p>
        </div>
        <div id="sep">
        </div>

        <div id="login">
            <form name="Login" method="post" action="login">
                <h1 id="title"><u>Enter details to login</u></h1>
                
                <div class="progress">
                    <div class="progress-bar" role="progressbar" aria-valuenow="70"
                         aria-valuemin="0" aria-valuemax="100" style="width:100%">
                        <span class="sr-only">70% Complete</span>
                    </div>
                </div>

                <table>
                    <input type="text" class="form-control" name="uname" placeholder="Enter user name" required="true"/>
                    <input type="password" class="form-control" name="pass" placeholder="Enter password" required="true"/>
                    <input type="submit" class="btn btn-default" value="Login" id="buttons"/>
                </table>
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