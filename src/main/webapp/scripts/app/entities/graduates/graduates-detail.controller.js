'use strict';

angular.module('jwebplatformApp')
    .controller('GraduatesDetailController', function ($scope, $stateParams, Graduates, Course) {
        $scope.graduates = {};
        $scope.load = function (id) {
            Graduates.get({id: id}, function(result) {
              $scope.graduates = result;
            });
        };
        $scope.load($stateParams.id);
    });
