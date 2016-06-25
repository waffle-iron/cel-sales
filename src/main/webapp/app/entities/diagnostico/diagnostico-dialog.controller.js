(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('DiagnosticoDialogController', DiagnosticoDialogController);

    DiagnosticoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Diagnostico'];

    function DiagnosticoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Diagnostico) {
        var vm = this;

        vm.diagnostico = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.diagnostico.id !== null) {
                Diagnostico.update(vm.diagnostico, onSaveSuccess, onSaveError);
            } else {
                Diagnostico.save(vm.diagnostico, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('celSalesApp:diagnosticoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
