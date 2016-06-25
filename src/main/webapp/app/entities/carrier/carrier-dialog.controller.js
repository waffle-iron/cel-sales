(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('CarrierDialogController', CarrierDialogController);

    CarrierDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Carrier'];

    function CarrierDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Carrier) {
        var vm = this;

        vm.carrier = entity;
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
            if (vm.carrier.id !== null) {
                Carrier.update(vm.carrier, onSaveSuccess, onSaveError);
            } else {
                Carrier.save(vm.carrier, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('celSalesApp:carrierUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setLogo = function ($file, carrier) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        carrier.logo = base64Data;
                        carrier.logoContentType = $file.type;
                    });
                });
            }
        };

    }
})();
