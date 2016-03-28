<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Error Occurred</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script src='webjars/jquery/1.11.1/jquery.js'></script>
        <script src='webjars/bootstrap/3.2.0/js/bootstrap.min.js'></script>
        <link rel="stylesheet" href="css/error.css">
        <script type="text/javascript" src="js/operations.js"></script>      
    </head>

    <body>

        <div id="error">            
            <h1>Error Occurred!</h1>

            <div class="progress">
                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="70"
                     aria-valuemin="0" aria-valuemax="100" style="width:100%">
                    <span class="sr-only">70% Complete</span>
                </div>
            </div>
            <p id="para">Click here to see detailed error report.</p>

            <div class="container">
                <button type="button" class="btn btn-danger input-sm" data-toggle="collapse" data-target="#explanation">Click here to see possible causes</button>
                <div id="explanation" class="collapse">                 
                    <%
                        out.println("<div id=\"errordiv\"><textarea rows=\"10\" cols=\"100\" disabled>");
                        out.println(Arrays.toString(exception.getStackTrace()));
                        out.println("</textarea></div>");
                    %>
                </div>
            </div>

            <div id="sep"></div>
            <button class="btn btn-link" onclick="goBack()">Go back and try again</button>
        </div>


    </body>
</html>
