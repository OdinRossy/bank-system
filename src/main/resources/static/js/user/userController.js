// function submitForm() {
//     const firstName = $('#firstName');
//     const lastName = $('#lastName');
//     const middleName = $('#middleName');
//     const username = $('#username');
//     const birthdate = $('#birthdate');
//     const idPassport = $('#idPassport');
//     const password = $('#password');
//     const idRole = $('#idRole');
//
//     const fields = [
//         firstName, lastName, middleName, username, birthdate, idPassport, password, idRole
//     ];
//
//     if (!userService.validate(fields)) {
//         const user = new User(
//             lastName.val(),
//             middleName.val(),
//             firstName.val(),
//             username.val(),
//             password.val(),
//             birthdate.val(),
//             idPassport.val(),
//             idRole.val()
//         );
//
//         console.info('Saving worker..');
//         console.info(user);
//
//         userService.save(user);
//     } else {
//         console.error('Some fields are empty!');
//     }
// }