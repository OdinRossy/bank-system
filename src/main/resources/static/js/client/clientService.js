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

    getAddress: function(data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/address/findByCountryAndCityAndStreetAndBuildingNumberAndApartmentNumberAndPostCode',
            type: 'GET',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    saveAddress: function(data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/address',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: successCallback,
            error: errorCallback
        });
    },

    getRegistration: function(data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/registration/' + data.id,
            type: 'GET',
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    saveRegistration: function(data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/registration',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    savePassport: function(data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/passport',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    checkIsEmailValid: function (data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/client/checkIsEmailValid',
            type: 'POST',
            data: {
                'email' : data.email
            },
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    checkIsMobilePhoneNumberValid: function (data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/client/checkIsMobilePhoneNumberValid',
            type: 'POST',
            data: {
                'mobilePhoneNumber' : data.mobilePhoneNumber
            },
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    checkIsPassportIdValid: function (data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/passport/checkIsPassportIdValid',
            type: 'POST',
            data: {
                'passportId' : data.passportId
            },
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    checkIsPassportNumberValid: function (data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/passport/checkIsPassportNumberValid',
            type: 'POST',
            data: {
                'passportNumber' : data.passportNumber
            },
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    removeSpecialSymbolsFromPhoneNumber: function(phoneNumber) {
        let value = phoneNumber.trim();

        value = main.removeSymbolRecursively(value, '+');
        value = main.removeSymbolRecursively(value, '-');
        value = main.removeSymbolRecursively(value, ' ');

        return value;
    },

    getClient: function(data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/client/' + data.id,
            type: 'GET',
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    saveClient: function(data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/client',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    updateClient: function(data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/client/' + client.id,
            type: 'PUT',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    deleteClient: function(id, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/client/' + id,
            type: 'DELETE',
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    saveAccount: function (data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/account',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

    deleteAccount: function(id, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/account/' + id,
            type: 'DELETE',
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },

};

