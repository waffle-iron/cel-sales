(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('UsuarioDiagnosticoDetailController', UsuarioDiagnosticoDetailController);

    UsuarioDiagnosticoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'UsuarioDiagnostico', 'Diagnostico', 'User'];

    function UsuarioDiagnosticoDetailController($scope, $rootScope, $stateParams, entity, UsuarioDiagnostico, Diagnostico, User) {
        var vm = this;

        vm.usuarioDiagnostico = entity;

        var unsubscribe = $rootScope.$on('celSalesApp:usuarioDiagnosticoUpdate', function(event, result) {
            vm.usuarioDiagnostico = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
