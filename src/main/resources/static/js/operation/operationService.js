const operationService = {

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

    saveContract: function (data, successCallback, errorCallback) {
        $.ajax({
            url: '/bank-system/api/contract',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: successCallback,
            error: errorCallback,
        });
    },
};