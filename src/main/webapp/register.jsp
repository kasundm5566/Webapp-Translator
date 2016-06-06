<h1 id="title"><fmt:message key="register.title"/></h1>

<div style="width: 600px;">
    <div class="progress" style="height: 5px;">
        <div class="progress-bar" role="progressbar" aria-valuenow="70"
             aria-valuemin="0" aria-valuemax="100" style="width:100%;">
            <span class="sr-only">70% Complete</span>
        </div>
    </div>
    <form id="register" name="Register" method="post" action="register">
        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title" style="text-align: left;">
                        <a id="userDetailsTab" data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                            <span class="glyphicon glyphicon-user"></span> <fmt:message
                                key="register.userdetailstab.text"/></a>
                    </h4>
                </div>
                <div id="collapse1" class="panel-collapse collapse in well">

                    <label style="text-align: left; display: block;"><fmt:message key="register.firstname.text"/><font
                            color="#FF0000"><fmt:message key="mandatory.indicator"/></font></label>
                    <label id="fname_error"
                           style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-edit"></i></span>
                        <input type="text" id="fname" class="form-control" name="fname"
                               placeholder="Enter your first name"/>
                    </div>
                    <div style="margin-top:5px;">
                        <label style="text-align: left; display: block;"><fmt:message
                                key="register.lastname.text"/></label>
                        <label id="lname_error"
                               style="color: red; font-weight: lighter; font-size: smaller;" text-align: left; display:
                               block;></label>

                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-edit"></i></span>
                            <input type="text" id="lname" class="form-control inp" name="lname"
                                   placeholder="Enter your last name"/></div>
                    </div>
                    <div style="margin-top:5px;">
                        <label style="text-align: left; display: block;"><fmt:message key="register.country.text"/><font
                                color="#FF0000"><fmt:message key="mandatory.indicator"/></font></label>

                        <div style="height: 35px;" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-map-marker"></i></span>
                            <select id="countrySelect" class="form-control" name="country">
                                <option selected>Sri Lanka</option>
                                <option>Australia</option>
                                <option>China</option>
                                <option>Japan</option>
                                <option>USA</option>
                            </select>
                        </div>
                    </div>
                    <div style="margin-top:5px;">
                        <label style="text-align: left; display: block;"><fmt:message key="register.city.text"/><font
                                color="#FF0000"><fmt:message key="mandatory.indicator"/></font></label>

                        <div style="height: 35px;" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-map-marker"></i></span>
                            <select id="citySelect" class="form-control" name="city">
                            </select>
                        </div>
                    </div>
                    <div style="margin-top:5px;">
                        <label style="text-align: left; display: block;"><fmt:message key="register.dob.text"/><font
                                color="#FF0000"><fmt:message key="mandatory.indicator"/></font></label>

                        <div><label id="dob_error"
                                    style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                            <input type="text" class="form-control" id="date" name="date"
                                   placeholder="Enter your date of birth" readonly/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title" style="text-align: left;">
                        <a id="accoutDetailsTab" data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                            <span class="glyphicon glyphicon-lock"></span> <fmt:message
                                key="register.accountdetailstab.text"/></a>
                    </h4>
                </div>
                <div id="collapse2" class="panel-collapse collapse well">
                    <label style="text-align: left; display: block;"><fmt:message key="register.username.text"/><font
                            color="#FF0000"><fmt:message key="mandatory.indicator"/></font></label>
                    <label id="uname_error"
                           style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input type="text" id="username" class="form-control" name="username"
                               placeholder="Enter user name"/>
                    </div>
                    <div style="margin-top:5px;">
                        <label style="text-align: left; display: block;"><fmt:message
                                key="register.password.text"/><font
                                color="#FF0000"><fmt:message key="mandatory.indicator"/></font></label>
                        <label id="pass_error"
                               style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>

                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input type="password" id="pass" class="form-control" name="pass"
                                   placeholder="Enter password"/>
                        </div>
                    </div>
                    <div style="margin-top:5px;">
                        <label style="text-align: left; display: block;"><fmt:message
                                key="register.repassword.text"/><font
                                color="#FF0000"><fmt:message key="mandatory.indicator"/></font></label>
                        <label id="repass_error"
                               style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>

                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input type="password" id="repass" class="form-control" name="repass"
                                   placeholder="Retype your password"/>
                        </div>
                    </div>
                    <div style="margin-top:5px;">
                        <label style="text-align: left; display: block;"><fmt:message
                                key="register.usergroup.text"/><font
                                color="#FF0000"><fmt:message key="mandatory.indicator"/></font></label>
                        <label id="group_error"
                               style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>

                        <div style="height: 35px;" class="input-group btn-group" >
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <select id="groupSelect" class="form-control" name="group" multiple="multiple">
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title" style="text-align: left;">
                        <a id="contactDetailsTab" data-toggle="collapse" data-parent="#accordion" href="#collapse3">
                            <span class="glyphicon glyphicon-envelope"></span> <fmt:message
                                key="register.contactdetailstab.text"/></a>
                    </h4>
                </div>
                <div id="collapse3" class="panel-collapse collapse well" style="margin-top:5px;">

                    <label style="text-align: left; display: block;"><fmt:message key="register.email.text"/><font
                            color="#FF0000"><fmt:message key="mandatory.indicator"/></font></label>
                    <label id="email_error"
                           style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>

                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input type="text" id="email" class="form-control" name="email"
                                   placeholder="Enter your email. eg: example@host.com"/>
                        </div>
                    </div>
                    <label style="text-align: left; display: block;"><fmt:message key="register.contactno.text"/><font
                            color="#FF0000"><fmt:message key="mandatory.indicator"/></font></label>
                    <label id="tel_error"
                           style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
                        <input type="tel" id="tel" class="form-control" name="tel"
                               placeholder="Enter your contact no. eg: 94771234567"/>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <button type="submit" class="btn btn-default" id="submit">
                <span class="glyphicon glyphicon-user"></span>&nbsp;<fmt:message key="register.submit.text"/>
            </button>
        </div>
    </form>
