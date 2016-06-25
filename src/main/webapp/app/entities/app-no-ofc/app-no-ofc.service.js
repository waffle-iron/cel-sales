(function() {
    'use strict';
    angular
        .module('celSalesApp')
        .factory('AppNoOfc', AppNoOfc);

    AppNoOfc.$inject = ['$resource'];

    function AppNoOfc ($resource) {
        var resourceUrl =  'api/app-no-ofcs/:id';

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
