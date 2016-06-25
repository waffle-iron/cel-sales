(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('liberaciones', {
            parent: 'app',
            url: '/',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/daniel/liberaciones/liberaciones.html',
                    controller: 'LiberacionesController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('liberaciones');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
