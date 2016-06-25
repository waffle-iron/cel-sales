(function() {
    'use strict';
    angular
        .module('celSalesApp')
        .factory('TelefonoDiagnostico', TelefonoDiagnostico);

    TelefonoDiagnostico.$inject = ['$resource'];

    function TelefonoDiagnostico ($resource) {
        var resourceUrl =  'api/telefono-diagnosticos/:id';

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
