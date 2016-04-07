<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

<%@ page import="java.util.Vector" %>
<!DOCTYPE html>
<html lang="en">
    <head>        
        <title>Translator</title>
        <link rel='shortcut icon' type='image/x-icon' href='images/bi.png' />
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <link rel="stylesheet" href="css/translate.css">  
        <script type="text/javascript" src="js/operations.js"></script>    
    </head>

    <body>

        <div id="header">
            <p class="header-text">Translator</p>
        </div>


        <%
            out.println("<div id=\"session\" class=\"alert alert-info\" role=\"alert\">");
            out.println("Logged in as <strong><u>" + session.getAttribute("username") + "</u></strong>");
            out.println("<br><form action=\"logout\" method=\"post\"><table><tr><button type=\"submit\" class=\"btn btn-info btn-xs\" id=\"logout\"><span class=\"glyphicon glyphicon-off\"></span>&nbsp;&nbsp;&nbsp;Logout</button></tr></table></form></div>");
        %>

        <div id="sep">
        </div>

        <div id="translate">
            <center>
                <form name="translate_form" method="post" action="translator">
                    <h1 id="title">Translate text</h1>

                    <div class="progress">
                        <div class="progress-bar" role="progressbar" aria-valuenow="70"
                             aria-valuemin="0" aria-valuemax="100" style="width:100%">
                            <span class="sr-only"></span>
                        </div>
                    </div>

                    <div style="float: left;">
                        <%
                            if (request.getSession().getAttribute("fromtext") == null) {
                                out.println("<textarea name=fromtext id=\"fromtext\" class=\"form-control\" rows=\"4\" cols=\"22\" placeholder=\"Enter text to translate\" required=\"true\"></textarea>");
                            } else {
                                out.println("<textarea name=fromtext id=\"fromtext\" class=\"form-control\" rows=\"4\" cols=\"22\">" + request.getSession().getAttribute("fromtext") + "</textarea>");
                            }
                        %> 
                    </div>
                    <div style="float: right;">
                        <select name="fromlang" class="form-control" data-toggle="tooltip" title="Select language of your text">
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
                        <label class="checkbox-inline"><input type="checkbox" name="autodetect" checked value="1"/>Auto detect language</label>
                        <button type="button" id="btnswap" class="btn btn-default btn-xs" onclick="swap();">
                            <span class="glyphicon glyphicon-resize-small"></span>Swap
                        </button>
                    </div>

                    <div id="sep3">
                    </div>

                    <div style="float: left;">
                        <%
                            if (request.getSession().getAttribute("final_result") == null) {
                                out.println("<textarea name=totext id=\"totext\" class=\"form-control\" rows=\"4\" cols=\"22\" placeholder=\"Translated text\" disabled></textarea>");
                            } else {
                                response.setCharacterEncoding("UTF-8");
                                out.println("<textarea name=totext id=\"totext\" class=\"form-control\" rows=\"4\" cols=\"22\" disabled>" + request.getSession().getAttribute("final_result") + "</textarea>");
                            }
                        %>                            
                    </div>
                    <div id="sep2">
                        <div style="float: right;">
                            <select name="tolang" class="form-control" data-toggle="tooltip" title="Select language to translate">
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
                        <button type="submit" class="btn btn-default" id="buttons">
                            <span class="glyphicon glyphicon-play-circle"></span>&nbsp;&nbsp;&nbsp;Translate
                        </button>
                    </div>
                </form>
                <div style="margin-top: 0.5em;">
                    <p id="yandex"><a href="http://translate.yandex.com/" target="_blank">Powered by Yandex.Translate</a></p>
                </div>

            </center>
        </div>


    </body>

</html>