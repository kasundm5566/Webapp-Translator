<div class="input-group pull-right" style="margin-top:10px; margin-left:5px;">
    <div class="input-group">
        <input type="text" id="txtSearch" data-provide="typeahead" class="search form-control" placeholder="Search by user name">
        <div class="input-group-btn">
            <button class="btn btn-default" id="btnSearch" type="submit"><i class="glyphicon glyphicon-search"></i></button>
        </div>
    </div>
</div>

<table id="table"></table>

<!-- Popup to get the confirmation to delete the selected user. -->
<div id="deleteUserPopup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/alert-triangle-yellow-128.png">&nbsp;&nbsp;Delete User</h4>
            </div>
            <div class="modal-body">
                <p id="dat">Are you sure you want to delete the following user?
                </p>

                <div><label id="lbFname"></label></div>
                <div><label id="lbLname"></label></div>
                <div><label id="lbCountry"></label></div>
                <div><label id="lbDob"></label></div>
                <div><label id="lbUsrname"></label></div>
                <div><label id="lbEmail"></label></div>
                <div><label id="lbTel"></label></div>
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
                <h4 class="modal-title"><img src="images/alert-triangle-yellow-128.png">&nbsp;&nbsp;Update User</h4>
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
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>