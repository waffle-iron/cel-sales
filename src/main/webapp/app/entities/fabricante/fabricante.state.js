(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fabricante', {
            parent: 'entity',
            url: '/fabricante',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.fabricante.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fabricante/fabricantes.html',
                    controller: 'FabricanteController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fabricante');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('fabricante-detail', {
            parent: 'entity',
            url: '/fabricante/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.fabricante.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fabricante/fabricante-detail.html',
                    controller: 'FabricanteDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fabricante');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Fabricante', function($stateParams, Fabricante) {
                    return Fabricante.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('fabricante.new', {
            parent: 'fabricante',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fabricante/fabricante-dialog.html',
                    controller: 'FabricanteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nombre: null,
                                imagen: null,
                                imagenContentType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fabricante', null, { reload: true });
                }, function() {
                    $state.go('fabricante');
                });
            }]
        })
        .state('fabricante.edit', {
            parent: 'fabricante',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fabricante/fabricante-dialog.html',
                    controller: 'FabricanteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fabricante', function(Fabricante) {
                            return Fabricante.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fabricante', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fabricante.delete', {
            parent: 'fabricante',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fabricante/fabricante-delete-dialog.html',
                    controller: 'FabricanteDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Fabricante', function(Fabricante) {
                            return Fabricante.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fabricante', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
