(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('TelefonoDetailController', TelefonoDetailController);

    TelefonoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'DataUtils', 'entity', 'Telefono', 'Fabricante'];

    function TelefonoDetailController($scope, $rootScope, $stateParams, DataUtils, entity, Telefono, Fabricante) {
        var vm = this;

        vm.telefono = entity;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('celSalesApp:telefonoUpdate', function(event, result) {
            vm.telefono = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
