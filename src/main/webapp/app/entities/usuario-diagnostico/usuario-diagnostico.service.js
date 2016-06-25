(function() {
    'use strict';
    angular
        .module('celSalesApp')
        .factory('UsuarioDiagnostico', UsuarioDiagnostico);

    UsuarioDiagnostico.$inject = ['$resource'];

    function UsuarioDiagnostico ($resource) {
        var resourceUrl =  'api/usuario-diagnosticos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
