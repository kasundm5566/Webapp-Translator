var background_color = "#fde99c";

function validateName(name) {
    if (name == null || name == "") {
        document.getElementById("name_error").hidden = false;
        document.getElementById("name_error").innerHTML = "Name should not be empty";
        document.getElementById("name").focus();
        document.getElementById("name").style.backgroundColor = background_color;
    } else {
        var array = [];
        for (var i = 0; i < name.length; i++) {
            array[i] = name.charAt(i);
        }
        for (var b in array) {
            if (!isNaN(array[b])) {
                document.getElementById("name_error").hidden = false;
                document.getElementById("name_error").innerHTML = "Name should be in text";
                document.getElementById("name").focus();
                document.getElementById("name").select();
                document.getElementById("name").style.backgroundColor = background_color;
                break;
            } else {
                document.getElementById("name").style.backgroundColor = "white";
                document.getElementById("name_error").hidden = true;
            }
        }
    }
}

function validateUserName(username) {
    if (username == null || username == "") {
        document.getElementById("uname_error").hidden = false;
        document.getElementById("uname_error").innerHTML = "Username should not be empty";
        document.getElementById("username").focus();
        document.getElementById("username").style.backgroundColor = background_color;
    } else if (!isNaN(username.charAt(0))) {
        document.getElementById("uname_error").hidden = false;
        document.getElementById("uname_error").innerHTML = "First character of username is invalid";
        document.getElementById("username").focus();
        document.getElementById("username").select();
        document.getElementById("username").style.backgroundColor = background_color;
    } else {
        var array = [];
        for (var i = 0; i < username.length; i++) {
            array[i] = username.charAt(i);
        }
        for (var b in array) {
            if (array[b] == " ") {
                document.getElementById("uname_error").hidden = false;
                document.getElementById("uname_error").innerHTML = "Username should not include spaces";
                document.getElementById("username").focus();
                document.getElementById("username").select();
                document.getElementById("username").style.backgroundColor = background_color;
                break;
            } else {
                document.getElementById("username").style.backgroundColor = "white";
                document.getElementById("uname_error").hidden = true;
            }
        }
    }
}

function validateTel(tel) {
    if (tel.charAt(0) != 0) {
        document.getElementById("tel_error").hidden = false;
        document.getElementById("tel_error").innerHTML = "Telphone no should start with 0";
        document.getElementById("tel").focus();
        document.getElementById("tel").select();
        document.getElementById("tel").style.backgroundColor = background_color;
    } else if (isNaN(tel) || tel.length < 10) {
        document.getElementById("tel_error").hidden = false;
        document.getElementById("tel_error").innerHTML = "Invalid telephone no: check the length and the content";
        document.getElementById("tel").focus();
        document.getElementById("tel").select();
        document.getElementById("tel").style.backgroundColor = background_color;
    } else {
        document.getElementById("tel").style.backgroundColor = "white";
        document.getElementById("tel_error").hidden = true;
    }
}

function validateEmail(email) {
    var atIndex = email.indexOf("@");
    var dotIndex = email.indexOf(".");
    if (email == null || email == "") {
        document.getElementById("email_error").hidden = false;
        document.getElementById("email_error").innerHTML = "Email required";
        document.getElementById("email").focus();
        document.getElementById("email").style.backgroundColor = background_color;
    } else if (atIndex <= 0 || dotIndex <= 0 || dotIndex + 2 >= email.length) {
        document.getElementById("email_error").hidden = false;
        document.getElementById("email_error").innerHTML = "Invalid email";
        document.getElementById("email").focus();
        document.getElementById("email").select();
        document.getElementById("email").style.backgroundColor = background_color;
    } else {
        document.getElementById("email").style.backgroundColor = "white";
        document.getElementById("email_error").hidden = true;
    }
}
