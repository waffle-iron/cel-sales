(function() {
    'use strict';

    angular
        .module('celSalesApp')
        .controller('DiagnosticoDetailController', DiagnosticoDetailController);

    DiagnosticoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Diagnostico'];

    function DiagnosticoDetailController($scope, $rootScope, $stateParams, entity, Diagnostico) {
        var vm = this;
        vm.diagnostico = entity;
        var unsubscribe = $rootScope.$on('celSalesApp:diagnosticoUpdate', function(event, result) {
            vm.diagnostico = result;
        });
        
        $scope.almacenamiento = {
            interno:{
                total: parseInt(entity.storage.internal.total.substr(0, entity.storage.internal.total.indexOf(" "))),
                disponible: parseInt(entity.storage.internal.available.substr(0, entity.storage.internal.available.indexOf(" "))),
                ocupado: parseInt(entity.storage.internal.total.substr(0, entity.storage.internal.total.indexOf(" "))) - parseInt(entity.storage.internal.available.substr(0, entity.storage.internal.available.indexOf(" ")))
            },
            externo:{
                total: parseInt(entity.storage.external.total.substr(0, entity.storage.external.total.indexOf(" "))),
                disponible: parseInt(entity.storage.external.available.substr(0, entity.storage.external.available.indexOf(" "))),
                ocupado: parseInt(entity.storage.external.total.substr(0, entity.storage.external.total.indexOf(" "))) - parseInt(entity.storage.internal.available.substr(0, entity.storage.internal.available.indexOf(" ")))
            }
        };
                
        $scope.$on('$destroy', unsubscribe);
        

        angular.element(document).ready(function () {
           google.charts.load('current', {'packages':['corechart']});
           google.charts.setOnLoadCallback(pintarGraficasAlmacenamiento);

           function pintarGraficasAlmacenamiento() {
                //Opciones de configuracion de las graficas de alamacenamiento
                var opciones_configuracion = {
                    chartArea:{left:10,top:10,width:'90%',height:'90%'},
                    pieHole: 0.5, 
                    pieSliceTextStyle: {
                        color: 'white',
                    },
                    legend: 'none'
                };

                //GRAFICA DE ALMACENAMIENTO EXTERNO
                var data = google.visualization.arrayToDataTable([
                  ['Descripcion', 'Capacidad'],
                  ['Espacio Ocupado', $scope.almacenamiento.interno.ocupado],
                  ['Espacio Disponible', $scope.almacenamiento.interno.disponible]
                ]);


                var chartAlmacenamientoInterno = new google.visualization.PieChart(document.getElementById('chart_almacenamiento_interno'));
                chartAlmacenamientoInterno.draw(data, opciones_configuracion);

                //GRAFICA DE ALMACENAMIENTO INTERNO
                var data = google.visualization.arrayToDataTable([
                  ['Descripcion', 'Capacidad'],
                  ['Espacio Ocupado', $scope.almacenamiento.externo.ocupado],
                  ['Espacio Disponible', $scope.almacenamiento.externo.disponible]
                ]);

                var chartAlmacenamientoExterno = new google.visualization.PieChart(document.getElementById('chart_almacenamiento_externo'));
                chartAlmacenamientoExterno.draw(data, opciones_configuracion);

            }
        });
    }
})();
