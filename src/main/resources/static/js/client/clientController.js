$(function () {

});

// Validation
let isContinue = true;

let passport = {};
let client = {};
let livingAddress = {};
let registrationAddress = {};
let registration = {};

let isGeneralInfoValid = false;
let isContactInfoValid = false;
let isPersonalInfoValid = false;

let isPassportIdValid = false;
let isPassportSeriesValid = false;
let isPassportNumberValid = false;
let isEmailValid = false;
let isMobilePhoneNumberValid = false;

// General info
let lastNameInput = $('#txt-last-name');
let firstNameInput = $('#txt-first-name');
let middleNameInput = $('#txt-middle-name');
let birthDateInput = $('#txt-birth-date');
let companyNameInput = $('#txt-company-name');
let positionInput = $('#txt-position');
let incomeInput = $('#txt-income-per-mouth');
let isMaleInput = $('input[name=gender]:checked');
let isEmployedInput = true;

// Contact info
let emailInput = $('#txt-email');
let homePhoneNumberInput = $('#txt-home-number');
let mobilePhoneNumberInput = $('#txt-mobile-number');
let livingPostCodeInput = $('#txt-living-address-post-code');
let livingBuildingNumberInput = $('#txt-living-address-building-number');
let livingStreetInput = $('#txt-living-address-street');
let livingCityInput = $('#txt-living-address-city');
let livingApartmentNumberInput = $('#txt-living-address-apartment-number');
let livingCountryFieldInput = $('#select-living-address-country');

// Personal info
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
    showCard('#btn-general-info', ['#general-info'], ['#personal-info', '#contact-info']);
}

function showContactInfo() {
    showCard('#btn-contacts', ['#contact-info'], ['#general-info', '#personal-info']);
}

function showPersonalInfo() {
    showCard('#btn-personal-info', ['#personal-info'], ['#general-info', '#contact-info']);
}

