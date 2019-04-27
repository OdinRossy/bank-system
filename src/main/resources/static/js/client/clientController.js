$(function () {
    loadCountries();
});

let countries;

let getLinkFindClient = function() {
    let enteredId = $('#idPassport');
    if (enteredId.val().trim() !== '' || enteredId.val() !== null) {
        enteredId.removeClass('notValid');
        window.open('/bank-system/client/' + enteredId.val(), '_self');
    } else {
        enteredId.addClass('notValid');
    }
};

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
    $('tbody tr').css('display', 'none');
};

let showAllRows = function () {
    $('tbody tr').css('display', 'table-row');
};

function loadCountries() {
    $.ajax({
        url: '/bank-system/api/country',
        type: 'GET',
        success: function (response) {
            countries = response;
            console.log(countries);
            countries.forEach(function (item) {
                console.log(item.countryName);
            });

        },
        error: function (response) {
            console.error(response);
        }
    });
}

function showCard(activeButton, showElems, hideElems) {

    hideElems.forEach(function (item) {
        $(item).css('display', 'none');
    });

    showElems.forEach(function (item) {
        $(item).css('display', 'block');
    });

    $('.card-header a').removeClass('active');
    $(activeButton).addClass("active");
}