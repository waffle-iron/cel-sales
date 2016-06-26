(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('diagnostico', {
            parent: 'app',
            url: '/diagnostico',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/daniel/diagnostico/diagnostico.html',
                    controller: 'DiagnosticoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('diagnostico');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
