var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

$(document).ready(function () {
    datatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                'desc'
            ]
        ]
    });
    makeEditable();
});

var isFiltered = function () {
    var inputs = $('#filterForm').find(":input");
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i] !== "") {
            return true;
        }
    }
    return false;
}

function updateTable() {
    if (isFiltered()) {
        filter()
    } else {
        $.get(ajaxUrl, function (data) {
            datatableApi.clear().rows.add(data).draw();
        });
    }
}

function filter() {
    $.ajax({
        type: "GET",
        url: ajaxUrl + "filter",
        data: $('#filterForm').serialize(),
        success: function (data) {
            $("#editRow").modal("hide");
            datatableApi.clear().rows.add(data).draw();
            successNoty("Filtered");
        }
    });
}