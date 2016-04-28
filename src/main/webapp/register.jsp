<h1 id="title">Enter details to add a user</h1>

<div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="70"
         aria-valuemin="0" aria-valuemax="100" style="width:100%; height:5px;">
        <span class="sr-only">70% Complete</span>
    </div>
</div>
<form id="register" name="Register" method="post" action="register">
    <div class="panel-group" id="accordion">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                        <span class="glyphicon glyphicon-user"></span> User Details</a>
                </h4>
            </div>
            <div id="collapse1" class="panel-collapse collapse in well" style="margin-top:5px; ">

                <label id="fname_error"
                       style="color: red; font-weight: lighter; font-size: smaller;"></label>

                <div class="input-group">
                    <span class="input-group-addon">First name <font color="#FF0000">*</font></span>
                    <input type="text" id="fname" class="form-control" name="fname"
                           placeholder="Enter your first name"/>
                </div>
                <div style="margin-top:5px;">
                    <label id="lname_error"
                           style="color: red; font-weight: lighter; font-size: smaller;"></label>

                    <div class="input-group">
                        <span class="input-group-addon">Last name</span>
                        <input type="text" id="lname" class="form-control inp" name="lname"
                               placeholder="Enter your last name"/></div>
                </div>
                <div style="margin-top:5px;">
                    <div style="height: 35px;" class="input-group">
                        <span class="input-group-addon">Country <font color="#FF0000">*</font>&nbsp;&nbsp;</span>
                        <select id="countrySelect" class="form-control" name="country">
                            <option selected>Sri Lanka</option>
                            <option>Australia</option>
                            <option>China</option>
                            <option>Japan</option>
                            <option>USA</option>
                        </select>
                    </div></div>
                <div style="margin-top:5px;">
                    <div><label id="dob_error" style="color: red; font-weight: lighter; font-size: smaller;"></label></div>
                    <div class="input-group">
                        <span class="input-group-addon">DOB <font color="#FF0000">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <input type="text" class="form-control" id="date" name="date" placeholder="Enter your date of birth" readonly/>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                        <span class="glyphicon glyphicon-lock"></span> Account Details</a>
                </h4>
            </div>
            <div id="collapse2" class="panel-collapse collapse well" style="margin-top:5px;">

                <label id="uname_error"
                       style="color: red; font-weight: lighter; font-size: smaller;"></label>

                <div class="input-group">
                    <span class="input-group-addon">User name <font color="#FF0000">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <input type="text" id="username" class="form-control" name="username"
                           placeholder="Enter user name"/>
                </div>
                <div style="margin-top:5px;">
                    <label id="pass_error"
                           style="color: red; font-weight: lighter; font-size: smaller;"></label>

                    <div class="input-group">
                        <span class="input-group-addon">Password <font color="#FF0000">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <input type="password" id="pass" class="form-control" name="pass"
                               placeholder="Enter password"/>
                    </div></div>
                <div style="margin-top:5px;">
                    <label id="repass_error"
                           style="color: red; font-weight: lighter; font-size: smaller;"></label>

                    <div class="input-group">
                        <span class="input-group-addon">Retype password <font color="#FF0000">*</font></span>
                        <input type="password" id="repass" class="form-control" name="repass"
                               placeholder="Retype your password"/>
                    </div></div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
                        <span class="glyphicon glyphicon-envelope"></span> Contact Details</a>
                </h4>
            </div>
            <div id="collapse3" class="panel-collapse collapse well" style="margin-top:5px;">

                <label id="email_error"
                       style="color: red; font-weight: lighter; font-size: smaller;"></label>

                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon">Email <font color="#FF0000">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <input type="text" id="email" class="form-control" name="email"
                               placeholder="Enter your email. eg: example@host.com"/>
                    </div>
                </div>

                <label id="tel_error"
                       style="color: red; font-weight: lighter; font-size: smaller;"></label>

                <div class="input-group">
                    <span class="input-group-addon">Contact no <font color="#FF0000">*</font></span>
                    <input type="tel" id="tel" class="form-control" name="tel"
                           placeholder="Enter your contact no. eg: 94771234567"/>
                </div>
            </div>
        </div>
    </div>
    <div>
        <button type="submit" class="btn btn-default" id="submit">
            <span class="glyphicon glyphicon-user"></span>&nbsp;Register
        </button>
    </div>
</form>

<!-- Validation error popup -->
<div id="popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/error.png">&nbsp;&nbsp;Validation Errors...</h4>
            </div>
            <div class="modal-body">
                <p><b>Cannot register the new user</b> since there are <b>validation errors</b>,<br>
                    Please solve them and try again.
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Popup for user adding confirmation -->
<div id="addUserPopup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/alert-triangle-yellow-128.png">&nbsp;&nbsp;Add New User</h4>
            </div>
            <div class="modal-body">
                <p id="dat">Please verify the details and click 'Add user' to add the user to the database.
                </p>
                <div><label id="lblFname"></label></div>
                <div><label id="lblLname"></label></div>
                <div><label id="lblCountry"></label></div>
                <div><label id="lblDob"></label></div>
                <div><label id="lblUsrname"></label></div>
                <div><label id="lblEmail"></label></div>
                <div><label id="lblTel"></label></div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" id="addOk">Add user</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Add user success message popup -->
<div id="addUserSuccess" class="modal fade">
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <h4 class="modal-title"><img src="images/OK.png">&nbsp;&nbsp;Process Succeeded</h4>
        </div>
        <div class="modal-body">
            <p>New user record added successfully...
            </p>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
        </div>
    </div>
</div>
</div>

<!-- Add user failed message popup -->
<div id="addUserFail" class="modal fade">
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <h4 class="modal-title"><img src="images/Error-128.png">&nbsp;&nbsp;Error Occurred</h4>
        </div>
        <div class="modal-body">
            <p>Error while trying to add a new user record.<br>
                <b>Please verify the data entered and try again.</b><br>
                Sorry for the inconvenience.
            </p>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
        </div>
    </div>
</div>
</div>