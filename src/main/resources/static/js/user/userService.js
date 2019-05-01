const userService = {

    user: null,

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

    save: function (user) {
        $.ajax({
            url: APP.userRestService,
            type: 'POST',
            data: JSON.stringify(user),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function (response) {
                user = response;
                console.info(user);
                window.open(APP.url + '/worker', '_self');
            },
            error: function (response) {
                let message = response.responseJSON.message;
                console.error(message);

                $('#idPassport').addClass('is-invalid');
            }
        });
    }
};