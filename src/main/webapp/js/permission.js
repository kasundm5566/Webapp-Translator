function hideTableFeatures() {
    var userName = $("#loginusername").val();
    $.ajax({
        type: "post",
        dataType: "json",
        url: "getuserpermissions",
        data: {"userName": userName},

        success: function (result) {
            /*alert(result);*/
            if(result.indexOf("EditUser") < 0){
                $(".edit").hide();
            }
        }
    });
}

$(document).ready(function () {
    $("#loginButton").click(function () {
        hideTableFeatures();
    });
});
