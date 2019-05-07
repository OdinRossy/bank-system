function showGeneralInfo() {
    showCard('#btn-general-info', ['#general-info']);
}

function showContactInfo() {
    showCard('#btn-contacts', ['#contact-info']);
}

function showPersonalInfo() {
    showCard('#btn-personal-info', ['#personal-info']);
}

function showClientAccounts() {
    showCard('#btn-client-accounts', ['#client-accounts']);
}

function showCard(activeButton, idElementsToShow) {

    let cards = $('.card-info');

    cards.each(function () {
        $(this).css('display', 'none');
    });

    idElementsToShow.forEach(function (item) {
        $(item).css('display', 'block');
    });

    $('.active').removeClass('active');
    $(activeButton).addClass("active");
}