'use strict';

angular.module('jwebplatformApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


