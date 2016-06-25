(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('diagnostico', {
            parent: 'entity',
            url: '/diagnostico',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.diagnostico.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/diagnostico/diagnosticos.html',
                    controller: 'DiagnosticoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('diagnostico');
                    $translatePartialLoader.addPart('estadoBateria');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('diagnostico-detail', {
            parent: 'entity',
            url: '/diagnostico/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.diagnostico.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/diagnostico/diagnostico-detail.html',
                    controller: 'DiagnosticoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('diagnostico');
                    $translatePartialLoader.addPart('estadoBateria');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Diagnostico', function($stateParams, Diagnostico) {
                    return Diagnostico.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('diagnostico.new', {
            parent: 'diagnostico',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diagnostico/diagnostico-dialog.html',
                    controller: 'DiagnosticoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                imei: null,
                                serial: null,
                                tempBateria: null,
                                tecBateria: null,
                                voltBateria: null,
                                capBateria: null,
                                fuenteEnergia: null,
                                versionSO: null,
                                estadoBateria: null,
                                almInternoTotal: null,
                                almInternoDisp: null,
                                almExternoTotal: null,
                                almExternoDisp: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('diagnostico', null, { reload: true });
                }, function() {
                    $state.go('diagnostico');
                });
            }]
        })
        .state('diagnostico.edit', {
            parent: 'diagnostico',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diagnostico/diagnostico-dialog.html',
                    controller: 'DiagnosticoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Diagnostico', function(Diagnostico) {
                            return Diagnostico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('diagnostico', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('diagnostico.delete', {
            parent: 'diagnostico',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diagnostico/diagnostico-delete-dialog.html',
                    controller: 'DiagnosticoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Diagnostico', function(Diagnostico) {
                            return Diagnostico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('diagnostico', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
