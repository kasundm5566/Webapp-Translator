$(document).ready(function () {
    loadTableData();

    $('#txtSearch').keyup(function(){
        autoFillSearch();
    });
    $('#txtSearch').change(function(){
        autoFillSearch();
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
                showRefresh: true,
                singleSelect: true,
                minimumCountColumns: 3,
                columns: [{
                    field: 'state',
                    checkbox: true
                }, {
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
                    title: 'User name'
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
        '<i class="glyphicon glyphicon-edit"></i>',
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="delete" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a></center>'
    ].join('');
}

window.operateEvents = {
    'click .edit': function (e, value, row, index) {
        $('#updateUserPopup').modal('show');
    },
    'click .delete': function (e, value, row, index) {
        var js = JSON.stringify(row);
        var obj = JSON.parse(js);
        alert(obj["username"]);
        $('#lbFname').text("First name: " + obj["firstname"]);
        $('#lbLname').text("Last name: " + obj["lastname"]);
        $('#lbCountry').text("Country: " + obj["country"]);
        $('#lbDob').text("Date of birth: " + obj["dob"]);
        $('#lbUsrname').text("User name: " + obj["username"]);
        $('#lbEmail').text("Email: " + obj["email"]);
        $('#lbTel').text("Contact no: " + obj["tel"]);
        $('#deleteUserPopup').modal('show');
    }
};

function autoFillSearch() {
    var searchUsername=$('#txtSearch').val();

    $.ajax({
        type: "POST",
        //dataType:"json",
        url: "search",
        // To send data we can use following format as well.
        // data: "searchUsername="+searchUsername+"&typeahead="+"1",
        data: {"searchUsername":searchUsername,"process":"typeahead"},
        success: function (result) {
            var userNames = new Array();
            $.each(result, function (index, txtSearch) {
                userNames.push(txtSearch.username);
            });
            $('#txtSearch').typeahead({source: userNames});
        }
    });
}