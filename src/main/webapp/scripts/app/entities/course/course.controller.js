'use strict';

angular.module('javaopsApp')
    .controller('CourseController', function ($scope, Course, Graduates) {
        $scope.courses = [];
        $scope.graduatess = Graduates.query();
        $scope.loadAll = function() {
            Course.query(function(result) {
               $scope.courses = result;
            });
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
            Course.get({id: id}, function(result) {
                $scope.course = result;
                $('#saveCourseModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.course.id != null) {
                Course.update($scope.course,
                    function () {
                        $scope.refresh();
                    });
            } else {
                Course.save($scope.course,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
            Course.get({id: id}, function(result) {
                $scope.course = result;
                $('#deleteCourseConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Course.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteCourseConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#saveCourseModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.course = {name: null, description: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
