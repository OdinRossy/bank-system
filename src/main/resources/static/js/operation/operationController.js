function showCreateCreditTab() {
    main.showTab('#btn-create-credit-tab', ['#create-credit-tab']);
}

function showAllCreditTab() {
    main.showTab('#btn-all-credit-tab', ['#all-credit-tab']);
}

function showCreateDepositTab() {
    main.showTab('#btn-create-deposit-tab', ['#create-deposit-tab']);
}

function showAllDepositTab() {
    main.showTab('#btn-all-deposit-tab', ['#all-deposit-tab']);
}

function saveAccount(accountTypeId) {
    let currentAccount = {};
    let percentAccount = {};

    let currencyId = $('#select-currency').val();
    let value = $('#number-deposit-value').val();
    let percentage = $('#number-deposit-percent').val();
    let clientId = $('#select-client').val();

    let data = {
        accountType: {
            id: accountTypeId,
        },
        isCurrent: true,
        currency: {
            id: currencyId
        },
        value: value
    };
    operationService.saveAccount(data,
        function (response) {
            currentAccount = response;
            console.log(percentAccount);
        },
        function (response) {
            console.error(response);
            main.showErrorModal(null, response.responseJSON.message);
        });

    data = {
        accountType: {
            id: accountTypeId,
        },
        isCurrent: false,
        currency: {
            id: currencyId
        },
    };
    operationService.saveAccount(data,
        function (response) {
            percentAccount = response;
            console.log(percentAccount);
        },
        function (response) {
            console.error(response);
            main.showErrorModal(null, response.responseJSON.message);
        });

    data = {
        current_account: {
            id: currentAccount.id
        },
        percent_account: {
            id: percentAccount.id
        },
        client:{
            id: clientId
        },
        percentage: percentage
    };

    operationService.saveContract(data,
        function () {
            location = 'http://localhost:9090/bank-system/operation/deposit';
        },
        function (response) {
            console.error(response);
            main.showErrorModal(null, response.responseJSON.message);
        });
}

function deleteAccount(accountId) {
    operationService.deleteAccount(accountId,
        function (response) {
            location = location;
        },
        function (response) {
            console.error(response);
            main.showErrorModal(null, response.responseJSON.message);
        });
}
