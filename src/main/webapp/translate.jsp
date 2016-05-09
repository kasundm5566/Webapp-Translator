<center>
    <div style="width: 600px;">
        <h1 id="title">Translate text</h1>

        <div class="progress" style="height: 5px;">
            <div class="progress-bar" role="progressbar" aria-valuenow="70"
                 aria-valuemin="0" aria-valuemax="100" style="width:100%;">
                <span class="sr-only"></span>
            </div>
        </div>

        <form name="translate_form" method="post" action="translator" id="translate_form">

            <div style="display: table; border-spacing: 10px;">
                <div style="display: table-row;">
                    <div style="display: table-cell;">
                        <%
                            if (request.getSession().getAttribute("fromtext") == null) {
                                out.println("<textarea name=fromtext id=\"fromtext\" class=\"form-control\" rows=\"6\" cols=\"50\" placeholder=\"Enter text to translate\" required=\"true\"></textarea>");
                            } else {
                                out.println("<textarea name=fromtext id=\"fromtext\" class=\"form-control\" rows=\"6\" cols=\"50\">" + request.getSession().getAttribute("fromtext") + "</textarea>");
                            }
                        %>
                    </div>
                    <div style="display: table-cell;">

                        <select name="fromlang" class="form-control" data-toggle="tooltip"
                                title="Select language of your text">
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
                </div>
            </div>

            <div style="display: table; border-spacing: 10px;">
                <div style="display: table-row;">
                    <div style="display: table-cell;">
                        <label class="checkbox-inline"><input type="checkbox" id="autodetect" name="autodetect" checked
                                                              value="1"/>Auto
                            detect
                            language</label>
                    </div>
                    <div style="display: table-cell;">
                        <button type="button" id="btnswap" class="btn btn-default btn-xs" onclick="swap();">
                            <span class="glyphicon glyphicon-resize-small"></span>Swap
                        </button>
                    </div>
                </div>
            </div>

            <div style="display: table; border-spacing: 10px;">
                <div style="display: table-row;">
                    <div style="display: table-cell;">
                        <%
                            if (request.getSession().getAttribute("final_result") == null) {
                                out.println("<textarea name=totext id=\"totext\" class=\"form-control\" rows=\"6\" cols=\"50\" placeholder=\"Translated text\" disabled></textarea>");
                            } else {
                                response.setCharacterEncoding("UTF-8");
                                out.println("<textarea name=totext id=\"totext\" class=\"form-control\" rows=\"6\" cols=\"50\" disabled>" + request.getSession().getAttribute("final_result") + "</textarea>");
                            }
                        %>
                    </div>
                    <div style="display: table-cell;">
                        <select name="tolang" class="form-control" data-toggle="tooltip"
                                title="Select language to translate">
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
                </div>
            </div>
            <div>
                <button type="submit" class="btn btn-default" id="buttons">
                    <span class="glyphicon glyphicon-play-circle"></span>&nbsp;&nbsp;&nbsp;Translate
                </button>
            </div>
        </form>

        <div style="margin-top: 0.5em;">
            <p id="yandex"><a href="http://translate.yandex.com/" target="_blank">Powered by
                Yandex.Translate</a></p>
        </div>

    </div>
</center>