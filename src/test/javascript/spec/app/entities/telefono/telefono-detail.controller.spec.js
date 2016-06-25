'use strict';

describe('Controller Tests', function() {

    describe('Telefono Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockTelefono, MockFabricante;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockTelefono = jasmine.createSpy('MockTelefono');
            MockFabricante = jasmine.createSpy('MockFabricante');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Telefono': MockTelefono,
                'Fabricante': MockFabricante
            };
            createController = function() {
                $injector.get('$controller')("TelefonoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'celSalesApp:telefonoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
