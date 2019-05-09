// Validation
let passport = {};
let client = {};
let registration = {};

// General info inputs
let lastNameInput = $('#txt-last-name');
let firstNameInput = $('#txt-first-name');
let middleNameInput = $('#txt-middle-name');
let birthDateInput = $('#txt-birth-date');
let companyNameInput = $('#txt-company-name');
let positionInput = $('#txt-position');
let incomeInput = $('#txt-income-per-mouth');
let isMaleInput = $('input[name=gender]');
let isEmployedInput = true;

// Contact info inputs
let emailInput = $('#txt-email');
let homePhoneNumberInput = $('#txt-home-number');
let mobilePhoneNumberInput = $('#txt-mobile-number');
let livingPostCodeInput = $('#txt-living-address-post-code');
let livingBuildingNumberInput = $('#txt-living-address-building-number');
let livingStreetInput = $('#txt-living-address-street');
let livingCityInput = $('#txt-living-address-city');
let livingApartmentNumberInput = $('#txt-living-address-apartment-number');
let livingCountryFieldInput = $('#select-living-address-country');

// Personal info inputs
let passportIdInput = $('#txt-passport-id');
let passportSeriesInput = $('#txt-passport-series');
let passportNumberInput = $('#txt-passport-number');
let citizenshipInput = $('#select-birthplace');
let passportAuthorityInput = $('#txt-passport-authority');
let passportDateOfIssueInput = $('#date-passport-date-of-issue');
let passportDateOfExpireInput = $('#date-passport-date-of-expire');
let registrationCountryInput = $('#registration-country');
let registrationCityInput = $('#txt-registration-city');
let registrationStreetInput = $('#txt-registration-street');
let registrationBuildingNumberInput = $('#txt-registration-building-number');
let registrationApartmentNumberInput = $('#txt-registration-apartment-number');
let registrationPostCodeInput = $('#txt-registration-post-code');
let registrationAuthorityInput = $('#txt-registration-authority');
let registrationDateInput = $('#date-registration-date');
let isMarriedInput = $('input[name=isMarried]');
let isBoundToMilitaryServiceInput = $('input[name=isBoundToMilitaryService]');
let isRetireeInput = $('input[name=isRetiree]');
let isDisabledInput = $('input[name=isDisabled]');


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


function openClient(id) {
    let clientId = $('#idPassport').val();

    if (id !== null && id !== undefined) {
        clientId = id;
    }

    window.open('/bank-system/client/' + clientId, '_self');

}


function findClientById() {
    let enteredId = $('#idPassport');

    if (enteredId.val().trim().length === 0) {
        showAllRows();
    } else {
        hideAllRows();
        const elementId = '#' + enteredId.val();
        if ($(elementId) !== null && $(elementId) !== undefined) {
            $(elementId).css('display', 'table-row');
        }
    }
}


function hideAllRows() {
    $('tbody tr').css('display', 'none');
}


function showAllRows() {
    $('tbody tr').css('display', 'table-row');
}

// validate passport id
passportIdInput.blur(validatePassportId);

// validate passport series
passportSeriesInput.blur(validatePassportSeries);

// validate passport number
passportNumberInput.blur(validatePassportNumber);

// validate email
emailInput.blur(validateEmail);

// validate mobile phone number
mobilePhoneNumberInput.blur(validateMobilePhoneNumber);

// validate home phone number
homePhoneNumberInput.blur(validateHomePhoneNumber);


function validatePassport() {
    return validatePassportId() && validatePassportSeries() && validatePassportNumber();
}


$('.numbers-to-validate').blur(function () {
    let elements = $('.numbers-to-validate');
    elements.each(function () {
        $(this).val(clientService.removeSpecialSymbolsFromPhoneNumber($(this).val()))
    })
});


