(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('TelefonoDiagnosticoDeleteController',TelefonoDiagnosticoDeleteController);

    TelefonoDiagnosticoDeleteController.$inject = ['$uibModalInstance', 'entity', 'TelefonoDiagnostico'];

    function TelefonoDiagnosticoDeleteController($uibModalInstance, entity, TelefonoDiagnostico) {
        var vm = this;

        vm.telefonoDiagnostico = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            TelefonoDiagnostico.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
