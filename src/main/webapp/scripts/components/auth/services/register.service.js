'use strict';

angular.module('javaopsApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


