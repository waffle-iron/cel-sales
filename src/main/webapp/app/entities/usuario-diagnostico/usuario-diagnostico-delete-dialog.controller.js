(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('UsuarioDiagnosticoDeleteController',UsuarioDiagnosticoDeleteController);

    UsuarioDiagnosticoDeleteController.$inject = ['$uibModalInstance', 'entity', 'UsuarioDiagnostico'];

    function UsuarioDiagnosticoDeleteController($uibModalInstance, entity, UsuarioDiagnostico) {
        var vm = this;

        vm.usuarioDiagnostico = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UsuarioDiagnostico.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
