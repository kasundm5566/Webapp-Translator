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
    <select id="comboPages" style="height: 32px; border-radius:4px;">
    </select>
</div>
<div id="pagination" class="text-right"></div>

<!-- Popup to get the confirmation to delete the selected user. -->
<div id="deleteUserPopup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/alert-yellow.png">&nbsp;&nbsp;Delete User</h4>
            </div>
            <div class="modal-body">
                <p id="dat">Are you sure you want to delete the following user?
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
                <button class="btn btn-success" id="deleteOk">Delete user</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

<!-- Popup to update the user details. -->
<div id="updateUserPopup" class="modal fade" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/alert-yellow.png">&nbsp;&nbsp;Update User</h4>
            </div>
            <div class="modal-body">
                <p>Enter the data you need to update and click 'Update' button.
                </p>
            </div>
            <div style="width: 80%;">
                <%@include file="update.jsp" %>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" id="updateOk">Update user</button>
                <button type="button" id="updateCancel" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

<!-- Delete user failed message popup -->
<div id="deleteUserFail" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/Error-128.png">&nbsp;&nbsp;Error Occurred</h4>
            </div>
            <div class="modal-body">
                <p>Error while trying to delete the user record.<br>
                    <b>Please try again later.</b><br>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
            </div>
        </div>
    </div>
</div>

<!-- Delete user success message popup -->
<div id="deleteUserSuccess" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/success.png">&nbsp;&nbsp;Process Succeeded</h4>
            </div>
            <div class="modal-body">
                <p>User record deleted successfully...
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
            </div>
        </div>
    </div>
</div>

<!-- Update user failed message popup -->
<div id="updateUserFail" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/Error-128.png">&nbsp;&nbsp;Error Occurred</h4>
            </div>
            <div class="modal-body">
                <p>Error while trying to update the user record.<br>
                    <b>Please verify the data entered or try again later.</b><br>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
            </div>
        </div>
    </div>
</div>

<!-- Update user success message popup -->
<div id="updateUserSuccess" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/success.png">&nbsp;&nbsp;Process Succeeded</h4>
            </div>
            <div class="modal-body">
                <p>User record updated successfully...
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
            </div>
        </div>
    </div>
</div>