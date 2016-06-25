(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('MensajeLogDeleteController',MensajeLogDeleteController);

    MensajeLogDeleteController.$inject = ['$uibModalInstance', 'entity', 'MensajeLog'];

    function MensajeLogDeleteController($uibModalInstance, entity, MensajeLog) {
        var vm = this;

        vm.mensajeLog = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            MensajeLog.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
