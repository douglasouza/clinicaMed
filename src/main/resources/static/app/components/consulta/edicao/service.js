var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('consultaEdicaoService',
    ['$rootScope', '$resource', 'consultaNovoService',
        function ($rootScope, $resource, consultaNovoService) {

            function resource() {
                return $resource('/consulta/:id', {}, {
                    'get': {method: 'GET'},
                    'update': {method: 'PUT'}
                });
            }

            this.getConsulta = function (id) {
                return resource().get({id: id});
            };

            this.updateConsulta = function (id, consulta) {
                return resource().update({id: id}, consulta).$promise.then(
                    function () {
                        $rootScope.$broadcast('CONSULTA_UPDATE_SUCCESS');
                    }
                );
            };

            this.getHorariosDisponiveisDoMedico = function (idMedico, idPaciente, data) {
                consultaNovoService.getHorariosDisponiveisDoMedico(idMedico, idPaciente, data);
            };
        }
    ]
);