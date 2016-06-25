(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('DiagnosticoCarrierDialogController', DiagnosticoCarrierDialogController);

    DiagnosticoCarrierDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DiagnosticoCarrier', 'Diagnostico', 'Carrier'];

    function DiagnosticoCarrierDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DiagnosticoCarrier, Diagnostico, Carrier) {
        var vm = this;

        vm.diagnosticoCarrier = entity;
        vm.clear = clear;
        vm.save = save;
        vm.diagnosticos = Diagnostico.query();
        vm.carriers = Carrier.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.diagnosticoCarrier.id !== null) {
                DiagnosticoCarrier.update(vm.diagnosticoCarrier, onSaveSuccess, onSaveError);
            } else {
                DiagnosticoCarrier.save(vm.diagnosticoCarrier, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('celSalesApp:diagnosticoCarrierUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
