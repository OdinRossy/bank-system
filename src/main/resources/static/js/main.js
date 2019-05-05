$('.scroll-top').click(function () {
    main.scrollTop();
});

const main = {
    showErrorModal: function(title, body) {
        if (title) {
            $('#error-modal-title').text(title);
        }
        $('#error-modal-body').text(body);
        $('#error-modal').modal('show');
    },

    removeSymbolRecursively: function(string, symbol){
        if (string.includes(symbol)) {
            string = string.replace(symbol, '');
            return main.removeSymbolRecursively(string, symbol);
        }
        return string;
    },
    scrollTop: function() {
        $('html, body').animate({scrollTop: $('html, body').offset().top}, 500);
    },
};
