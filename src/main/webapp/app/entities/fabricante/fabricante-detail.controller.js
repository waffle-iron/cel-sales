(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('FabricanteDetailController', FabricanteDetailController);

    FabricanteDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'DataUtils', 'entity', 'Fabricante'];

    function FabricanteDetailController($scope, $rootScope, $stateParams, DataUtils, entity, Fabricante) {
        var vm = this;

        vm.fabricante = entity;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('celSalesApp:fabricanteUpdate', function(event, result) {
            vm.fabricante = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
