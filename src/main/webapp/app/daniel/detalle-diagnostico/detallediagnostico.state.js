(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('diagnostico-detail', {
            parent: 'entity',
            url: '/diagnostico/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'celSalesApp.diagnostico.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/daniel/detalle-diagnostico/detallediagnostico.html',
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
        });
    }
})();
