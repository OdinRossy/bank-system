const clientService = {

    loadCountries : function () {
        $.ajax({
            url: '/bank-system/api/country',
            type: 'GET',
            success: function (response) {
                console.log(response);
            },
            error: function (response) {
                return response;
            }
        });
    },

};