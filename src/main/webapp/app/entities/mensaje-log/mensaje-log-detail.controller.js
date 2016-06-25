(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('MensajeLogDetailController', MensajeLogDetailController);

    MensajeLogDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'MensajeLog', 'Diagnostico'];

    function MensajeLogDetailController($scope, $rootScope, $stateParams, entity, MensajeLog, Diagnostico) {
        var vm = this;

        vm.mensajeLog = entity;

        var unsubscribe = $rootScope.$on('celSalesApp:mensajeLogUpdate', function(event, result) {
            vm.mensajeLog = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
