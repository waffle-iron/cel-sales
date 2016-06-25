(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('telefono', {
            parent: 'entity',
            url: '/telefono',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.telefono.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/telefono/telefonos.html',
                    controller: 'TelefonoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('telefono');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('telefono-detail', {
            parent: 'entity',
            url: '/telefono/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.telefono.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/telefono/telefono-detail.html',
                    controller: 'TelefonoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('telefono');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Telefono', function($stateParams, Telefono) {
                    return Telefono.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('telefono.new', {
            parent: 'telefono',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telefono/telefono-dialog.html',
                    controller: 'TelefonoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                marca: null,
                                nombre: null,
                                modelo: null,
                                imagen: null,
                                imagenContentType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('telefono', null, { reload: true });
                }, function() {
                    $state.go('telefono');
                });
            }]
        })
        .state('telefono.edit', {
            parent: 'telefono',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telefono/telefono-dialog.html',
                    controller: 'TelefonoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Telefono', function(Telefono) {
                            return Telefono.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('telefono', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('telefono.delete', {
            parent: 'telefono',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telefono/telefono-delete-dialog.html',
                    controller: 'TelefonoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Telefono', function(Telefono) {
                            return Telefono.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('telefono', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
