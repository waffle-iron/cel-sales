(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('TelefonoDiagnosticoDialogController', TelefonoDiagnosticoDialogController);

    TelefonoDiagnosticoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'TelefonoDiagnostico', 'Diagnostico', 'Telefono'];

    function TelefonoDiagnosticoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, TelefonoDiagnostico, Diagnostico, Telefono) {
        var vm = this;

        vm.telefonoDiagnostico = entity;
        vm.clear = clear;
        vm.save = save;
        vm.diagnosticos = Diagnostico.query();
        vm.telefonos = Telefono.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.telefonoDiagnostico.id !== null) {
                TelefonoDiagnostico.update(vm.telefonoDiagnostico, onSaveSuccess, onSaveError);
            } else {
                TelefonoDiagnostico.save(vm.telefonoDiagnostico, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('celSalesApp:telefonoDiagnosticoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
