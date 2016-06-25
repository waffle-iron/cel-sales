(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('DiagnosticoDetailController', DiagnosticoDetailController);

    DiagnosticoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Diagnostico'];

    function DiagnosticoDetailController($scope, $rootScope, $stateParams, entity, Diagnostico) {
        var vm = this;

        vm.diagnostico = entity;

        var unsubscribe = $rootScope.$on('celSalesApp:diagnosticoUpdate', function(event, result) {
            vm.diagnostico = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