// Validate
function validateEmail() {
    let isEmailValid = false;
    if (clientService.validate([emailInput])) {
        let isEmailLengthValid = emailInput.val().trim().length > 3;
        let isEmailContainsSpecialSymbol = emailInput.val().includes('@');
        let data = {email: emailInput.val()};
        let isSuccess = false;

        clientService.checkIsEmailValid(data,
            function (response) {
                isSuccess = true;
                console.log(response);
            },
            function (response) {
                isSuccess = false;
                main.showErrorModal(null, response.responseJSON.message);
                console.error(response);
            }
        );

        isEmailValid = isEmailLengthValid && isEmailContainsSpecialSymbol && isSuccess;
    }
    isEmailValid ? emailInput.addClass('is-valid') : emailInput.addClass('is-invalid');

    console.log('Validating email: ' + isEmailValid);
    return isEmailValid;
}

function validateMobilePhoneNumber() {
    let isMobilePhoneNumberValid = false;
    if (clientService.validate([mobilePhoneNumberInput])) {
        let value = clientService.removeSpecialSymbolsFromPhoneNumber(mobilePhoneNumberInput.val());
        console.log('MobilePhoneNumber after replace: ' + value);

        mobilePhoneNumberInput.val(value);

        let isMobilePhoneNumberLengthValid = value.length >= 7;
        let data = {mobilePhoneNumber: value};
        let isSuccess = false;

        clientService.checkIsMobilePhoneNumberValid(data,
            function (response) {
                isSuccess = true;
                console.log(response);
            },
            function (response) {
                isSuccess = false;
                console.error(response);
                main.showErrorModal(null, response.responseJSON.message);
            }
        );

        isMobilePhoneNumberValid = isMobilePhoneNumberLengthValid && isSuccess;
    }
    isMobilePhoneNumberValid ? mobilePhoneNumberInput.addClass('is-valid') : mobilePhoneNumberInput.addClass('is-invalid');

    console.log('Validating mobilePhoneNumber: ' + isMobilePhoneNumberValid);
    return isMobilePhoneNumberValid;
}

function validateHomePhoneNumber() {
    let value = clientService.removeSpecialSymbolsFromPhoneNumber(homePhoneNumberInput.val());
    homePhoneNumberInput.val(value);
    return true;
}

function validatePassportId() {
    let isPassportIdValid = false;
    if (clientService.validate([passportIdInput])) {

        let isPassportIdLengthValid = passportIdInput.val().trim().length === 14;
        let data = {passportId: passportIdInput.val()};
        let isSuccess = false;

        clientService.checkIsPassportIdValid(data,
            function (response) {
                isSuccess = true;
                console.log(response);
            },
            function (response) {
                isSuccess = false;
                console.error(response);
                main.showErrorModal(null, response.responseJSON.message);
            }
        );
        isPassportIdValid = isPassportIdLengthValid && isSuccess;
    }

    isPassportIdValid ? passportIdInput.addClass('is-valid') : passportIdInput.addClass('is-invalid');

    console.log('Validating passport id: ' + isPassportIdValid);
    return isPassportIdValid;
}

function validatePassportSeries() {
    let isPassportSeriesValid = false;
    if (clientService.validate([passportSeriesInput])) {

        let isPassportSeriesLengthValid = passportSeriesInput.val().trim().length === 2;

        isPassportSeriesValid = isPassportSeriesLengthValid;

    } else {
        isPassportSeriesValid = false;
    }
    isPassportSeriesValid ? passportSeriesInput.addClass('is-valid') : passportSeriesInput.addClass('is-invalid');

    console.log('Validating passport series: ' + isPassportSeriesValid);
    return isPassportSeriesValid;
}

