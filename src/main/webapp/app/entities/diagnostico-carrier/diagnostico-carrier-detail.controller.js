(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('DiagnosticoCarrierDetailController', DiagnosticoCarrierDetailController);

    DiagnosticoCarrierDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'DiagnosticoCarrier', 'Diagnostico', 'Carrier'];

    function DiagnosticoCarrierDetailController($scope, $rootScope, $stateParams, entity, DiagnosticoCarrier, Diagnostico, Carrier) {
        var vm = this;

        vm.diagnosticoCarrier = entity;

        var unsubscribe = $rootScope.$on('celSalesApp:diagnosticoCarrierUpdate', function(event, result) {
            vm.diagnosticoCarrier = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
