var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('consultaEdicaoController',
    ['$scope', '$state', '$stateParams', '$filter', 'jQuery', 'constants', 'datePickerUtils', 'consultaEdicaoService',
        function ($scope, $state, $stateParams, $filter, $, constants, datePickerUtils, consultaEdicaoService) {
            $scope.salvar = function (formularioValido) {
                if (!formularioValido)
                    return;

                if ($scope.acao === 'NOVO') {
                    consultaEdicaoService.saveConsulta($scope.consulta);
                } else {
                    consultaEdicaoService.updateConsulta($stateParams.id, $scope.consulta);
                }
            };

            $scope.$on('CONSULTA_SAVE_SUCCESS', function () {
                operacaoSucesso();
            });

            $scope.$on('CONSULTA_UPDATE_SUCCESS', function () {
                operacaoSucesso();
            });

            $scope.$on('PACIENTES_FETCHED_SUCCESS', function (e, data) {
                $scope.pacientes = data;
            });

            $scope.$on('MEDICOS_FETCHED_SUCCESS', function (e, data) {
                $scope.medicos = data;
            });

            $scope.$on('HORARIOS_DISPONIVEIS_SUCCESS', function (e, data) {
                $scope.consulta.idHorarioConsulta = '';
                $scope.horarios = data;
            });

            $scope.$watch('especialidade', function (novoValor) {
                if (novoValor)
                    consultaEdicaoService.getMedicosPorEspecialidade(novoValor);
                else
                    $scope.consulta.idMedico = '';
            });

            $scope.$watch('consulta.idMedico', function (novoValor) {
                if (novoValor && $scope.consulta.dataConsulta)
                    consultaEdicaoService.getHorariosDisponiveisDoMedico($scope.consulta.idMedico, novoValor);
            });

            $scope.$watch('consulta.dataConsulta', function (novoValor) {
                if (novoValor)
                    consultaEdicaoService.getHorariosDisponiveisDoMedico($scope.consulta.idMedico, novoValor);
            });

            function operacaoSucesso() {
                $scope.mostrarAlertaSucesso = true;
                $scope.acaoFinalizada = true;
            }

            function configurarDatePicker() {
                var options = {
                    elemento: $('#dataConsulta'),
                    model: 'consulta.dataConsulta',
                    dataMinima: new Date(),
                    fdsDisabled: true
                };

                datePickerUtils.config(options, $scope, $filter);
            }

            function inicializar() {
                configurarDatePicker();
                $scope.consulta = {};
                $scope.especialidades = constants.ENUM.ESPECIALIDADE_MEDICA;
                $scope.acao = $state.current.name === 'consulta.novo' ? 'NOVO' : 'EDICAO';
                if ($scope.acao === 'EDICAO') {
                    consultaEdicaoService.getConsulta($stateParams.id).$promise.then(
                        function (data) {
                            $scope.consulta = data;
                        }
                    );
                } else {
                    consultaEdicaoService.getPacientes();
                }
            }

            inicializar();
        }
    ]
);