(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('AppNoOfcDetailController', AppNoOfcDetailController);

    AppNoOfcDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'DataUtils', 'entity', 'AppNoOfc', 'Diagnostico'];

    function AppNoOfcDetailController($scope, $rootScope, $stateParams, DataUtils, entity, AppNoOfc, Diagnostico) {
        var vm = this;

        vm.appNoOfc = entity;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('celSalesApp:appNoOfcUpdate', function(event, result) {
            vm.appNoOfc = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
