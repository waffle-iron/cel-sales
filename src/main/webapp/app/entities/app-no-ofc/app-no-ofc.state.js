(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('app-no-ofc', {
            parent: 'entity',
            url: '/app-no-ofc',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.appNoOfc.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/app-no-ofc/app-no-ofcs.html',
                    controller: 'AppNoOfcController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('appNoOfc');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('app-no-ofc-detail', {
            parent: 'entity',
            url: '/app-no-ofc/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.appNoOfc.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/app-no-ofc/app-no-ofc-detail.html',
                    controller: 'AppNoOfcDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('appNoOfc');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'AppNoOfc', function($stateParams, AppNoOfc) {
                    return AppNoOfc.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('app-no-ofc.new', {
            parent: 'app-no-ofc',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/app-no-ofc/app-no-ofc-dialog.html',
                    controller: 'AppNoOfcDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nombre: null,
                                icono: null,
                                iconoContentType: null,
                                version: null,
                                empaquetado: null,
                                fechaInstalacion: null,
                                fechaModificacion: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('app-no-ofc', null, { reload: true });
                }, function() {
                    $state.go('app-no-ofc');
                });
            }]
        })
        .state('app-no-ofc.edit', {
            parent: 'app-no-ofc',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/app-no-ofc/app-no-ofc-dialog.html',
                    controller: 'AppNoOfcDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AppNoOfc', function(AppNoOfc) {
                            return AppNoOfc.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('app-no-ofc', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('app-no-ofc.delete', {
            parent: 'app-no-ofc',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/app-no-ofc/app-no-ofc-delete-dialog.html',
                    controller: 'AppNoOfcDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AppNoOfc', function(AppNoOfc) {
                            return AppNoOfc.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('app-no-ofc', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
