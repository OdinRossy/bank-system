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

let saveGeneralInfo = function () {

    let lastName = $('#txt-last-name');
    let firstName = $('#txt-first-name');
    let middleName = $('#txt-middle-name');
    let birthDate = $('#txt-birth-date');
    let companyName = $('#txt-company-name');
    let position = $('#txt-position');
    let income = $('#txt-income-per-mouth');
    let isMale = $('input[name=gender]:checked');
    let isEmployed = true;

    const fields = [
        firstName, lastName, middleName, birthDate, companyName, position, income
    ];

    console.debug('Validating general info..');
    if (clientService.validate(fields)) {
        isGeneralInfoValid = true;

        console.log('valid.');
        passport.lastName = lastName.val();
        passport.firstName = firstName.val();
        passport.middleName = middleName.val();
        passport.birthDate = birthDate.val();
        passport.isMale = isMale.val();
        client.companyName = companyName.val();
        client.position = position.val();
        client.incomePerMonth = income.val();
        client.passport = passport;
        client.isEmployed = isEmployed;

        console.log('client: ');
        console.log(client);


        showCard('#btn-contacts', ['#contact-info'], ['#general-info', '#personal-info'])
    } else {
        console.error('Some fields not valid.');
        isGeneralInfoValid = false;
    }
};

let saveContactInfo = function () {

    let email = $('#txt-email');
    let homePhoneNumber = $('#txt-home-number');
    let mobilePhoneNumber = $('#txt-mobile-number');
    let postCode = $('#txt-living-address-post-code');
    let buildingNumber = $('#txt-living-address-building-number');
    let street = $('#txt-living-address-street');
    let city = $('#txt-living-address-city');
    let apartmentNumber = $('#txt-living-address-apartment-number');
    let countryField = $('#select-living-address-country');

    const fields = [
        email, homePhoneNumber, mobilePhoneNumber, postCode, buildingNumber, street, city, apartmentNumber, countryField
    ];

    console.debug('Validating contact info..');
    if (clientService.validate(fields)) {
        isContactInfoValid = true;

        console.log('valid.');

        livingAddress.country = {
            iso3code: countryField.val(),
        };

        livingAddress.city = city.val();
        livingAddress.street = street.val();
        livingAddress.buildingNumber = buildingNumber.val();
        livingAddress.isApartment = true;
        livingAddress.apartmentNumber = apartmentNumber.val();
        livingAddress.postCode = postCode.val();
        client.email = email.val();
        client.mobilePhoneNumber = mobilePhoneNumber.val();
        client.homePhoneNumber = homePhoneNumber.val();

        clientService.saveLivingAddressIfNotExist(livingAddress);

        console.log('livingAddress: ');
        console.log(livingAddress);

        client.address = livingAddress;

        console.log('client: ');
        console.log(client);

        if (isContactInfoValid && isContinue) {
            showCard('#btn-personal-info', ['#personal-info'], ['#general-info', '#contact-info'])
        }

    } else {
        console.error('Some fields not valid.');
        isContactInfoValid = false;
    }
};

let savePersonalInfo = function () {

    let idPassport = $('#txt-passport-id');
    let series = $('#txt-passport-series');
    let number = $('#txt-passport-number');
    let citizenship = $('#select-birthplace');
    let passportAuthority = $('#txt-passport-authority');
    let dateOfIssue = $('#date-passport-date-of-issue');
    let dateOfExpire = $('#date-passport-date-of-expire');
    let registrationCountry = $('#registration-country');
    let registrationCity = $('#txt-registration-city');
    let registrationStreet = $('#txt-registration-street');
    let registrationBuildingNumber = $('#txt-registration-building-number');
    let registrationApartmentNumber = $('#txt-registration-apartment-number');
    let registrationPostCode = $('#txt-registration-post-code');
    let registrationAuthority = $('#txt-registration-authority');
    let registrationDate = $('#date-registration-date');
    let isMarried = $('input[name=isMarried]').prop('checked');
    let isBoundToMilitaryService = $('input[name=isBoundToMilitaryService]').prop('checked');
    let isRetiree = $('input[name=isRetiree]').prop('checked');
    let isDisabled = $('input[name=isDisabled]').prop('checked');


    const fields = [idPassport, series, number, passportAuthority, dateOfIssue, dateOfExpire, registrationCountry,
        registrationCity, registrationStreet, registrationBuildingNumber, registrationApartmentNumber,
        registrationPostCode, registrationAuthority, registrationDate, citizenship
    ];

    console.debug('Validating contact info..');
    if (clientService.validate(fields)) {
        isPersonalInfoValid = true;

        console.log('valid.');

        registrationAddress.country = {
            iso3code: registrationCountry.val(),
        };

        registrationAddress.city = registrationCity.val();
        registrationAddress.street = registrationStreet.val();
        registrationAddress.buildingNumber = registrationBuildingNumber.val();
        registrationAddress.isApartment = true;
        registrationAddress.apartmentNumber = registrationApartmentNumber.val();
        registrationAddress.postCode = registrationPostCode.val();

        clientService.saveRegistrationAddressIfNotExist(registrationAddress);

        console.log('registrationAddress: ');
        console.log(registrationAddress);

        registration.address = registrationAddress;
        registration.dateOfRegistration = registrationDate.val();
        registration.registrationAuthority = registrationAuthority.val();

        clientService.saveRegistrationIfNotExist(registration);

        console.log('registration: ');
        console.log(registration);

        passport.registration = registration;

        passport.id = idPassport.val();
        passport.series = series.val();
        passport.number = number.val();
        passport.dateOfIssue = dateOfIssue.val();
        passport.dateOfExpire = dateOfExpire.val();
        passport.citizenship = {
            iso3code: citizenship.val()
        };
        passport.passportAuthority = passportAuthority.val();

        passport.isMarried = isMarried;

        clientService.savePassportIfNotExist(passport);

        console.log('passport : ');
        console.log(passport);

        client.birthplace = passport.citizenship;
        client.passport = passport;
        client.isDisabled = isDisabled;
        client.isRetiree = isRetiree;
        client.isBoundToMilitaryService = isBoundToMilitaryService;

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
    } else {
        console.error('Some fields not valid.');
        isPersonalInfoValid = false;
    }
};