<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>        
        <title>Translator</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>

        <style>
            input:not([type=checkbox]){
                /*border: 2px solid #dadada;*/
                height: 35px;
                width: 100%;
                /*border-radius: 7px;*/
            }
            input:focus{
                outline: none;
                border-color: #9ecaed;
                box-shadow: 0 0 10px #9ecaed;
            }
            select:focus{
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
            #translate{
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
            input[type=text]{
                height: 44px;
                font-size: 16px;
                width: 100%;
                margin-bottom: 10px;
                background: #fff;
                border: 1px solid #d9d9d9;
                border-top: 1px solid #c0c0c0;
                padding: 0 8px;
                box-sizing: border-box;
                height: 35px;
            }
            select{
                border: 2px solid #dadada;
                height: 35px;
                width: 100%;
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

            input[type=checkbox]{
                transform: scale(1.3);
            }
            #yandex{
                font-style: oblique;
                font-family: monospace;
                font-size: 11px;
                font-weight: bold;
                color: blueviolet;
            }
            table{
                border-collapse: separate;
                border-spacing: 5px;
            }
            .progress{
                height: 5px;
            }
        </style>        
    </head>

    <body>
        <div id="header">
            <p class="header-text">Translator</p>
        </div>
        <div id="sep">
        </div>
        <div id="translate">
            <center>
                <form name="translate_form" method="post" action="translator">
                    <h1 id="title">Translate text</h1>

                    <div class="progress">
                        <div class="progress-bar" role="progressbar" aria-valuenow="70"
                             aria-valuemin="0" aria-valuemax="100" style="width:100%">
                            <span class="sr-only">70% Complete</span>
                        </div>
                    </div>

                    <table>
                        <tr>
                            <td>
                                <%
                                    if (request.getAttribute("fromtext") == null) {
                                        out.println("<input type=\"text\" class=\"form-control\" name=\"fromtext\" placeholder=\"Enter text to translate\" required=\"true\"/>");
                                    } else {
                                        out.println("<input type=\"text\" class=\"form-control\" name=\"fromtext\" placeholder=\"Enter text to translate\" value=" + request.getAttribute("fromtext") + ">");
                                    }
                                %>                           
                            </td>
                            <td>
                                <select name="fromlang" class="form-control" data-toggle="tooltip" title="Select language of your text">
                                    <%
                                        ArrayList<String> ar2 = new ArrayList<String>();
                                        ar2 = (ArrayList<String>) request.getAttribute("langs");
                                        for (int i = 0; i < ar2.size(); i++) {
                                            if (ar2.get(i).equals(request.getAttribute("fromlang"))) {
                                                out.println("<option selected>" + ar2.get(i) + "</option>");
                                            } else {
                                                out.println("<option>" + ar2.get(i) + "</option>");
                                            }
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td><label class="checkbox-inline"><input type="checkbox" name="autodetect" checked value="1"/>Auto detect language</label>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <%
                                    if (request.getAttribute("final_result") == null) {
                                        out.println("<input type=\"text\" class=\"form-control\" name=\"totext\" placeholder=\"Translated text\" disabled/>");
                                    } else {
                                        response.setCharacterEncoding("UTF-8");
                                        out.println("<input type=\"text\" class=\"form-control\" name=\"totext\" placeholder=\"Translated text\" disabled value=" + request.getAttribute("final_result") + ">");
                                    }
                                %>                            
                            </td>
                            <td>
                                <select name="tolang" class="form-control" data-toggle="tooltip" title="Select language to translate">
                                    <%
                                        ArrayList<String> ar = new ArrayList<String>();
                                        ar = (ArrayList<String>) request.getAttribute("langs");
                                        for (int i = 0; i < ar.size(); i++) {
                                            if (ar.get(i).equals(request.getAttribute("tolang"))) {
                                                out.println("<option selected>" + ar.get(i) + "</option>");
                                            } else {
                                                out.println("<option>" + ar.get(i) + "</option>");
                                            }
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>                        
                    </table>  
                    <table>
                        <tr>
                        <input type="submit" class="btn btn-default" id="buttons" value="Translate"/>
                        </tr>
                    </table>
                </form>
            </center>

            <center>  
                <p id="yandex"><a href="http://translate.yandex.com/" target="_blank">Powered by Yandex.Translate</a></p>
            </center>

        </div>

    </body>

</html>