<form id="update" name="update" method="post" action="update">
    <label style="text-align: left; display: block;">First name:</label>
    <label id="ufname_error"
           style="color: red; font-weight: lighter; font-size: smaller;  text-align: left; display: block;"></label>
    <input type="text" id="ufname" class="form-control" name="ufname"
           placeholder="Enter your first name"/>

    <label style="text-align: left; display: block;">Last name:</label>
    <label id="ulname_error"
           style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>
    <input type="text" id="ulname" class="form-control inp" name="ulname"
           placeholder="Enter your last name"/>

    <label style="text-align: left; display: block;">Country:</label>
    <select id="ucountrySelect" class="form-control" name="ucountry" text-align: left; display: block;>
        <option selected>Sri Lanka</option>
        <option>Australia</option>
        <option>China</option>
        <option>Japan</option>
        <option>USA</option>
    </select>

    <label style="text-align: left; display: block;">City:</label>
    <select id="ucitySelect" class="form-control" name="ucity" text-align: left; display: block;></select>

    <label style="text-align: left; display: block;">Date of birth:</label>
    <label id="udob_error" style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>
    <input type="text" class="form-control" id="udate" name="udate" placeholder="Enter your date of birth"
    </div>

    <label style="text-align: left; display: block;">User name:</label>
    <label id="uuname_error"
           style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>
    <input type="text" id="uusername" class="form-control" name="uusername"
           placeholder="Enter user name" disabled/>

    <label style="text-align: left; display: block;">Email:</label>
    <label id="uemail_error"
           style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>
    <input type="text" id="uemail" class="form-control" name="uemail"
           placeholder="Enter your email. eg: example@host.com"/>

    <label style="text-align: left; display: block;">Tel:</label>
    <label id="utel_error"
           style="color: red; font-weight: lighter; font-size: smaller; text-align: left; display: block;"></label>
    <input type="tel" id="utel" class="form-control" name="utel"
           placeholder="Enter your contact no. eg: 94771234567"/>
</form>