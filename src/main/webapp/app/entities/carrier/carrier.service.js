(function() {
    'use strict';
    angular
        .module('celSalesApp')
        .factory('Carrier', Carrier);

    Carrier.$inject = ['$resource'];

    function Carrier ($resource) {
        var resourceUrl =  'api/carriers/:id';

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
