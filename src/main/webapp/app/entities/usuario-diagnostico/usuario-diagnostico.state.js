(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('usuario-diagnostico', {
            parent: 'entity',
            url: '/usuario-diagnostico',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.usuarioDiagnostico.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/usuario-diagnostico/usuario-diagnosticos.html',
                    controller: 'UsuarioDiagnosticoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('usuarioDiagnostico');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('usuario-diagnostico-detail', {
            parent: 'entity',
            url: '/usuario-diagnostico/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.usuarioDiagnostico.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/usuario-diagnostico/usuario-diagnostico-detail.html',
                    controller: 'UsuarioDiagnosticoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('usuarioDiagnostico');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UsuarioDiagnostico', function($stateParams, UsuarioDiagnostico) {
                    return UsuarioDiagnostico.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('usuario-diagnostico.new', {
            parent: 'usuario-diagnostico',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/usuario-diagnostico/usuario-diagnostico-dialog.html',
                    controller: 'UsuarioDiagnosticoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                esCreador: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('usuario-diagnostico', null, { reload: true });
                }, function() {
                    $state.go('usuario-diagnostico');
                });
            }]
        })
        .state('usuario-diagnostico.edit', {
            parent: 'usuario-diagnostico',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/usuario-diagnostico/usuario-diagnostico-dialog.html',
                    controller: 'UsuarioDiagnosticoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UsuarioDiagnostico', function(UsuarioDiagnostico) {
                            return UsuarioDiagnostico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('usuario-diagnostico', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('usuario-diagnostico.delete', {
            parent: 'usuario-diagnostico',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/usuario-diagnostico/usuario-diagnostico-delete-dialog.html',
                    controller: 'UsuarioDiagnosticoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UsuarioDiagnostico', function(UsuarioDiagnostico) {
                            return UsuarioDiagnostico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('usuario-diagnostico', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
