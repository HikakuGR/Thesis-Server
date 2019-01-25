$(document).ready(function () {
    update();
});

function update() {
    setTimeout(update, 2000);
        $.ajax({
        type: "GET",
        url: constants.Root+"Home/AssignmentsList",      
        success: function (data) {
            $('#assignmentsList').html(data)
        }
        
        });
        $.ajax({
            type: "GET",
            url: constants.Root + "Home/ApprovedAssignments",
            success: function (data) {
                $('#approvedAssignments').html(data)
            }

        });
}

function onMapRefresh() {
    $.ajax({
        type: "GET",
        url: constants.Root + "Home/Map",
        success: function (data) {
            $('#mapContainer').html(data);
        }

    });
}
function assignmentAction(status,assignmentID) {
    $.ajax({
        type: "POST",
        url: constants.Root + "Home/AssignmentAction",
        data: {
            approved: status,
            assignmentID: assignmentID
        },
        success: function (data) {

        }
    });
    
}