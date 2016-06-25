'use strict';

describe('Controller Tests', function() {

    describe('Diagnostico Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDiagnostico;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDiagnostico = jasmine.createSpy('MockDiagnostico');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Diagnostico': MockDiagnostico
            };
            createController = function() {
                $injector.get('$controller')("DiagnosticoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'celSalesApp:diagnosticoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
