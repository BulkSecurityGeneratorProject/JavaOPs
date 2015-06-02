'use strict';

angular.module('javaopsApp')
    .controller('LessonDetailController', function ($scope, $stateParams, Lesson, Course) {
        $scope.lesson = {};
        $scope.load = function (id) {
            Lesson.get({id: id}, function(result) {
              $scope.lesson = result;
            });
        };
        $scope.load($stateParams.id);
    });