function validatePassportNumber() {
    let isPassportNumberValid = false;

    if (clientService.validate([passportNumberInput])) {
        let isPassportNumberLengthValid = passportNumberInput.val().trim().length === 7;
        let data = {passportNumber: passportNumberInput.val()};
        let isSuccess = false;

        clientService.checkIsPassportNumberValid(data,
            function (response) {
                isSuccess = true;
                console.log(response);
            },
            function (response) {
                isSuccess = false;
                console.error(response);
                main.showErrorModal(null, response.responseJSON.message);
            }
        );

        isPassportNumberValid = isPassportNumberLengthValid && isSuccess;
    }
    isPassportNumberValid ? passportNumberInput.addClass('is-valid') : passportNumberInput.addClass('is-invalid');

    console.log('Validating passport number: ' + isPassportNumberValid);
    return isPassportNumberValid;
}


// validate info
function validateGeneralInfo() {
    const fields = [
        firstNameInput,
        lastNameInput,
        middleNameInput,
        birthDateInput,
        companyNameInput,
        positionInput,
        incomeInput
    ];

    let isGeneralInfoValid = clientService.validate(fields);
    console.log('Validating general info: ' + isGeneralInfoValid);
    return isGeneralInfoValid;

}

function validateContactInfo() {
    const fields = [
        homePhoneNumberInput,
        livingPostCodeInput,
        livingBuildingNumberInput,
        livingStreetInput,
        livingCityInput,
        livingApartmentNumberInput,
        livingCountryFieldInput
    ];
    let isMobilePhoneNumberValid = validateMobilePhoneNumber();
    let isEmailValid = validateEmail();
    let isContactInfoValid = clientService.validate(fields) && isMobilePhoneNumberValid && isEmailValid;
    console.log('Validating contact info: ' + isContactInfoValid);
    return isContactInfoValid;
}

function validatePersonalInfo() {
    const fields = [
        passportAuthorityInput,
        passportDateOfIssueInput,
        passportDateOfExpireInput,
        registrationCountryInput,
        registrationCityInput,
        registrationStreetInput,
        registrationBuildingNumberInput,
        registrationApartmentNumberInput,
        registrationPostCodeInput,
        registrationAuthorityInput,
        registrationDateInput,
        citizenshipInput
    ];

    let isPersonalInfoValid = validatePassport() && clientService.validate(fields);
    console.log('Validating personal info: ' + isPersonalInfoValid);
    return isPersonalInfoValid;
}


// Save info
function saveGeneralInfo() {
    if (validateGeneralInfo()) {
        passport.lastName = lastNameInput.val();
        passport.firstName = firstNameInput.val();
        passport.middleName = middleNameInput.val();
        passport.birthDate = birthDateInput.val();
        passport.isMale = isMaleInput.prop('checked');
        client.companyName = companyNameInput.val();
        client.position = positionInput.val();
        client.incomePerMonth = incomeInput.val();
        client.isEmployed = isEmployedInput;
        client.passport = passport;

        showContactInfo();

    } else {
        console.error('General info not valid.')
    }
}

function saveContactInfo() {
    if (validateContactInfo()) {

        let livingAddress = {
            country: {
                iso3code: livingCountryFieldInput.val(),
            },
            city: livingCityInput.val(),
            street: livingStreetInput.val(),
            buildingNumber: livingBuildingNumberInput.val(),
            isApartment: true,
            apartmentNumber: livingApartmentNumberInput.val(),
            postCode: livingPostCodeInput.val(),
        };

        client.email = emailInput.val();
        client.mobilePhoneNumber = mobilePhoneNumberInput.val();
        client.homePhoneNumber = homePhoneNumberInput.val();

        // save living address
        livingAddress = saveAddressIfNotExists(livingAddress);
        console.log('livingAddress: ');
        console.log(livingAddress);

        client.address = livingAddress;

        showPersonalInfo();

    } else {
        console.error('Contact info not valid.');
    }
}

