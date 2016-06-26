(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('DiagnosticoController', DiagnosticoController);

    DiagnosticoController.$inject = ['$scope', '$state', 'Diagnostico', 'ParseLinks', 'AlertService'];

    function DiagnosticoController ($scope, $state, Diagnostico, ParseLinks, AlertService) {
        var vm = this;
        
        vm.diagnosticos = [];
        vm.loadPage = loadPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'id';
        vm.reset = reset;
        vm.reverse = true;

        loadAll();

        function loadAll () {
            Diagnostico.query({
                page: vm.page,
                size: 20,
                sort: sort()
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                    vm.diagnosticos.push(data[i]);
                }
                console.log(data);
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function reset () {
            vm.page = 0;
            vm.diagnosticos = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }
    }
})();
