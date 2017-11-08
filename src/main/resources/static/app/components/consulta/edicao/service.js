var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('consultaEdicaoService',
    ['$rootScope', '$resource', 'pacienteListagemService', 'medicoListagemService',
        function ($rootScope, $resource, pacienteListagemService, medicoListagemService) {

            function resource() {
                return $resource('/consulta/:id', {}, {
                    'get': {method: 'GET'},
                    'save': {method: 'POST'},
                    'update': {method: 'PUT'}
                });
            }

            this.getConsulta = function (id) {
                return resource().get({id: id});
            };

            this.saveConsulta = function (consulta) {
                return resource().save(consulta).$promise.then(
                    function () {
                        $rootScope.$broadcast('CONSULTA_SAVE_SUCCESS');
                    }
                );
            };

            this.updateConsulta = function (id, consulta) {
                return resource().update({id: id}, consulta).$promise.then(
                    function () {
                        $rootScope.$broadcast('CONSULTA_UPDATE_SUCCESS');
                    }
                );
            };

            this.getPacientes = function () {
                pacienteListagemService.fetchAll();
            };

            this.getMedicosPorEspecialidade = function (especialidade) {
                medicoListagemService.fetchAll({especialidade: especialidade});
            };

            this.getHorariosDisponiveisDoMedico = function (idMedico, data) {
                var resource = $resource('/medico/:id/horariosDisponiveis/',
                    {data: converterDataParaPadraoAnoMesDia(data)},
                    {'get': {method: 'GET', isArray: true}}
                );

                resource.get({id: idMedico}).$promise.then(
                    function (data) {
                        $rootScope.$broadcast('HORARIOS_DISPONIVEIS_SUCCESS', data);
                    }
                );
            };

            function converterDataParaPadraoAnoMesDia(data) {
                var dataArray = data.split('/');
                return new Date(dataArray[2] + '/' + dataArray[1] + '/' + dataArray[0]).toUTCString();
            }
        }
    ]
);