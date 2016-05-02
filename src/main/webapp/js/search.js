$(document).ready(function () {
    loadTableData();

    $('#txtSearch').keyup(function () {
        autoFillSearch();
    });
    $('#txtSearch').change(function () {
        autoFillSearch();
    });

    $('#btnRefresh').click(function () {
        refresh();
    });

    $('#btnSearch').click(function () {
        search();
    });
});

function loadTableData() {
    $.ajax({
        type: "POST",
        url: "load",
        dataType: "json",
        success: function (result) {
            $('#table').bootstrapTable({
                pagination: true,
                pageSize: 10,
                pageList: [10, 25, 50, 100, 200],
                showColumns: true,
                singleSelect: true,
                minimumCountColumns: 3,
                columns: [/*{
                 field: 'state',
                 checkbox: true
                 }, */{
                    field: 'firstname',
                    title: 'First name',
                    sortable: true
                }, {
                    field: 'lastname',
                    title: 'Last name',
                    sortable: true
                }, {
                    field: 'country',
                    title: 'Country',
                    sortable: true
                }, {
                    field: 'dob',
                    title: 'DOB',
                    sortable: true
                }, {
                    field: 'username',
                    title: 'User name'
                }, {
                    field: 'email',
                    title: 'Email'
                }, {
                    field: 'tel',
                    title: 'Contact no'
                }, {
                    field: 'operations',
                    title: 'Operations',
                    formatter: operateFormatter,
                    events: operateEvents
                }],
                data: result
            });
        }
    });
}

function operateFormatter(value, row, index) {
    return [
        '<center>',
        '<a class="edit" href="javascript:void(0)" title="Edit">',
        '<i class="glyphicon glyphicon-edit"></i>Edit',
        '</a>&nbsp;&nbsp;',
        '<a class="delete" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-remove"></i>Delete',
        '</a></center>'
    ].join('');
}

window.operateEvents = {
    'click .edit': function (e, value, row, index) {
        var js = JSON.stringify(row);
        var obj = JSON.parse(js);
        var userId = obj["id"];
        $('#ufname').val(obj["firstname"]);
        $('#ulname').val(obj["lastname"]);
        $('#ucountrySelect').val(obj["country"]);
        $('#udate').val(obj["dob"]);
        $('#uusername').val(obj["username"]);
        $('#uemail').val(obj["email"]);
        $('#utel').val(obj["tel"]);
        $('#updateUserPopup').modal('show');

        $('#updateOk').off('click');
        $('#updateOk').click(function () {
            $.ajax({
                type: "POST",
                url: "update",
                data: {
                    "id": userId,
                    "ufname": $('#ufname').val(),
                    "ulname": $('#ulname').val(),
                    "ucountry": $('#ucountrySelect').val(),
                    "udate": $('#udate').val(),
                    "uemail": $('#uemail').val(),
                    "utel": $('#utel').val()
                },
                success: function (result) {
                    if ($.trim(result) == 1) {
                        $('#updateUserPopup').modal('hide');
                        $('#updateUserSuccess').modal('show');
                        $('#update').trigger('reset');
                        refresh();
                    } else {
                        $('#updateUserFail').modal('show');
                    }
                }
            });

        });
    },
    'click .delete': function (e, value, row, index) {
        var js = JSON.stringify(row);
        var obj = JSON.parse(js);
        var userId = obj["id"];
        $('#lbFname').text("First name: " + obj["firstname"]);
        $('#lbLname').text("Last name: " + obj["lastname"]);
        $('#lbCountry').text("Country: " + obj["country"]);
        $('#lbDob').text("Date of birth: " + obj["dob"]);
        $('#lbUsrname').text("User name: " + obj["username"]);
        $('#lbEmail').text("Email: " + obj["email"]);
        $('#lbTel').text("Contact no: " + obj["tel"]);
        $('#deleteUserPopup').modal('show');

        $('#deleteOk').off('click');
        $('#deleteOk').click(function () {
            $.ajax({
                type: "POST",
                url: "delete",
                data: {"id": userId},
                success: function (result) {
                    if ($.trim(result) == 1) {
                        $('#deleteUserPopup').modal('hide');
                        $('#deleteUserSuccess').modal('show');
                        refresh();
                    } else {
                        $('#deleteUserPopup').modal('hide');
                        $('#deleteUserFail').modal('show');
                    }
                }
            });
        });
    }
};

function autoFillSearch() {
    var searchName = $('#txtSearch').val();

    $.ajax({
        type: "POST",
        //dataType:"json",
        url: "search",
        // To send data we can use following format as well.
        // data: "searchUsername="+searchUsername+"&typeahead="+"1",
        data: {"searchName": searchName, "process": "typeahead"},
        success: function (result) {
            var firstNames = new Array();
            $.each(result, function (index, txtSearch) {
                firstNames.push(txtSearch.firstname);
            });
            $('#txtSearch').typeahead({source: firstNames});
        }
    });
}

function search() {
    var searchName = $('#txtSearch').val();
    $.ajax({
        type: "POST",
        url: "search",
        dataType: "json",
        data: {"searchName": searchName, "process": "search"},
        success: function (result) {
            $('#table').bootstrapTable('load', result);
        }
    });
}

function refresh() {
    $.ajax({
        type: "POST",
        url: "load",
        dataType: "json",
        success: function (result) {
            $('#table').bootstrapTable('load', result);
        }
    });
}
