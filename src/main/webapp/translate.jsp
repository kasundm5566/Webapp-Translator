<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>        
        <title>Translator</title>
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
                            <input type="text" name="fromtext" placeholder="Enter text to translate"/>
                        </td>
                        <td>
                            <select name="fromlang">
                                <%
                                    ArrayList<String> ar2 = new ArrayList<String>();
                                    ar2 = (ArrayList<String>) request.getAttribute("langs");
                                    for (int i = 0; i < ar2.size(); i++) {
                                        out.println("<option>" + ar2.get(i) + "</option>");
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="totext" placeholder="Translated text will be here" disabled />
                        </td>
                        <td>
                            <select name="tolang">
                                <%
                                    ArrayList<String> ar = new ArrayList<String>();
                                    ar = (ArrayList<String>) request.getAttribute("langs");
                                    for (int i = 0; i < ar.size(); i++) {
                                        out.println("<option>" + ar.get(i) + "</option>");
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
        </div>

        <%
            if (request.getAttribute("final_result") == null) {
                out.println("");
            } else {
                response.setCharacterEncoding("euc-jp");
                out.println(request.getAttribute("final_result"));
            }
        %>

    </body>

</html>