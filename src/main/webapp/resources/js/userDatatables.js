var ajaxUrl = "ajax/admin/users/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
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
                "asc"
            ]
        ]
    });
    makeEditable();
    $(".checkbox").click(function () {
        changeEnabled($(this).closest('tr').attr("id"), $(this).is(":checked"))
    });
});

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

function changeEnabled(id, enabled) {
    $.ajax({
        type: "POST",
        url: ajaxUrl + id,
        data: "enabled=" + enabled,
        success: function () {
            updateTable();
            successNoty("User was changed!");
        }
    });
}