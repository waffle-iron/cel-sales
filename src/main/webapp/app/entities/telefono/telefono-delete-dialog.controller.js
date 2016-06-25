(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('TelefonoDeleteController',TelefonoDeleteController);

    TelefonoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Telefono'];

    function TelefonoDeleteController($uibModalInstance, entity, Telefono) {
        var vm = this;

        vm.telefono = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Telefono.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
