(function() {
    'use strict';
    angular
        .module('celSalesApp')
        .factory('Diagnostico', Diagnostico);

    Diagnostico.$inject = ['$resource'];

    function Diagnostico ($resource) {
        var resourceUrl =  'api/diagnosticos/:id';

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
