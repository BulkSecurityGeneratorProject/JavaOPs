'use strict';

angular.module('javaopsApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
