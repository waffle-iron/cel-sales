(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('FabricanteDialogController', FabricanteDialogController);

    FabricanteDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Fabricante'];

    function FabricanteDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Fabricante) {
        var vm = this;

        vm.fabricante = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.fabricante.id !== null) {
                Fabricante.update(vm.fabricante, onSaveSuccess, onSaveError);
            } else {
                Fabricante.save(vm.fabricante, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('celSalesApp:fabricanteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setImagen = function ($file, fabricante) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        fabricante.imagen = base64Data;
                        fabricante.imagenContentType = $file.type;
                    });
                });
            }
        };

    }
})();
