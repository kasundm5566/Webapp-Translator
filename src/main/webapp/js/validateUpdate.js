var background_color = "#fde99c";

// Validate the first name
function validateFirstName2() {
    if ($("#ufname").val().length == 0) {
        $("#ufname_error").show();
        $("#ufname").css("background-color", background_color);
        $("#ufname_error").text("First name should not be empty.");
        return false;
    } else {
        var inputVal = $("#ufname").val();
        var numericReg = /^[a-zA-Z]+$/;
        if (!numericReg.test(inputVal)) {
            $("#ufname_error").show();
            $("#ufname").css("background-color", background_color);
            $("#ufname_error").text("Invalid first name. Only alphabetic characters without spaces allowed.");
            return false;
        } else {
            $("#ufname").css("background-color", "white");
            $("#ufname_error").hide();
        }
    }
}

// Validate the last name
function validateLastName2() {
    if ($("#ulname").val().length == 0) {
        $("#ulname").css("background-color", "white");
        $("#ulname_error").hide();
    } else {
        var inputVal = $("#ulname").val();
        var numericReg = /^[a-zA-Z]+$/;
        if (!numericReg.test(inputVal)) {
            $("#ulname_error").show();
            $("#ulname").css("background-color", background_color);
            $("#ulname_error").text("Invalid last name. Only alphabetic characters without spaces allowed.");
            return false;
        } else {
            $("#ulname").css("background-color", "white");
            $("#ulname_error").hide();
        }
    }
}

// Validate the password
function validatePassword2() {
    if ($("#upass").val().length > 7 && $("#pass").val().length < 17) {
        var inputVal = $("#upass").val();
        var oneDigit = /^(?=.*\d).+$/;
        var oneSpecChar = /^(?=.*[_\W]).+$/;
        if (!oneDigit.test(inputVal)) {
            $("#upass_error").show();
            $("#upass").css("background-color", background_color);
            $("#upass_error").text("Password should contain at least one digit.");
            return false;
        } else if (!oneSpecChar.test(inputVal)) {
            $("#upass_error").show();
            $("#upass").css("background-color", background_color);
            $("#upass_error").text("Password should contain at least one special character.");
            return false;
        } else {
            $("#upass").css("background-color", "white");
            $("#upass_error").hide();
        }
    } else {
        $("#upass_error").text("Password length should be in between 8 to 16.");
        $("#upass").css("background-color", background_color);
        $("#upass_error").show();
        return false;
    }
}

//Validate the email address
function validateEmail2() {
    if ($("#uemail").val().length == 0) {
        $("#uemail_error").show();
        $("#uemail").css("background-color", background_color);
        $("#uemail_error").text("Email should not be empty.");
        return false;
    } else {
        var inputVal = $("#uemail").val();
        var emailReg = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
        if (!emailReg.test(inputVal)) {
            $("#uemail_error").show();
            $("#uemail").css("background-color", background_color);
            $("#uemail_error").text("Please enter a valid email.");
            return false;
        } else {
            $("#email").css("background-color", "white");
            $("#email_error").hide();
        }
    }
}

// Validate the tel no
function validateTelNo2() {
    if ($("#utel").val().length != 11) {
        $("#utel_error").show();
        $("#utel").css("background-color", background_color);
        $("#utel_error").text("Telephone no should contain 11 characters without the leading zero.");
        return false;
    } else {
        var inputVal = $("#utel").val();
        var telReg = /^94[^0]\d+$/;
        if (!telReg.test(inputVal)) {
            $("#utel_error").show();
            $("#utel").css("background-color", background_color);
            $("#utel_error").text("Please enter a valid telephone no.");
            return false;
        } else {
            $("#utel").css("background-color", "white");
            $("#utel_error").hide();
        }
    }
}

// Validate the re-typed password
function validateRetypedPass2() {
    if ($("#urepass").val().length == 0) {
        $("#urepass_error").show();
        $("#urepass").css("background-color", background_color);
        $("#urepass_error").text("Please re-type the password.");
        return false;
    } else {
        var pass = $("#upass").val();
        var repass = $("#urepass").val();
        if (pass != repass) {
            $("#urepass_error").show();
            $("#urepass").css("background-color", background_color);
            $("#urepass_error").text("Passwords do not match.");
            return false;
        } else {
            $("#urepass").css("background-color", "white");
            $("#urepass_error").hide();
        }
    }
}

// Validate the DOB
function validateDOB2() {
    if ($("#udate").val().length == 0) {
        $("#udob_error").show();
        $("#udate").css("background-color", background_color);
        $("#udob_error").text("Date of birth is mandatory.");
        return false;
    } else {
        $("#udate").css("background-color", "white");
        $("#udob_error").hide();
    }
}

// Check user name availability
function validateUserName2() {
    $("#uuname_error").css("color", "red");
    var userName = $("#uusername").val();
    var unameRegex = /^[a-zA-z][a-zA-Z0-9_]+$/;
    if ($("#uusername").val().length == 0) {
        $("#uuname_error").show();
        $("#uusername").css("background-color", background_color);
        $("#uuname_error").text("User name should not empty.");
        return false;
    } else {
        $.ajax({
            type: "POST",
            url: "checkusername",
            data: "username=" + userName,
            success: function (result) {
                if ($.trim(result) === "NO") {
                    $("#uuname_error").show();
                    $("#uusername").css("background-color", background_color);
                    $("#uuname_error").text("That user name already used.");
                    return false;
                } else {
                    $("#uuname_error").css("color", "#009900");
                    $("#uuname_error").show();
                    $("#uusername").css("background-color", "white");
                    $("#uuname_error").text("User name is valid.");
                }
            }
        });
    }
}

function hideErrorLabels2() {
    $("#ufname_error").hide();
    $("#ulname_error").hide();
    $("#udob_error").hide();
    $("#uuname_error").hide();
    $("#upass_error").hide();
    $("#urepass_error").hide();
    $("#uemail_error").hide();
    $("#utel_error").hide();
}
$(document).ready(function () {
    hideErrorLabels2();

    $("#ufname").keyup(function () {
        validateFirstName2();
    });
    $("#ufname").focusout(function () {
        validateFirstName2();
    });

    $("#ulname").keyup(function () {
        validateLastName2();
    });
    $("#ulname").focusout(function () {
        validateLastName2();
    });

    $("#udate").focusout(function () {
        validateDOB2();
    });

    $("#pass").keyup(function () {
        validatePassword();
    });
    $("#upass").focusout(function () {
        validatePassword2();
    });

    $("#urepass").keyup(function () {
        validateRetypedPass2();
    });
    $("#urepass").focusout(function () {
        validateRetypedPass2();
    });

    $("#uemail").keyup(function () {
        validateEmail2();
    });
    $("#uemail").focusout(function () {
        validateEmail2();
    });

    $("#utel").keyup(function () {
        validateTelNo();
    });
    $("#utel").focusout(function () {
        validateTelNo2();
    });

    $("#uusername").keyup(function () {
        validateUserName2();
    });
    $("#uusername").focusout(function () {
        validateUserName2();
    });

 /*   $("#uregister").submit(function () {
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
*/

/*
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
*/
});