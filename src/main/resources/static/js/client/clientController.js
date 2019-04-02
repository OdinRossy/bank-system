let findClientById = function () {
    let enteredId = $('#idPassport');
    if (enteredId.val().trim() === '' || enteredId.val() === null) {
        showAllRows();
    } else {
        hideAllRows();
        if ($('#' + enteredId.val()) !== null && $('#' + enteredId.val()) !== undefined) {
            $('#' + enteredId.val()).css('display', 'table-row');
        }
    }
};

let hideAllRows = function () {
    $('tr').css('display', 'none')
};

let showAllRows = function () {
    $('tr').css('display', 'table-row')
};