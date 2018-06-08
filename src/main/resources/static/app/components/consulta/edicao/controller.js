var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('consultaEdicaoController',
    ['$scope', '$stateParams', '$filter', 'constants', 'datePickerUtils', 'consultaEdicaoService',
        function ($scope, $stateParams, $filter, constants, datePickerUtils, consultaEdicaoService) {
            $scope.salvar = function (formularioValido) {
                if (formularioValido)
                    consultaEdicaoService.updateConsulta($stateParams.id, $scope.consulta);
            };

            $scope.$on('CONSULTA_UPDATE_SUCCESS', function () {
                operacaoSucesso();
            });

            $scope.$on('HORARIOS_DISPONIVEIS_SUCCESS', function (e, data) {
                $scope.consulta.idHorarioConsulta = '';
                $scope.horarios = data;
            });

            $scope.$watch('consulta.dataConsulta', function (novoValor) {
                if (novoValor)
                    consultaEdicaoService.getHorariosDisponiveisDoMedico($scope.consulta.idMedico, $scope.consulta.idPaciente, novoValor);
            });

            function operacaoSucesso() {
                $scope.mostrarAlertaSucesso = true;
                $scope.acaoFinalizada = true;
            }

            function configurarDatePicker() {
                var options = {
                    idElemento: '#dataConsulta',
                    model: 'consulta.dataConsulta',
                    dataMinima: new Date(),
                    fdsDisabled: true
                };

                datePickerUtils.config(options, $scope);
            }

            function inicializar() {
                configurarDatePicker();
                consultaEdicaoService.getConsulta($stateParams.id).$promise.then(
                    function (data) {
                        $scope.consulta = {
                            id: data.id,
                            idPaciente: data.paciente.id,
                            nomePaciente: data.paciente.nome,
                            idMedico: data.medico.id,
                            nomeMedico: data.medico.nome,
                            dataConsulta: $filter('date')(data.dataConsulta, 'dd/MM/yyyy'),
                            idHorarioConsulta: data.horarioConsulta.id
                        };
                        consultaEdicaoService.getHorariosDisponiveisDoMedico($scope.consulta.idMedico, $scope.consulta.idPaciente, $scope.consulta.dataConsulta);
                    }
                );
            }

            inicializar();
        }
    ]
);