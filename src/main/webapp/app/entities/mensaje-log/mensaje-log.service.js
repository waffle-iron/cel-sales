(function() {
    'use strict';
    angular
        .module('celSalesApp')
        .factory('MensajeLog', MensajeLog);

    MensajeLog.$inject = ['$resource'];

    function MensajeLog ($resource) {
        var resourceUrl =  'api/mensaje-logs/:id';

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
