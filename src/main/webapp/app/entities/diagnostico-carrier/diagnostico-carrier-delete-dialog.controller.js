(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('DiagnosticoCarrierDeleteController',DiagnosticoCarrierDeleteController);

    DiagnosticoCarrierDeleteController.$inject = ['$uibModalInstance', 'entity', 'DiagnosticoCarrier'];

    function DiagnosticoCarrierDeleteController($uibModalInstance, entity, DiagnosticoCarrier) {
        var vm = this;

        vm.diagnosticoCarrier = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DiagnosticoCarrier.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
