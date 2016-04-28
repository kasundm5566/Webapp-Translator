var background_color = "#fde99c";

// Validate the first name
function validateFirstName() {
    if ($("#fname").val().length == 0) {
        $("#fname_error").show();
        $("#fname").css("background-color", background_color);
        $("#fname_error").text("First name should not be empty.");
        return false;
    } else {
        var inputVal = $("#fname").val();
        var numericReg = /^[a-zA-Z]+$/;
        if (!numericReg.test(inputVal)) {
            $("#fname_error").show();
            $("#fname").css("background-color", background_color);
            $("#fname_error").text("Invalid first name. Only alphabetic characters without spaces allowed.");
            return false;
        } else {
            $("#fname").css("background-color", "white");
            $("#fname_error").hide();
        }
    }
}

// Validate the last name
function validateLastName() {
    if ($("#lname").val().length == 0) {
        $("#lname").css("background-color", "white");
        $("#lname_error").hide();
    } else {
        var inputVal = $("#lname").val();
        var numericReg = /^[a-zA-Z]+$/;
        if (!numericReg.test(inputVal)) {
            $("#lname_error").show();
            $("#lname").css("background-color", background_color);
            $("#lname_error").text("Invalid last name. Only alphabetic characters without spaces allowed.");
            return false;
        } else {
            $("#lname").css("background-color", "white");
            $("#lname_error").hide();
        }
    }
}

// Validate the password
function validatePassword() {
    if ($("#pass").val().length > 7 && $("#pass").val().length < 17) {
        var inputVal = $("#pass").val();
        var oneDigit = /^(?=.*\d).+$/;
        var oneSpecChar = /^(?=.*[_\W]).+$/;
        if (!oneDigit.test(inputVal)) {
            $("#pass_error").show();
            $("#pass").css("background-color", background_color);
            $("#pass_error").text("Password should contain at least one digit.");
            return false;
        } else if (!oneSpecChar.test(inputVal)) {
            $("#pass_error").show();
            $("#pass").css("background-color", background_color);
            $("#pass_error").text("Password should contain at least one special character.");
            return false;
        } else {
            $("#pass").css("background-color", "white");
            $("#pass_error").hide();
        }
    } else {
        $("#pass_error").text("Password length should be in between 8 to 16.");
        $("#pass").css("background-color", background_color);
        $("#pass_error").show();
        return false;
    }
}

//Validate the email address
function validateEmail() {
    if ($("#email").val().length == 0) {
        $("#email_error").show();
        $("#email").css("background-color", background_color);
        $("#email_error").text("Email should not be empty.");
        return false;
    } else {
        var inputVal = $("#email").val();
        var emailReg = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
        if (!emailReg.test(inputVal)) {
            $("#email_error").show();
            $("#email").css("background-color", background_color);
            $("#email_error").text("Please enter a valid email.");
            return false;
        } else {
            $("#email").css("background-color", "white");
            $("#email_error").hide();
        }
    }
}

// Validate the tel no
function validateTelNo() {
    if ($("#tel").val().length != 11) {
        $("#tel_error").show();
        $("#tel").css("background-color", background_color);
        $("#tel_error").text("Telephone no should contain 11 characters without the leading zero.");
        return false;
    } else {
        var inputVal = $("#tel").val();
        var telReg = /^94[^0]\d+$/;
        if (!telReg.test(inputVal)) {
            $("#tel_error").show();
            $("#tel").css("background-color", background_color);
            $("#tel_error").text("Please enter a valid telephone no.");
            return false;
        } else {
            $("#tel").css("background-color", "white");
            $("#tel_error").hide();
        }
    }
}

// Validate the re-typed password
function validateRetypedPass() {
    if ($("#repass").val().length == 0) {
        $("#repass_error").show();
        $("#repass").css("background-color", background_color);
        $("#repass_error").text("Please re-type the password.");
        return false;
    } else {
        var pass = $("#pass").val();
        var repass = $("#repass").val();
        if (pass != repass) {
            $("#repass_error").show();
            $("#repass").css("background-color", background_color);
            $("#repass_error").text("Passwords do not match.");
            return false;
        } else {
            $("#repass").css("background-color", "white");
            $("#repass_error").hide();
        }
    }
}

