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

    savePassport: function(data) {
        $.ajax({
            url: '/bank-system/api/passport',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function (response) {
                passport = response;
            },
            error: function (response) {
                console.error(response);
                isContinue = false;
                alert(response.responseJSON.message)
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
                alert('Client saved.')
            },
            error: function (response) {
                console.error(response);
                isContinue = false;
            }
        });
    },

    checkIsEmailValid: function (data) {
        let isSuccess = false;
        $.ajax({
            url: '/bank-system/api/client/checkIsEmailValid',
            type: 'POST',
            data: {
                'email' : data.email
            },
            async: false,
            success: function (response) {
                isSuccess = true;
                console.log(response);

            },
            error: function (response) {
                isSuccess = false;
                console.error(response);
                alert(response.responseJSON.message);
            }
        });
        return isSuccess;
    },

    checkIsMobilePhoneNumberValid: function (data) {
        let isSuccess = false;
        $.ajax({
            url: '/bank-system/api/client/checkIsMobilePhoneNumberValid',
            type: 'POST',
            data: {
                'mobilePhoneNumber' : data.mobilePhoneNumber
            },
            async: false,
            success: function (response) {
                isSuccess = true;
                console.log(response);

            },
            error: function (response) {
                isSuccess = false;
                console.error(response);
                alert(response.responseJSON.message);
            }
        });
        return isSuccess;
    },

    checkIsPassportIdValid: function (data) {
        let isSuccess = false;
        $.ajax({
            url: '/bank-system/api/passport/checkIsPassportIdValid',
            type: 'POST',
            data: {
                'passportId' : data.passportId
            },
            async: false,
            success: function (response) {
                isSuccess = true;
                console.log(response);

            },
            error: function (response) {
                isSuccess = false;
                console.error(response);
                alert(response.responseJSON.message);
            }
        });
        return isSuccess;
    },

    checkIsPassportNumberValid: function (data) {
        let isSuccess = false;
        $.ajax({
            url: '/bank-system/api/passport/checkIsPassportNumberValid',
            type: 'POST',
            data: {
                'passportNumber' : data.passportNumber
            },
            async: false,
            success: function (response) {
                isSuccess = true;
                console.log(response);

            },
            error: function (response) {
                isSuccess = false;
                console.error(response);
                alert(response.responseJSON.message);
            }
        });
        return isSuccess;
    },

    removeSpecialSymbolsFromPhoneNumber: function(phoneNumber) {
        let value = phoneNumber.trim();

        value = removeSymbolRecursively(value, '+');
        value = removeSymbolRecursively(value, '-');
        value = removeSymbolRecursively(value, ' ');

        return value;
    }

};

let removeSymbolRecursively = function(string, symbol){
    if (string.includes(symbol)) {
        string = string.replace(symbol, '');
        return removeSymbolRecursively(string, symbol);
    }
    return string;
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

