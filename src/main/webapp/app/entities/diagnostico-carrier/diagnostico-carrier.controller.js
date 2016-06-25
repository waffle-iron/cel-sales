(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('DiagnosticoCarrierController', DiagnosticoCarrierController);

    DiagnosticoCarrierController.$inject = ['$scope', '$state', 'DiagnosticoCarrier'];

    function DiagnosticoCarrierController ($scope, $state, DiagnosticoCarrier) {
        var vm = this;
        
        vm.diagnosticoCarriers = [];

        loadAll();

        function loadAll() {
            DiagnosticoCarrier.query(function(result) {
                vm.diagnosticoCarriers = result;
            });
        }
    }
})();
