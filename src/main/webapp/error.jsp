<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Error Occurred</title>
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
                color: red;
            }

            #error{
                padding: 10px;
                width: 800px;
                height: 310px;
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
        </style>
        
    </head>
    <body>

        <div id="error">            
            <h1><%=exception.getMessage()%></h1>
            <hr>
            <p>Please verify following before you try again.</p>

            <ul>
                <li>Make sure the text you entered is a valid one (Currently this program supports only for single words).</li>
                <li>If you have used any spaces, please remove them and try again.</li>
                <li>Remove if there are any illegal characters.(%, ^, &AMP;, {}, etc)</li>
                <li>If none of them worked. Please try again later. Sorry for the inconvenience.</li>
            </ul>
            <div id="sep"></div>
            <button onclick="goBack()">Go back and try again</button>
        </div>

    </body>
</html>
