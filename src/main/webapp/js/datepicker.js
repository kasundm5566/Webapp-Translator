var date_input = $('input[name="date"]');
var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
var options = {
    format: 'yyyy-mm-dd ',
    container: container,
    todayHighlight: true,
    autoclose: true,
    weekStart: 1,
    orientation: "top left",
    daysOfWeekHighlighted: "0,6",
    todayBtn: true
};
date_input.datepicker(options);