// Validate the DOB
function validateDOB() {
    if ($("#date").val().length == 0) {
        $("#dob_error").show();
        $("#date").css("background-color", background_color);
        $("#dob_error").text("Date of birth is mandatory");
        return false;
    } else {
        $("#date").css("background-color", "white");
        $("#dob_error").hide();
    }
}

// Check user name availability
function validateUserName() {
    $("#uname_error").css("color", "red");
    var userName = $("#username").val();
    var unameRegex = /^[a-zA-z][a-zA-Z0-9_]+$/;
    if ($("#username").val().length == 0) {
        $("#uname_error").show();
        $("#username").css("background-color", background_color);
        $("#uname_error").text("User name should not empty.");
        return false;
    } else {
        $.ajax({
            type: "POST",
            url: "checkusername",
            data: "username=" + userName,
            success: function (result) {
                if ($.trim(result) === "NO") {
                    $("#uname_error").show();
                    $("#username").css("background-color", background_color);
                    $("#uname_error").text("That user name already used.");
                    return false;
                } else {
                    $("#uname_error").css("color", "#009900");
                    $("#uname_error").show();
                    $("#username").css("background-color", "white");
                    $("#uname_error").text("User name is valid.");
                }
            }
        });
    }
}

function hideErrorLabels() {
    $("#fname_error").hide();
    $("#lname_error").hide();
    $("#dob_error").hide();
    $("#uname_error").hide();
    $("#pass_error").hide();
    $("#repass_error").hide();
    $("#email_error").hide();
    $("#tel_error").hide();
}
$(document).ready(function () {
    hideErrorLabels();

    $("#fname").keyup(function () {
        validateFirstName();
    });
    $("#fname").focusout(function () {
        validateFirstName();
    });

    $("#lname").keyup(function () {
        validateLastName();
    });
    $("#lname").focusout(function () {
        validateLastName();
    });

    $("#date").focusout(function () {
        validateDOB();
    });

    $("#pass").keyup(function () {
        validatePassword();
    });
    $("#pass").focusout(function () {
        validatePassword();
    });

    $("#repass").keyup(function () {
        validateRetypedPass();
    });
    $("#repass").focusout(function () {
        validateRetypedPass();
    });

    $("#email").keyup(function () {
        validateEmail();
    });
    $("#email").focusout(function () {
        validateEmail();
    });

    $("#tel").keyup(function () {
        validateTelNo();
    });
    $("#tel").focusout(function () {
        validateTelNo();
    });

    $("#username").keyup(function () {
        validateUserName();
    });
    $("#username").focusout(function () {
        validateUserName();
    });

    $("#register").submit(function () {
        if (validateFirstName() == false || validateLastName() == false || validateDOB() == false || validateUserName() == false || validatePassword() == false || validateRetypedPass() == false || validateEmail() == false || validateTelNo() == false) {
            $('#popup').modal('show');
            return false;
        }else{
            $('#lblFname').text("First name: "+$('#fname').val());
            $('#lblLname').text("Last name: "+$('#lname').val());
            $('#lblCountry').text("Country: "+$('#countrySelect').val());
            $('#lblDob').text("Date of birth: "+$('#date').val());
            $('#lblUsrname').text("User name: "+$('#username').val());
            $('#lblEmail').text("Email: "+$('#email').val());
            $('#lblTel').text("Contact no: "+$('#tel').val());
            $('#addUserPopup').modal('show');
            return false;
        }
    });

    $("#addOk").click(function(){
        $.ajax({
            type: "POST",
            url: "register",
            data: $('#register').serialize(),
            success: function (result) {
                if($.trim(result)==1){
                    $('#addUserPopup').modal('hide');
                    $('#addUserSuccess').modal('show');
                    $('#register').trigger('reset');
                }else{
                    $('#addUserPopup').modal('hide');
                    $('#addUserFail').modal('show');
                }
            }
        });
    });
});