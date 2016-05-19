function hideTableFeatures() {
    var userName = $("#userName").val();
    $.ajax({
        type: "post",
        dataType: "json",
        url: "getuserpermissions",
        data: {"userName": userName},
        success: function (result) {
            if (result.indexOf("EditUser") < 0) {
                $(".edit").hide();
            }
            if (result.indexOf("DeleteUser") < 0) {
                $(".delete").hide();
            }
            if(result.indexOf("EditUser") < 0 && result.indexOf("DeleteUser") < 0){
                $("#table").bootstrapTable('hideColumn',"operations");
            }
        }
    });
}

$(document).ready(function () {
    $('#tabSrch').click(function () {
        hideTableFeatures();
    });
});
