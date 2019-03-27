const userService = {

    user: null,

    isEmpty: function (arr) {
        let isEmpty = false;
        for (const arrElement of arr) {
            if (arrElement.val() === undefined || arrElement.val().trim() === '') {
                arrElement.addClass('is-invalid');
                isEmpty = true;
            } else {
                arrElement.removeClass('is-invalid');
                arrElement.addClass('is-valid');
            }
        }
        return isEmpty;
    },

    save: function (user) {
        $.ajax({
            url: '/bank-system/api/user',
            type: 'POST',
            data: JSON.stringify(user),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function (response) {
                user = response;
                console.info(user);
                window.open('/bank-system/user/profile', '_self');
            },
            error: function (response) {
                let message = response.responseJSON.message;
                console.error(message);

                $('#idPassport').addClass('is-invalid');
            }
        });
    }
};