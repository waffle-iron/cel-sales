(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('AppNoOfcDeleteController',AppNoOfcDeleteController);

    AppNoOfcDeleteController.$inject = ['$uibModalInstance', 'entity', 'AppNoOfc'];

    function AppNoOfcDeleteController($uibModalInstance, entity, AppNoOfc) {
        var vm = this;

        vm.appNoOfc = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AppNoOfc.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
