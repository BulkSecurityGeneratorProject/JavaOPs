'use strict';

angular.module('javaopsApp')
    .controller('GraduatesController', function ($scope, Graduates, Course) {
        $scope.graduatess = [];
        $scope.courses = Course.query();
        $scope.loadAll = function() {
            Graduates.query(function(result) {
               $scope.graduatess = result;
            });
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
            Graduates.get({id: id}, function(result) {
                $scope.graduates = result;
                $('#saveGraduatesModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.graduates.id != null) {
                Graduates.update($scope.graduates,
                    function () {
                        $scope.refresh();
                    });
            } else {
                Graduates.save($scope.graduates,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
            Graduates.get({id: id}, function(result) {
                $scope.graduates = result;
                $('#deleteGraduatesConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Graduates.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteGraduatesConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#saveGraduatesModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.graduates = {graduates_order: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
