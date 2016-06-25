(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('UsuarioDiagnosticoController', UsuarioDiagnosticoController);

    UsuarioDiagnosticoController.$inject = ['$scope', '$state', 'UsuarioDiagnostico'];

    function UsuarioDiagnosticoController ($scope, $state, UsuarioDiagnostico) {
        var vm = this;
        
        vm.usuarioDiagnosticos = [];

        loadAll();

        function loadAll() {
            UsuarioDiagnostico.query(function(result) {
                vm.usuarioDiagnosticos = result;
            });
        }
    }
})();
