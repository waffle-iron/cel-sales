(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('AppNoOfcDialogController', AppNoOfcDialogController);

    AppNoOfcDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'AppNoOfc', 'Diagnostico'];

    function AppNoOfcDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, AppNoOfc, Diagnostico) {
        var vm = this;

        vm.appNoOfc = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
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
            if (vm.appNoOfc.id !== null) {
                AppNoOfc.update(vm.appNoOfc, onSaveSuccess, onSaveError);
            } else {
                AppNoOfc.save(vm.appNoOfc, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('celSalesApp:appNoOfcUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setIcono = function ($file, appNoOfc) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        appNoOfc.icono = base64Data;
                        appNoOfc.iconoContentType = $file.type;
                    });
                });
            }
        };

    }
})();
