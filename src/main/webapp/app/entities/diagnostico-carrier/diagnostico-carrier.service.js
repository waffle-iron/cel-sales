(function() {
    'use strict';
    angular
        .module('celSalesApp')
        .factory('DiagnosticoCarrier', DiagnosticoCarrier);

    DiagnosticoCarrier.$inject = ['$resource'];

    function DiagnosticoCarrier ($resource) {
        var resourceUrl =  'api/diagnostico-carriers/:id';

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
