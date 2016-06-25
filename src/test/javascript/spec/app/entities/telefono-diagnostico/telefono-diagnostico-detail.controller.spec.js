'use strict';

describe('Controller Tests', function() {

    describe('TelefonoDiagnostico Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockTelefonoDiagnostico, MockDiagnostico, MockTelefono;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockTelefonoDiagnostico = jasmine.createSpy('MockTelefonoDiagnostico');
            MockDiagnostico = jasmine.createSpy('MockDiagnostico');
            MockTelefono = jasmine.createSpy('MockTelefono');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'TelefonoDiagnostico': MockTelefonoDiagnostico,
                'Diagnostico': MockDiagnostico,
                'Telefono': MockTelefono
            };
            createController = function() {
                $injector.get('$controller')("TelefonoDiagnosticoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'celSalesApp:telefonoDiagnosticoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
