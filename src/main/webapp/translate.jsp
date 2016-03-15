<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">
    <head>        
        <title>Translator</title>
        <style>
            input:not([type=checkbox]){
                border: 2px solid #dadada;
                height: 35px;
                width: 100%;
                /*border-radius: 7px;*/
            }
            input:focus:not([type=checkbox]){
                outline: none;
                border-color: #9ecaed;
                box-shadow: 0 0 10px #9ecaed;
            }
            #title{
                font-weight: 100;
                text-align: center;
                font-size: 1.4em;
            }
            body{
                background-size: cover;
                font-family: 'Roboto', sans-serif;
                background-color: darkgrey;
                background-image: url(shopping-cart-online.jpg);
                background-size: cover;
            }
            #translate{
                padding: 40px;
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
            .buttons{
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
        </style>
    </head>

    <body>
        <div id="header">
            <p class="header-text">Translator</p>
        </div>
        <div id="sep">
        </div>
        <div id="translate">
            <form name="translate_form" method="post" action="translator">
                <table>
                    <tr>
                        <td>

                            <%
                                if (request.getAttribute("fromtext") == null) {
                                    out.println("<input type=\"text\" name=\"fromtext\" placeholder=\"Enter text to translate\"/>");
                                } else {
                                    out.println("<input type=\"text\" name=\"fromtext\" placeholder=\"Enter text to translate\" value=" + request.getAttribute("fromtext") + ">");
                                }
                            %>

                        </td>
                        <td>
                            <select name="fromlang">
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
                        <td><input type="checkbox" name="autodetect" value="1"/>Auto detect language
                        </td>
                    </tr>

                    <tr>
                        <td>

                            <%
                                if (request.getAttribute("final_result") == null) {
                                    out.println("<input type=\"text\" name=\"totext\" placeholder=\"Translated text\" disabled/>");
                                } else {
                                    response.setCharacterEncoding("UTF-8");
                                    out.println("<input type=\"text\" name=\"fromtext\" placeholder=\"Translated text\" disabled value=" + request.getAttribute("final_result") + ">");
                                }
                            %>

                        </td>
                        <td>
                            <select name="tolang">
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
                    <tr>
                        <td><input type="reset" class="buttons" value="Reset values"/></td>
                        <td><input type="submit" class="buttons" value="Translate"/></td>
                    </tr>
                </table>    
            </form>
        </div>

        <div>
        <%
        out.println(hsenid.webapp.Translator.s);
        %>
        </div>
    </body>

</html>