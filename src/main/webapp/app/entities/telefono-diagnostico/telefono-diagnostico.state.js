(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('telefono-diagnostico', {
            parent: 'entity',
            url: '/telefono-diagnostico',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.telefonoDiagnostico.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/telefono-diagnostico/telefono-diagnosticos.html',
                    controller: 'TelefonoDiagnosticoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('telefonoDiagnostico');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('telefono-diagnostico-detail', {
            parent: 'entity',
            url: '/telefono-diagnostico/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.telefonoDiagnostico.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/telefono-diagnostico/telefono-diagnostico-detail.html',
                    controller: 'TelefonoDiagnosticoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('telefonoDiagnostico');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'TelefonoDiagnostico', function($stateParams, TelefonoDiagnostico) {
                    return TelefonoDiagnostico.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('telefono-diagnostico.new', {
            parent: 'telefono-diagnostico',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telefono-diagnostico/telefono-diagnostico-dialog.html',
                    controller: 'TelefonoDiagnosticoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                fechaCreacion: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('telefono-diagnostico', null, { reload: true });
                }, function() {
                    $state.go('telefono-diagnostico');
                });
            }]
        })
        .state('telefono-diagnostico.edit', {
            parent: 'telefono-diagnostico',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telefono-diagnostico/telefono-diagnostico-dialog.html',
                    controller: 'TelefonoDiagnosticoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TelefonoDiagnostico', function(TelefonoDiagnostico) {
                            return TelefonoDiagnostico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('telefono-diagnostico', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('telefono-diagnostico.delete', {
            parent: 'telefono-diagnostico',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telefono-diagnostico/telefono-diagnostico-delete-dialog.html',
                    controller: 'TelefonoDiagnosticoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['TelefonoDiagnostico', function(TelefonoDiagnostico) {
                            return TelefonoDiagnostico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('telefono-diagnostico', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
