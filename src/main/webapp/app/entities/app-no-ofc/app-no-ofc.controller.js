(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('AppNoOfcController', AppNoOfcController);

    AppNoOfcController.$inject = ['$scope', '$state', 'DataUtils', 'AppNoOfc'];

    function AppNoOfcController ($scope, $state, DataUtils, AppNoOfc) {
        var vm = this;
        
        vm.appNoOfcs = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            AppNoOfc.query(function(result) {
                vm.appNoOfcs = result;
            });
        }
    }
})();
