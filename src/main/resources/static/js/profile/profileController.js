function showCard(cardName, element) {
    $('#personal').css('display', 'none');
    $('#general').css('display', 'none');
    $(cardName).css('display', 'block');

    $('#btn-general').removeClass('active');
    $('#btn-personal').removeClass('active');
    $(element).addClass("active");
}