$(function () {

});
let isContinue = true;
let passport = {};
let client = {};
let livingAddress = {};
let registrationAddress = {};
let registration = {};

let isGeneralInfoValid = false;
let isContactInfoValid = false;
let isPersonalInfoValid = false;

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
let isMarriedInput = $('input[name=isMarried]').prop('checked');
let isBoundToMilitaryServiceInput = $('input[name=isBoundToMilitaryService]').prop('checked');
let isRetireeInput = $('input[name=isRetiree]').prop('checked');
let isDisabledInput = $('input[name=isDisabled]').prop('checked');


function showGeneralInfo() {
    showCard('#btn-general-info', ['#general-info'], ['#personal-info', '#contact-info']);
}

function showContactInfo() {
    showCard('#btn-contacts', ['#contact-info'], ['#general-info', '#personal-info']);
}

function showPersonalInfo() {
    showCard('#btn-personal-info', ['#personal-info'], ['#general-info', '#contact-info']);
}

let showCard = function (activeButton, showElems, hideElems) {

    hideElems.forEach(function (item) {
        $(item).css('display', 'none');
    });

    showElems.forEach(function (item) {
        $(item).css('display', 'block');
    });

    $('.active').removeClass('active');
    $(activeButton).addClass("active");
};

let openClient = function (id) {
    let clientId = $('#idPassport').val();

    if (id !== null && id !== undefined) {
        clientId = id;
    }

    window.open('/bank-system/client/' + clientId, '_self');

};

let findClientById = function () {
    let enteredId = $('#idPassport');
    if (enteredId.val().trim() === '' || enteredId.val() === null) {
        showAllRows();
    } else {
        hideAllRows();
        if ($('#' + enteredId.val()) !== null && $('#' + enteredId.val()) !== undefined) {
            $('#' + enteredId.val()).css('display', 'table-row');
        }
    }
};

let hideAllRows = function () {
    $('tbody tr').css('display', 'none');
};

let showAllRows = function () {
    $('tbody tr').css('display', 'table-row');
};

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
    console.log('Validating general info.. ' + isGeneralInfoValid);

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

    isContactInfoValid = clientService.validate(fields);
    console.log('Validating contact info.. ' + isContactInfoValid);
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

    isPersonalInfoValid = clientService.validate(fields);
    console.log('Validating contact info.. ' + isPersonalInfoValid);

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
    if (isContactInfoValid) {

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

        passport.isMarried = isMarriedInput;

        clientService.savePassportIfNotExist(passport);

        console.log('passport : ');
        console.log(passport);

        client.birthplace = passport.citizenship;
        client.passport = passport;
        client.isDisabled = isDisabledInput;
        client.isRetiree = isRetireeInput;
        client.isBoundToMilitaryService = isBoundToMilitaryServiceInput;

    } else {
        console.error('Some fields not valid.');
        isPersonalInfoValid = false;
    }
}

// Save client
function saveClient() {

    if (isContinue && isGeneralInfoValid && isContactInfoValid && isPersonalInfoValid) {
        console.log('Ready to save client');
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
    if (isContinue && isGeneralInfoValid && isContactInfoValid && isPersonalInfoValid) {
        console.log('Ready to update client');
        // clientService.saveClient(client);
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
