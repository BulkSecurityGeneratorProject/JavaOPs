'use strict';

angular.module('jwebplatformApp')
    .factory('Graduates', function ($resource) {
        return $resource('api/graduatess/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
