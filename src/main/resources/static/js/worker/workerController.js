function showGeneralTab() {
    main.showTab('#btn-general-tab', ['#general-tab']);
}

function showPersonalTab() {
    main.showTab('#btn-personal-tab', ['#personal-tab']);
}

// function showCard(activeButton, idElementsToShow) {
//
//     let cards = $('.card-info');
//
//     cards.each(function () {
//         $(this).css('display', 'none');
//     });
//
//     idElementsToShow.forEach(function (item) {
//         $(item).css('display', 'block');
//     });
//
//     $('.active').removeClass('active');
//     $(activeButton).addClass("active");
// }