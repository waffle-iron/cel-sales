(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('mensaje-log', {
            parent: 'entity',
            url: '/mensaje-log?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.mensajeLog.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/mensaje-log/mensaje-logs.html',
                    controller: 'MensajeLogController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('mensajeLog');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('mensaje-log-detail', {
            parent: 'entity',
            url: '/mensaje-log/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.mensajeLog.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/mensaje-log/mensaje-log-detail.html',
                    controller: 'MensajeLogDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('mensajeLog');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'MensajeLog', function($stateParams, MensajeLog) {
                    return MensajeLog.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('mensaje-log.new', {
            parent: 'mensaje-log',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/mensaje-log/mensaje-log-dialog.html',
                    controller: 'MensajeLogDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                tipo: null,
                                contenido: null,
                                fecha: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('mensaje-log', null, { reload: true });
                }, function() {
                    $state.go('mensaje-log');
                });
            }]
        })
        .state('mensaje-log.edit', {
            parent: 'mensaje-log',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/mensaje-log/mensaje-log-dialog.html',
                    controller: 'MensajeLogDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MensajeLog', function(MensajeLog) {
                            return MensajeLog.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('mensaje-log', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('mensaje-log.delete', {
            parent: 'mensaje-log',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/mensaje-log/mensaje-log-delete-dialog.html',
                    controller: 'MensajeLogDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['MensajeLog', function(MensajeLog) {
                            return MensajeLog.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('mensaje-log', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
