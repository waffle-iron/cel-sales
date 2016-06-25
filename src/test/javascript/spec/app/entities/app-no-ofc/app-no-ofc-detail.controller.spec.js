'use strict';

describe('Controller Tests', function() {

    describe('AppNoOfc Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockAppNoOfc, MockDiagnostico;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockAppNoOfc = jasmine.createSpy('MockAppNoOfc');
            MockDiagnostico = jasmine.createSpy('MockDiagnostico');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'AppNoOfc': MockAppNoOfc,
                'Diagnostico': MockDiagnostico
            };
            createController = function() {
                $injector.get('$controller')("AppNoOfcDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'celSalesApp:appNoOfcUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
