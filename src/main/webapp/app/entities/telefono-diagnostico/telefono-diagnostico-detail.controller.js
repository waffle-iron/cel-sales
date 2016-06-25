(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('TelefonoDiagnosticoDetailController', TelefonoDiagnosticoDetailController);

    TelefonoDiagnosticoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'TelefonoDiagnostico', 'Diagnostico', 'Telefono'];

    function TelefonoDiagnosticoDetailController($scope, $rootScope, $stateParams, entity, TelefonoDiagnostico, Diagnostico, Telefono) {
        var vm = this;

        vm.telefonoDiagnostico = entity;

        var unsubscribe = $rootScope.$on('celSalesApp:telefonoDiagnosticoUpdate', function(event, result) {
            vm.telefonoDiagnostico = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
