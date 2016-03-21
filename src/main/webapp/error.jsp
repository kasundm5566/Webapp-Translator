<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Error Occurred</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script src='webjars/jquery/1.11.1/jquery.js'></script>
        <script src='webjars/bootstrap/3.2.0/js/bootstrap.min.js'></script>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
        <style>
            input{
                height: 35px;
                width: 100%;
            }
            input:focus{
                outline: none;
                border-color: #9ecaed;
                box-shadow: 0 0 10px #9ecaed;
            }
            body{
                background-size: cover;
                font-family: 'Roboto', sans-serif;
                background-color: #111;
                background-image: url(bg2.png);
                background-size: cover;
            }

            button{
                border: 0px;
                color: #fff;
                text-shadow: 0 1px rgba(0,0,0,0.1);
                background-color: #4d90fe;
                font-size: 20px;
            }

            h1{
                color: #111;
            }

            #error{
                padding: 10px;
                width: 800px;
                height: 360px;
                background-color: #F7F7F7;
                margin: 100px auto;
                border-radius: 2px;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                overflow: hidden;
            }
            #sep{
                height: 10px;
            }
            li{
                margin: 10px 0;
            }
            .progress{
                height: 5px;
            }
            #explanation, #para{
                color: #111;
            }
        </style>        
    </head>
    <body>

        <div id="error">            
            <h1><%=exception.getMessage()%></h1>
            
            <div class="progress">
                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="70"
                         aria-valuemin="0" aria-valuemax="100" style="width:100%">
                        <span class="sr-only">70% Complete</span>
                    </div>
                </div>
            <p id="para">Please verify following before you try again.</p>

            <div class="container">
                <button type="button" class="btn btn-danger input-sm" data-toggle="collapse" data-target="#explanation">Click here to see possible causes</button>
                <div id="explanation" class="collapse">
                    <ul>
                        <li>If you have used any spaces, please remove them and try again.</li>
                        <li>Remove if there are any illegal characters.(%, ^, &AMP;, {}, etc)</li>
                        <li>If none of them worked. Please try again later. Sorry for the inconvenience.</li>
                    </ul>
                </div>
            </div>

            <div id="sep"></div>
            <button onclick="goBack()" class="btn btn-link">Go back and try again</button>
        </div>

    </body>
</html>
