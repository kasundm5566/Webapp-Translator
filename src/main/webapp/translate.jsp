<center>
    <div style="width: 600px;">
        <h1 id="title"><fmt:message key="translator.title"/></h1>

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
                            request.setAttribute("frm", request.getSession().getAttribute("fromtext"));
                        %>
                        <c:choose>
                            <c:when test="${not empty frm}">
                                <c:set var="val" value="${frm}"/>
                                <textarea name="fromtext" id="fromtext" class="form-control" rows="6" cols="50"
                                          placeholder="Enter text to translate" required="true">${val}</textarea>
                            </c:when>
                            <c:otherwise>
                                <textarea name="fromtext" id="fromtext" class="form-control" rows="6" cols="50"
                                          placeholder="Enter text to translate" required="true"></textarea>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div style="display: table-cell;">
                        <select name="fromlang" class="form-control" data-toggle="tooltip"
                                title="Select language of your text">
                            <%
                                request.setAttribute("fromList",request.getSession().getAttribute("langs"));
                                request.setAttribute("fromSelected",request.getSession().getAttribute("fromlang"));
                            %>
                            <c:forEach items="${fromList}" var="fromLangs">
                                <c:choose>
                                    <c:when test="${fromLangs eq fromSelected}">
                                        <option selected>${fromLangs}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>${fromLangs}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div style="display: table; border-spacing: 10px;">
                <div style="display: table-row;">
                    <div style="display: table-cell;">
                        <label class="checkbox-inline"><input type="checkbox" id="autodetect" name="autodetect" checked
                                                              value="1"/><fmt:message
                                key="checkbox.autodetect.text"/></label>
                    </div>
                    <div style="display: table-cell;">
                        <button type="button" id="btnswap" class="btn btn-default btn-xs" onclick="swap();">
                            <span class="glyphicon glyphicon-resize-small"></span><fmt:message key="button.swap.text"/>
                        </button>
                    </div>
                </div>
            </div>

            <div style="display: table; border-spacing: 10px;">
                <div style="display: table-row;">
                    <div style="display: table-cell;">
                            <%
                                request.setAttribute("to", request.getSession().getAttribute("final_result"));
                            %>
                            <c:choose>
                                <c:when test="${not empty to}">
                                    <c:set var="toVal" value="${to}"/>
                                    <textarea name="totext" id="totext" class="form-control" rows="6" cols="50"
                                              placeholder="Translated text" disabled>${toVal}</textarea>
                                </c:when>
                                <c:otherwise>
                                    <textarea name="totext" id="totext" class="form-control" rows="6" cols="50"
                                              placeholder="Translated text" disabled></textarea>
                                </c:otherwise>
                            </c:choose>
                    </div>
                    <div style="display: table-cell;">
                        <select name="tolang" class="form-control" data-toggle="tooltip"
                                title="Select language to translate">
                            <%
                                request.setAttribute("toList",request.getSession().getAttribute("langs"));
                                request.setAttribute("toSelected",request.getSession().getAttribute("tolang"));
                            %>
                            <c:forEach items="${toList}" var="toLangs">
                                <c:choose>
                                    <c:when test="${toLangs eq toSelected}">
                                        <option selected>${toLangs}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>${toLangs}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <button type="submit" class="btn btn-default" id="buttons">
                    <span class="glyphicon glyphicon-play-circle"></span>&nbsp;&nbsp;&nbsp;<fmt:message
                        key="button.translate.text"/>
                </button>
            </div>
        </form>

        <div style="margin-top: 0.5em;">
            <p id="yandex"><a href="http://translate.yandex.com/" target="_blank"><fmt:message key="yandex.text"/></a>
            </p>
        </div>

    </div>
</center>