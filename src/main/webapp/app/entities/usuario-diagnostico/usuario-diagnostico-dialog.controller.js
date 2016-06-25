(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('UsuarioDiagnosticoDialogController', UsuarioDiagnosticoDialogController);

    UsuarioDiagnosticoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UsuarioDiagnostico', 'Diagnostico', 'User'];

    function UsuarioDiagnosticoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UsuarioDiagnostico, Diagnostico, User) {
        var vm = this;

        vm.usuarioDiagnostico = entity;
        vm.clear = clear;
        vm.save = save;
        vm.diagnosticos = Diagnostico.query();
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.usuarioDiagnostico.id !== null) {
                UsuarioDiagnostico.update(vm.usuarioDiagnostico, onSaveSuccess, onSaveError);
            } else {
                UsuarioDiagnostico.save(vm.usuarioDiagnostico, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('celSalesApp:usuarioDiagnosticoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
