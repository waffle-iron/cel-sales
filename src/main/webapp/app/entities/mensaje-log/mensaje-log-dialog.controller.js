(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('MensajeLogDialogController', MensajeLogDialogController);

    MensajeLogDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'MensajeLog', 'Diagnostico'];

    function MensajeLogDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, MensajeLog, Diagnostico) {
        var vm = this;

        vm.mensajeLog = entity;
        vm.clear = clear;
        vm.save = save;
        vm.diagnosticos = Diagnostico.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.mensajeLog.id !== null) {
                MensajeLog.update(vm.mensajeLog, onSaveSuccess, onSaveError);
            } else {
                MensajeLog.save(vm.mensajeLog, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('celSalesApp:mensajeLogUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
