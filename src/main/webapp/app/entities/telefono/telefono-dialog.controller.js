(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('TelefonoDialogController', TelefonoDialogController);

    TelefonoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Telefono', 'Fabricante'];

    function TelefonoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Telefono, Fabricante) {
        var vm = this;

        vm.telefono = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.fabricantes = Fabricante.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.telefono.id !== null) {
                Telefono.update(vm.telefono, onSaveSuccess, onSaveError);
            } else {
                Telefono.save(vm.telefono, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('celSalesApp:telefonoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setImagen = function ($file, telefono) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        telefono.imagen = base64Data;
                        telefono.imagenContentType = $file.type;
                    });
                });
            }
        };

    }
})();
