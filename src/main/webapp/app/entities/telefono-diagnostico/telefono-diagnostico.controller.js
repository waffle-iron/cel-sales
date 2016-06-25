(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('TelefonoDiagnosticoController', TelefonoDiagnosticoController);

    TelefonoDiagnosticoController.$inject = ['$scope', '$state', 'TelefonoDiagnostico'];

    function TelefonoDiagnosticoController ($scope, $state, TelefonoDiagnostico) {
        var vm = this;
        
        vm.telefonoDiagnosticos = [];

        loadAll();

        function loadAll() {
            TelefonoDiagnostico.query(function(result) {
                vm.telefonoDiagnosticos = result;
            });
        }
    }
})();
