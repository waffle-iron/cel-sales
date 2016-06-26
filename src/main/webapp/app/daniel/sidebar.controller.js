(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('SideBarController', SideBarController);

    SideBarController.$inject = ['Principal'];

    function SideBarController (Principal) {
        var vm = this;
        vm.collapseNavbar = collapseNavbar;

        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;
        
        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }

    }
})();
