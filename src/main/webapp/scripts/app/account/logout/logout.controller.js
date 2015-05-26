'use strict';

angular.module('jwebplatformApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
