<form id="register" name="Register" method="post" action="register">
    <label>First name:</label>
    <label id="ufname_error"
           style="color: red; font-weight: lighter; font-size: smaller;"></label>
    <input type="text" id="ufname" class="form-control" name="ufname"
           placeholder="Enter your first name"/>

    <label>Last name:</label>
    <label id="ulname_error"
           style="color: red; font-weight: lighter; font-size: smaller;"></label>
    <input type="text" id="ulname" class="form-control inp" name="ulname"
           placeholder="Enter your last name"/>

    <label>Country:</label>
    <select id="ucountrySelect" class="form-control" name="ucountry">
        <option selected>Sri Lanka</option>
        <option>Australia</option>
        <option>China</option>
        <option>Japan</option>
        <option>USA</option>
    </select>

    <label>Date of birth:</label>
    <label id="udob_error" style="color: red; font-weight: lighter; font-size: smaller;"></label>
    <input type="text" class="form-control" id="udate" name="udate" placeholder="Enter your date of birth"
    </div>

    <label>User name:</label>
    <label id="uuname_error"
           style="color: red; font-weight: lighter; font-size: smaller;"></label>
    <input type="text" id="uusername" class="form-control" name="uusername"
           placeholder="Enter user name"/>

    <label>Email:</label>
    <label id="uemail_error"
           style="color: red; font-weight: lighter; font-size: smaller;"></label>
    <input type="text" id="uemail" class="form-control" name="uemail"
           placeholder="Enter your email. eg: example@host.com"/>

    <label>Tel:</label>
    <label id="utel_error"
           style="color: red; font-weight: lighter; font-size: smaller;"></label>
    <input type="tel" id="utel" class="form-control" name="utel"
           placeholder="Enter your contact no. eg: 94771234567"/>
</form>