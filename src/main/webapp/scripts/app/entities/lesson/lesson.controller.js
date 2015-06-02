'use strict';

angular.module('javaopsApp')
    .controller('LessonController', function ($scope, Lesson, Course) {
        $scope.lessons = [];
        $scope.courses = Course.query();
        $scope.loadAll = function() {
            Lesson.query(function(result) {
               $scope.lessons = result;
            });
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
            Lesson.get({id: id}, function(result) {
                $scope.lesson = result;
                $('#saveLessonModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.lesson.id != null) {
                Lesson.update($scope.lesson,
                    function () {
                        $scope.refresh();
                    });
            } else {
                Lesson.save($scope.lesson,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
            Lesson.get({id: id}, function(result) {
                $scope.lesson = result;
                $('#deleteLessonConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Lesson.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteLessonConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#saveLessonModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.lesson = {title: null, lesson_order: null, description: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