</div>

<!-- Validation error popup -->
<div id="popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/error.png">&nbsp;&nbsp;<fmt:message
                        key="register.validationerror.popup.title"/></h4>
            </div>
            <div class="modal-body">
                <p><b><fmt:message key="register.validationerror.popup.boldtext1"/></b> <fmt:message
                        key="register.validationerror.popup.text1"/> <b><fmt:message
                        key="register.validationerror.popup.boldtext2"/></b><fmt:message
                        key="register.validationerror.popup.text.separator"/><br>
                    <fmt:message key="register.validationerror.popup.text2"/>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message
                        key="register.validationerror.popup.btnclose.text"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Popup for user adding confirmation -->
<div id="addUserPopup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/alert-yellow.png">&nbsp;&nbsp;<fmt:message
                        key="register.confirmadd.popup.title"/></h4>
            </div>
            <div class="modal-body">
                <p id="dat" style="text-align: left; display: block;"><fmt:message
                        key="register.confirmadd.popup.text"/>
                </p>

                <div style="text-align: left; display: block;"><label id="lblFname"></label></div>
                <div style="text-align: left; display: block;"><label id="lblLname"></label></div>
                <div style="text-align: left; display: block;"><label id="lblCountry"></label></div>
                <div style="text-align: left; display: block;"><label id="lblCity"></label></div>
                <div style="text-align: left; display: block;"><label id="lblDob"></label></div>
                <div style="text-align: left; display: block;"><label id="lblUsrname"></label></div>
                <div style="text-align: left; display: block;"><label id="lblGroup"></label></div>
                <div style="text-align: left; display: block;"><label id="lblEmail"></label></div>
                <div style="text-align: left; display: block;"><label id="lblTel"></label></div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" id="addOk"><i class="glyphicon glyphicon-export"></i> <fmt:message
                        key="register.confirmadd.popup.okaytext"/></button>
                <button type="button" class="btn btn-primary" data-dismiss="modal"><i
                        class="glyphicon glyphicon-step-backward"></i> <fmt:message
                        key="register.confirmadd.popup.canceltext"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Add user success message popup -->
<div id="addUserSuccess" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/success.png">&nbsp;&nbsp;<fmt:message
                        key="register.addsuccess.popup.text"/></h4>
            </div>
            <div class="modal-body">
                <p><fmt:message key="register.addsuccess.popup.message"/>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message
                        key="register.addsuccess.popup.btnok.text"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Add user failed message popup -->
<div id="addUserFail" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><img src="images/Error-128.png">&nbsp;&nbsp;<fmt:message
                        key="register.adderror.popup.title"/></h4>
            </div>
            <div class="modal-body">
                <p><fmt:message key="register.adderror.popup.text1"/><br>
                    <b><fmt:message key="register.adderror.popup.boldtext"/></b><br>
                    <fmt:message key="register.adderror.popup.text2"/>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message
                        key="register.addsuccess.popup.btnok.text"/></button>
            </div>
        </div>
    </div>
</div>