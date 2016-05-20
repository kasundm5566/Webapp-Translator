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

//Validate the email address
function validateEmail2() {
    if ($("#uemail").val().length == 0) {
        $("#uemail_error").show();
        $("#uemail").css("background-color", background_color);
        $("#uemail_error").text("Email should not be empty.");
        return false;
    } else {
        var inputVal2 = $("#uemail").val();
        var emailReg = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
        if (!emailReg.test(inputVal2)) {
            $("#uemail_error").show();
            $("#uemail").css("background-color", background_color);
            $("#uemail_error").text("Please enter a valid email.");
            return false;
        } else {
            $("#uemail").css("background-color", "white");
            $("#uemail_error").hide();
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

// Validate user group
function validateGroup2() {
    if ($("#ugroupSelect").val() == null) {
        $("#ugroup_error").show();
        $("#ugroup_error").text("User must belongs to at least one group.");
        return false;
    } else {
        $("#ugroup_error").hide();
    }
}

function loadCities2() {
    var country = $('#ucountrySelect').val();
    $.ajax({
        type: "POST",
        url: "cityloader",
        dataType: "json",
        data: {"country": country},
        success: function (result) {
            var select = $("#ucitySelect"), options = '';
            select.empty();
            for (var i = 0; i < result.length; i++) {
                options += "<option value='" + result[i].cityname + "'>" + result[i].cityname + "</option>";
            }
            select.append(options);
        }
    });
}

function loadUserCity() {
    var uname = $("#uusername").val();
    $.ajax({
        type: "POST",
        url: "getusercity",
        dataType: "json",
        data: {"username": uname},
        success: function (userCity) {
            for (var i = 0; i < userCity.length; i++) {
                $("#ucitySelect option[value='" + userCity[i].cityname + "']").prop('selected', true);
            }
        }
    });
}

$('#ucountrySelect').change(function () {
    loadCities2();
});

function loadGroups2() {
    var username = $("#uusername").val();
    $("#ugroupSelect").multiselect({
        buttonWidth: '100%',
        onChange: function (element, checked) {
            validateGroup2();
        }
    });
    $.ajax({
        type: "POST",
        url: "grouploader",
        dataType: "json",
        success: function (groups) {
            $.ajax({
                type: "POST",
                url: "usergroupsloader",
                dataType: "json",
                data: {"userName": username},
                success: function (userGroups) {
                    var select = $("#ugroupSelect"), options = '';
                    select.empty();
                    for (var i = 0; i < groups.length; i++) {
                        options += "<option value='" + groups[i].groupname + "'>" + groups[i].groupname + "</option>";
                    }
                    select.append(options);
                    for (var i = 0; i < userGroups.length; i++) {
                        $("#ugroupSelect option[value='" + userGroups[i].groupname + "']").prop('selected', true);
                    }
                    $('#ugroupSelect').multiselect('rebuild');
                }
            });
        }
    });
}

function hideErrorLabels2() {
    $("#ufname_error").hide();
    $("#ulname_error").hide();
    $("#udob_error").hide();
    $("#uuname_error").hide();
    $("#ugroup_error").hide();
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

    $("#uemail").keyup(function () {
        validateEmail2();
    });
    $("#uemail").focusout(function () {
        validateEmail2();
    });

    $("#utel").keyup(function () {
        validateTelNo2();
    });
    $("#utel").focusout(function () {
        validateTelNo2();
    });
});