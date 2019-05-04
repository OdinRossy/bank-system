const clientService = {

    validate: function (arr) {
        let isValid = true;
        for (const arrElement of arr) {
            if (arrElement.val() === undefined || arrElement.val().trim() === '') {
                arrElement.addClass('is-invalid');
                isValid = false;
            } else {
                arrElement.removeClass('is-invalid');
                arrElement.addClass('is-valid');
            }
        }
        return isValid;
    },

    saveLivingAddressIfNotExist: function (address) {
        $.ajax({
            url: '/bank-system/api/address/findByCountryAndCityAndStreetAndBuildingNumberAndApartmentNumberAndPostCode',
            type: 'POST',
            data: JSON.stringify(address),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function (response) {
                livingAddress = response;
            },
            error: function () {
                address.id = undefined;
                return saveLivingAddress(address);
            }
        });
    },

    saveRegistrationAddressIfNotExist: function (address) {
        $.ajax({
            url: '/bank-system/api/address/findByCountryAndCityAndStreetAndBuildingNumberAndApartmentNumberAndPostCode',
            type: 'POST',
            data: JSON.stringify(address),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function (response) {
                registrationAddress = response;
            },
            error: function () {
                address.id = undefined;
                return saveRegistrationAddress(address);
            }
        });
    },

    saveRegistrationIfNotExist: function (data) {
        $.ajax({
            url: '/bank-system/api/registration/' + data.id,
            type: 'GET',
            async: false,
            success: function (response) {
                console.log(response);
                registration = response;
            },
            error: function (response) {
                console.error(response);
                saveRegistration(data);
            }
        });
    },

    savePassportIfNotExist: function(data, elementId) {
        $.ajax({
            url: '/bank-system/api/passport/' + data.id,
            type: 'GET',
            async: false,
            success: function (response) {
                console.log(response);
                passport = response;
                isContinue = false;
                $(elementId).addClass('is-invalid');
            },
            error: function (response) {
                console.error(response);
                savePassport(data);
                $(elementId).removeClass('is-invalid');
                $(elementId).addClass('is-valid');
            }
        });
    },

    saveClient: function(data) {
        $.ajax({
            url: '/bank-system/api/client',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function (response) {
                client = response;
                isContinue = true;
            },
            error: function (response) {
                console.error(response);
                isContinue = false;
            }
        });
    }

};

let saveLivingAddress = function (address) {
    $.ajax({
        url: '/bank-system/api/address',
        type: 'POST',
        data: JSON.stringify(address),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function (response) {
            livingAddress = response;
            isContinue = true
        },
        error: function (response) {
            isContinue = false;
            console.error(response)
        }
    });
};

let saveRegistrationAddress = function (address) {
    $.ajax({
        url: '/bank-system/api/address',
        type: 'POST',
        data: JSON.stringify(address),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function (response) {
            registrationAddress = response;
            isContinue = true
        },
        error: function (response) {
            isContinue = false;
            console.error(response)
        }
    });
};

let savePassport = function(data) {
    $.ajax({
        url: '/bank-system/api/passport',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function (response) {
            passport = response;
            isContinue = true;
        },
        error: function (response) {
            console.error(response);
            isContinue = false;
            alert(response.responseJSON.message)
        }
    });
};

let saveRegistration = function (data) {
    $.ajax({
        url: '/bank-system/api/registration',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function (response) {
            console.log(response);
            registration = response;
        },
        error: function (response) {
            console.error(response);
            isContinue = false;
        }
    });
};