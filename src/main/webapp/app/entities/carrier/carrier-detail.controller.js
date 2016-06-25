(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('CarrierDetailController', CarrierDetailController);

    CarrierDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'DataUtils', 'entity', 'Carrier'];

    function CarrierDetailController($scope, $rootScope, $stateParams, DataUtils, entity, Carrier) {
        var vm = this;

        vm.carrier = entity;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('celSalesApp:carrierUpdate', function(event, result) {
            vm.carrier = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
