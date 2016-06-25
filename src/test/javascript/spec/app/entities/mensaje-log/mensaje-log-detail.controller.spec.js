'use strict';

describe('Controller Tests', function() {

    describe('MensajeLog Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockMensajeLog, MockDiagnostico;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockMensajeLog = jasmine.createSpy('MockMensajeLog');
            MockDiagnostico = jasmine.createSpy('MockDiagnostico');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'MensajeLog': MockMensajeLog,
                'Diagnostico': MockDiagnostico
            };
            createController = function() {
                $injector.get('$controller')("MensajeLogDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'celSalesApp:mensajeLogUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