function savePersonalInfo() {
    if (validatePersonalInfo()) {

        let registrationAddress = {
            country: {
                iso3code: registrationCountryInput.val(),
            },
            city: registrationCityInput.val(),
            street: registrationStreetInput.val(),
            buildingNumber: registrationBuildingNumberInput.val(),
            isApartment: true,
            apartmentNumber: registrationApartmentNumberInput.val(),
            postCode: registrationPostCodeInput.val(),
        };

        // save registration address
        registrationAddress = saveAddressIfNotExists(registrationAddress);
        console.log('registrationAddress: ');
        console.log(registrationAddress);

        registration.address = registrationAddress;
        registration.dateOfRegistration = registrationDateInput.val();
        registration.registrationAuthority = registrationAuthorityInput.val();


        // save registration
        registration = saveRegistrationIfNotExists(registration);
        console.log('registration: ');
        console.log(registration);

        passport.registration = registration;

        passport.id = passportIdInput.val();
        passport.series = passportSeriesInput.val();
        passport.number = passportNumberInput.val();
        passport.dateOfIssue = passportDateOfIssueInput.val();
        passport.dateOfExpire = passportDateOfExpireInput.val();

        passport.citizenship = {
            iso3code: citizenshipInput.val()
        };
        passport.passportAuthority = passportAuthorityInput.val();

        passport.isMarried = isMarriedInput.prop('checked');

        clientService.savePassport(passport,
            function (response) {
                passport = response;
            },
            function (response) {
                console.error(response);
                main.showErrorModal(null, response.responseJSON.message);
            });

        console.log('passport : ');
        console.log(passport);

        client.birthplace = passport.citizenship;
        client.passport = passport;
        client.isDisabled = isDisabledInput.prop('checked');
        client.isRetiree = isRetireeInput.prop('checked');
        client.isBoundToMilitaryService = isBoundToMilitaryServiceInput.prop('checked');

    } else {
        console.error('Personal info not valid.');
    }
}


// Save client
function saveClient() {
    console.log('Saving client..');

    clientService.saveClient(client,
        function (response) {
            client = response;
            window.open('/bank-system/client/' + client.id, '_self');
        },
        function (response) {
            console.error(response);
            main.showErrorModal(null, response.responseJSON.message);
        });
    console.log(client);
}


// Update client
function updateClient() {
    console.log('Updating client..');
    clientService.updateClient(client,
        function (response) {
            client = response;
            window.open('/bank-system/client/' + client.id, '_self');
        },
        function (response) {
            console.error(response);
            main.showErrorModal(null, response.responseJSON.message);
        });
    console.log(client);
}


// Delete client
function deleteClient(id) {
    if (id > 0) {
        console.log('Delete client: ' + id);
        clientService.deleteClient(id,
            function () {
                window.open('/bank-system/client', '_self');
            },
            function () {
                console.error(response);
                main.showErrorModal(null, response.responseJSON.message);
            });
    }
}

function saveAddressIfNotExists(address) {
    clientService.getAddress(address,
        function (response) {
            address = response;
        },
        function () {
            clientService.saveAddress(address,
                function (response) {
                    address = response;
                },
                function (response) {
                    console.error(response);
                    main.showErrorModal(null, response.responseJSON.message);
                    address = null;
                });
        });
    return address;
}

function saveRegistrationIfNotExists(registration) {
    clientService.getRegistration(registration,
        function (response) {
            console.log(response);
            registration = response;
        },
        function () {
            clientService.saveRegistration(registration,
                function (response) {
                    console.log(response);
                    registration = response;
                },
                function (response) {
                    console.error(response);
                    main.showErrorModal(null, response.responseJSON.message);
                    registration = null;
                });
        });
    return registration;
}

function saveAccount(clientId) {
    let currencyId = $('#select-currency').val();
    let data = {
        client:{
            id:clientId
        },
        currency:{
            id: currencyId
        },
        value: 0
    };
    clientService.saveAccount(data,
        function (response) {
            location = location;
        },
        function (response) {
            console.error(response);
            main.showErrorModal(null, response.responseJSON.message);
        });
}

function deleteAccount(accountId) {
    clientService.deleteAccount(accountId,
        function (response) {
            location = location;
        },
        function (response) {
            console.error(response);
            main.showErrorModal(null, response.responseJSON.message);
        });
}