function showCard(activeButton, idElementsToShow, idElementsToHide) {

    idElementsToHide.forEach(function (item) {
        $(item).css('display', 'none');
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

// Validate
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

    isGeneralInfoValid = clientService.validate(fields);
    console.log('Validating general info: ' + isGeneralInfoValid);

}

function validateContactInfo() {
    const fields = [
        emailInput,
        homePhoneNumberInput,
        mobilePhoneNumberInput,
        livingPostCodeInput,
        livingBuildingNumberInput,
        livingStreetInput,
        livingCityInput,
        livingApartmentNumberInput,
        livingCountryFieldInput
    ];

    isContactInfoValid = isEmailValid && isMobilePhoneNumberValid && clientService.validate(fields);
    console.log('Validating contact info: ' + isContactInfoValid);
}

function validatePersonalInfo() {
    const fields = [
        passportIdInput,
        passportSeriesInput,
        passportNumberInput,
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

    isPersonalInfoValid = isPassportValid() && clientService.validate(fields);
    console.log('Validating personal info: ' + isPersonalInfoValid);

}

passportIdInput.blur(function validatePassportId() {

    if (clientService.validate([passportIdInput])) {

        let isPassportIdLengthValid = passportIdInput.val().trim().length === 14;
        let isPassportIdUniq = clientService.checkIsPassportIdValid({passportId: passportIdInput.val()});

        isPassportIdValid = isPassportIdLengthValid && isPassportIdUniq;

    } else {
        isPassportIdValid = false;
    }

    isPassportIdValid ? passportIdInput.addClass('is-valid') : passportIdInput.addClass('is-invalid');

    console.log('Validating passport id: ' + isPassportIdValid);
});

passportSeriesInput.blur(function validatePassportSeries() {

    if (clientService.validate([passportSeriesInput])) {

        let isPassportSeriesLengthValid = passportSeriesInput.val().trim().length === 2;

        isPassportSeriesValid = isPassportSeriesLengthValid;

    } else {
        isPassportSeriesValid = false;
    }

    isPassportSeriesValid ? passportSeriesInput.addClass('is-valid') : passportSeriesInput.addClass('is-invalid');

    console.log('Validating passport series: ' + isPassportSeriesValid);
});

passportNumberInput.blur(function validatePassportNumber() {

    if (clientService.validate([passportNumberInput])) {
        let isPassportNumberLengthValid = passportNumberInput.val().trim().length === 7;
        let isPassportNumberUniq = clientService.checkIsPassportNumberValid({passportNumber: passportNumberInput.val()});

        isPassportNumberValid = isPassportNumberLengthValid && isPassportNumberUniq;

    } else {
        isPassportNumberValid = false;
    }

    isPassportNumberValid ? passportNumberInput.addClass('is-valid') : passportNumberInput.addClass('is-invalid');

    console.log('Validating passport number: ' + isPassportNumberValid);
});

emailInput.blur(function validateEmail() {

    if (clientService.validate([emailInput])) {
        let isEmailLengthValid = emailInput.val().trim().length > 3;
        let isEmailContainsSpecialSymbol = emailInput.val().includes('@');
        let isEmailUniq = clientService.checkIsEmailValid({email: emailInput.val()});

        isEmailValid = isEmailLengthValid && isEmailContainsSpecialSymbol && isEmailUniq;

    } else {
        isEmailValid = false;
    }

    isEmailValid ? emailInput.addClass('is-valid') : emailInput.addClass('is-invalid');

    console.log('Validating email: ' + isEmailValid);
});

mobilePhoneNumberInput.blur(function validateMobilePhoneNumber() {

    if (clientService.validate([mobilePhoneNumberInput])) {
        let value = clientService.removeSpecialSymbolsFromPhoneNumber(mobilePhoneNumberInput.val());
        console.log('MobilePhoneNumber after replace: ' + value);

        mobilePhoneNumberInput.val(value);

        let isMobilePhoneNumberLengthValid = value.length >= 7;
        let isMobilePhoneNumberUniq = clientService.checkIsMobilePhoneNumberValid({mobilePhoneNumber: value});

        isMobilePhoneNumberValid = isMobilePhoneNumberLengthValid && isMobilePhoneNumberUniq;

    } else {
        isMobilePhoneNumberValid = false;
    }

    isMobilePhoneNumberValid ? mobilePhoneNumberInput.addClass('is-valid') : mobilePhoneNumberInput.addClass('is-invalid');

    console.log('Validating mobilePhoneNumber: ' + isMobilePhoneNumberValid);
});

homePhoneNumberInput.blur(function () {

    let value = clientService.removeSpecialSymbolsFromPhoneNumber(homePhoneNumberInput.val());
    homePhoneNumberInput.val(value);

});

$('.numbers-to-validate').blur(function () {
    let elements = $('.numbers-to-validate');
    elements.each(function () {
        $(this).val(clientService.removeSpecialSymbolsFromPhoneNumber($(this).val()))
    })
});

function isPassportValid() {
    return isPassportIdValid && isPassportSeriesValid && isPassportNumberValid;
}

// Save info
function saveGeneralInfo() {
    if (isGeneralInfoValid) {
        passport.lastName = lastNameInput.val();
        passport.firstName = firstNameInput.val();
        passport.middleName = middleNameInput.val();
        passport.birthDate = birthDateInput.val();
        passport.isMale = isMaleInput.val();
        client.companyName = companyNameInput.val();
        client.position = positionInput.val();
        client.incomePerMonth = incomeInput.val();
        client.isEmployed = isEmployedInput;
        client.passport = passport;

        console.log('client: ');
        console.log(client);

        if (isContinue) {
            showContactInfo();
        }

    } else {
        console.error('General info not valid.')
    }
}

function saveContactInfo() {
    if (isEmailValid && isContactInfoValid) {

        livingAddress.country = {
            iso3code: livingCountryFieldInput.val(),
        };

        livingAddress.city = livingCityInput.val();
        livingAddress.street = livingStreetInput.val();
        livingAddress.buildingNumber = livingBuildingNumberInput.val();
        livingAddress.isApartment = true;
        livingAddress.apartmentNumber = livingApartmentNumberInput.val();
        livingAddress.postCode = livingPostCodeInput.val();
        client.email = emailInput.val();
        client.mobilePhoneNumber = mobilePhoneNumberInput.val();
        client.homePhoneNumber = homePhoneNumberInput.val();

        clientService.saveLivingAddressIfNotExist(livingAddress);

        console.log('livingAddress: ');
        console.log(livingAddress);

        client.address = livingAddress;

        console.log('client: ');
        console.log(client);

        if (isContinue) {
            showPersonalInfo();
        }

    } else {
        console.error('Contact info not valid.');
        isContactInfoValid = false;
    }
}

function savePersonalInfo() {
    if (isPersonalInfoValid) {

        registrationAddress.country = {
            iso3code: registrationCountryInput.val(),
        };

        registrationAddress.city = registrationCityInput.val();
        registrationAddress.street = registrationStreetInput.val();
        registrationAddress.buildingNumber = registrationBuildingNumberInput.val();
        registrationAddress.isApartment = true;
        registrationAddress.apartmentNumber = registrationApartmentNumberInput.val();
        registrationAddress.postCode = registrationPostCodeInput.val();

        clientService.saveRegistrationAddressIfNotExist(registrationAddress);

        console.log('registrationAddress: ');
        console.log(registrationAddress);

        registration.address = registrationAddress;
        registration.dateOfRegistration = registrationDateInput.val();
        registration.registrationAuthority = registrationAuthorityInput.val();

        clientService.saveRegistrationIfNotExist(registration);

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

        clientService.savePassport(passport);

        console.log('passport : ');
        console.log(passport);

        client.birthplace = passport.citizenship;
        client.passport = passport;
        client.isDisabled = isDisabledInput.prop('checked');
        client.isRetiree = isRetireeInput.prop('checked');
        client.isBoundToMilitaryService = isBoundToMilitaryServiceInput.prop('checked');

    } else {
        console.error('Personal info not valid.');
        isPersonalInfoValid = false;
    }
}

function isReadyToSaveOrUpdateClient() {
    return isContinue && isGeneralInfoValid && isContactInfoValid && isPersonalInfoValid
}

// Save client
function saveClient() {

    validateGeneralInfo();
    validateContactInfo();
    validatePersonalInfo();

    if (isReadyToSaveOrUpdateClient()) {
        console.log('Saving client..');
        clientService.saveClient(client);
        console.log(client);
        window.open('/bank-system/client/' + client.id, '_self');

    } else {
        console.error('isContinue: ' + isContinue);
        console.error('isGeneralInfoValid: ' + isGeneralInfoValid);
        console.error('isContactInfoValid: ' + isContactInfoValid);
        console.error('isPersonalInfoValid: ' + isPersonalInfoValid);
        console.error('client: ');
        console.error(client);
    }
}

function updateClient() {
    if (isReadyToSaveOrUpdateClient()) {
        console.log('Updating client..');
        // clientService.updateClient(client);
        console.log(client);
        // window.open('/bank-system/client/' + client.id, '_self');

    } else {
        console.error('isContinue: ' + isContinue);
        console.error('isGeneralInfoValid: ' + isGeneralInfoValid);
        console.error('isContactInfoValid: ' + isContactInfoValid);
        console.error('isPersonalInfoValid: ' + isPersonalInfoValid);
        console.error('client: ');
        console.error(client);
    }
}
