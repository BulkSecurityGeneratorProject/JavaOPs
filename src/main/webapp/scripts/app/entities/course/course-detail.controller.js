'use strict';

angular.module('jwebplatformApp')
    .controller('CourseDetailController', function ($scope, $stateParams, Course, Graduates) {
        $scope.course = {};
        $scope.load = function (id) {
            Course.get({id: id}, function(result) {
              $scope.course = result;
            });
        };
        $scope.load($stateParams.id);
    });
