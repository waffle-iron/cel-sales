(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('diagnostico-carrier', {
            parent: 'entity',
            url: '/diagnostico-carrier',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.diagnosticoCarrier.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/diagnostico-carrier/diagnostico-carriers.html',
                    controller: 'DiagnosticoCarrierController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('diagnosticoCarrier');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('diagnostico-carrier-detail', {
            parent: 'entity',
            url: '/diagnostico-carrier/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.diagnosticoCarrier.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/diagnostico-carrier/diagnostico-carrier-detail.html',
                    controller: 'DiagnosticoCarrierDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('diagnosticoCarrier');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DiagnosticoCarrier', function($stateParams, DiagnosticoCarrier) {
                    return DiagnosticoCarrier.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('diagnostico-carrier.new', {
            parent: 'diagnostico-carrier',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diagnostico-carrier/diagnostico-carrier-dialog.html',
                    controller: 'DiagnosticoCarrierDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                esOrigen: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('diagnostico-carrier', null, { reload: true });
                }, function() {
                    $state.go('diagnostico-carrier');
                });
            }]
        })
        .state('diagnostico-carrier.edit', {
            parent: 'diagnostico-carrier',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diagnostico-carrier/diagnostico-carrier-dialog.html',
                    controller: 'DiagnosticoCarrierDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DiagnosticoCarrier', function(DiagnosticoCarrier) {
                            return DiagnosticoCarrier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('diagnostico-carrier', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('diagnostico-carrier.delete', {
            parent: 'diagnostico-carrier',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diagnostico-carrier/diagnostico-carrier-delete-dialog.html',
                    controller: 'DiagnosticoCarrierDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DiagnosticoCarrier', function(DiagnosticoCarrier) {
                            return DiagnosticoCarrier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('diagnostico-carrier', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
