<div class="input-group pull-right" style="margin-top:10px; margin-left:5px;">
    <div class="input-group">
        <input type="text" id="txtSearch" class="search form-control"
               placeholder="Search by first name">

        <div class="input-group-btn">
            <button class="btn btn-default" id="btnSearch" type="submit"><i class="glyphicon glyphicon-search"></i>
            </button>
            <button class="btn btn-default" id="btnRefresh" type="submit"><i class="glyphicon glyphicon-refresh"></i>
            </button>
        </div>
    </div>
</div>

<table id="table"></table>
<div class="input-group pull-right" style="margin-top:21px; margin-left:5px;">
    <label class="pagiTexts" style="display: inline;"><small><fmt:message key="search.gotopage"></fmt:message></small></label>
    <select id="comboPages" style="height: 32px; border-radius:4px;">
    </select>
    &nbsp;&nbsp;
    <label class="pagiTexts" style="display: inline;"><small><fmt:message key="search.recordsperpage"></fmt:message></small></label>
    <select id="comboRecCount" style="height: 32px; border-radius:4px;">
        <option value="10" selected>10</option>
        <option value="20">20</option>
        <option value="50">50</option>
        <option value="100">100</option>
        <option value="1000">1000</option>
    </select>
</div>
<div id="pagination" class="text-right"></div>
<div id="pagination2" class="text-right"></div>

<!-- Popup to get the confirmation to delete the selected user. -->
<div id="deleteUserPopup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/alert-yellow.png">&nbsp;&nbsp;<fmt:message key="search.delete.popup.title"/></h4>
            </div>
            <div class="modal-body">
                <p id="dat"><fmt:message key="search.delete.popup.message"/>
                </p>
                <div><label id="lbFname" style="text-align: left; display: block;"></label></div>
                <div><label id="lbLname" style="text-align: left; display: block;"></label></div>
                <div><label id="lbCountry" style="text-align: left; display: block;"></label></div>
                <div><label id="lbCity" style="text-align: left; display: block;"></label></div>
                <div><label id="lbDob" style="text-align: left; display: block;"></label></div>
                <div><label id="lbUsrname" style="text-align: left; display: block;"></label></div>
                <div><label id="lbEmail" style="text-align: left; display: block;"></label></div>
                <div><label id="lbTel" style="text-align: left; display: block;"></label></div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" id="deleteOk"><i class="glyphicon glyphicon-trash"></i> <fmt:message key="search.delete.popup.btndelete.text"/></button>
                <button type="button" class="btn btn-primary" data-dismiss="modal"><i class="glyphicon glyphicon-step-backward"></i> <fmt:message key="search.delete.popup.btncancel.text"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Popup to update the user details. -->
<div id="updateUserPopup" class="modal fade" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/alert-yellow.png">&nbsp;&nbsp;<fmt:message key="search.update.popup.title"/></h4>
            </div>
            <div class="modal-body">
                <p><fmt:message key="search.update.popup.message"/>
                </p>
            </div>
            <div style="width: 80%;">
                <%@include file="update.jsp" %>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" id="updateOk"><i class="glyphicon glyphicon-edit"></i> <fmt:message key="search.update.popup.btnupdate.text"/></button>
                <button type="button" id="updateCancel" class="btn btn-primary" data-dismiss="modal"><i class="glyphicon glyphicon-step-backward"></i> <fmt:message key="register.confirmadd.popup.canceltext"/> </button>
            </div>
        </div>
    </div>
</div>

<!-- Delete user failed message popup -->
<div id="deleteUserFail" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/Error-128.png">&nbsp;&nbsp;<fmt:message key="search.deleteerror.popup.title"/></h4>
            </div>
            <div class="modal-body">
                <p><fmt:message key="search.deleteerror.popup.text"/><br>
                    <b>P<fmt:message key="search.deleteerror.popup.boldtext"/></b><br>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key="register.addsuccess.popup.btnok.text"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Delete user success message popup -->
<div id="deleteUserSuccess" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/success.png">&nbsp;&nbsp;<fmt:message key="search.deletesuccess.title"/></h4>
            </div>
            <div class="modal-body">
                <p>User record deleted successfully...
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key="register.addsuccess.popup.btnok.text"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Update user failed message popup -->
<div id="updateUserFail" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/Error-128.png">&nbsp;&nbsp;<fmt:message key="search.deleteerror.popup.title"/></h4>
            </div>
            <div class="modal-body">
                <p><fmt:message key="search.updateerror.popup.text"/><br>
                    <b><fmt:message key="search.updateerror.popup.boldtext"/></b><br>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key="register.addsuccess.popup.btnok.text"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Update user success message popup -->
<div id="updateUserSuccess" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/success.png">&nbsp;&nbsp;<fmt:message key="search.deletesuccess.title"/></h4>
            </div>
            <div class="modal-body">
                <p><fmt:message key="search.updatesucess.message"/>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key="register.addsuccess.popup.btnok.text"/></button>
            </div>
        </div>
    </div>
</div>