'use strict';

angular.module('javaopsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('lesson', {
                parent: 'entity',
                url: '/lesson',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'javaopsApp.lesson.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/lesson/lessons.html',
                        controller: 'LessonController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('lesson');
                        return $translate.refresh();
                    }]
                }
            })
            .state('lessonDetail', {
                parent: 'entity',
                url: '/lesson/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'javaopsApp.lesson.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/lesson/lesson-detail.html',
                        controller: 'LessonDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('lesson');
                        return $translate.refresh();
                    }]
                }
            });
    });
