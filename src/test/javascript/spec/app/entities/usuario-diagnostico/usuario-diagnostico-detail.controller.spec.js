'use strict';

describe('Controller Tests', function() {

    describe('UsuarioDiagnostico Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockUsuarioDiagnostico, MockDiagnostico, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockUsuarioDiagnostico = jasmine.createSpy('MockUsuarioDiagnostico');
            MockDiagnostico = jasmine.createSpy('MockDiagnostico');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'UsuarioDiagnostico': MockUsuarioDiagnostico,
                'Diagnostico': MockDiagnostico,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("UsuarioDiagnosticoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'celSalesApp:usuarioDiagnosticoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
