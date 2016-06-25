'use strict';

describe('Controller Tests', function() {

    describe('DiagnosticoCarrier Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDiagnosticoCarrier, MockDiagnostico, MockCarrier;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDiagnosticoCarrier = jasmine.createSpy('MockDiagnosticoCarrier');
            MockDiagnostico = jasmine.createSpy('MockDiagnostico');
            MockCarrier = jasmine.createSpy('MockCarrier');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'DiagnosticoCarrier': MockDiagnosticoCarrier,
                'Diagnostico': MockDiagnostico,
                'Carrier': MockCarrier
            };
            createController = function() {
                $injector.get('$controller')("DiagnosticoCarrierDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'celSalesApp:diagnosticoCarrierUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
