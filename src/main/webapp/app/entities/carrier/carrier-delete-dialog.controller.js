(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('CarrierDeleteController',CarrierDeleteController);

    CarrierDeleteController.$inject = ['$uibModalInstance', 'entity', 'Carrier'];

    function CarrierDeleteController($uibModalInstance, entity, Carrier) {
        var vm = this;

        vm.carrier = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Carrier.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
