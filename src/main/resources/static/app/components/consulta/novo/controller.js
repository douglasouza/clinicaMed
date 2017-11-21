var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('consultaNovoController',
    ['$scope', 'constants', 'datePickerUtils', 'consultaNovoService',
        function ($scope, constants, datePickerUtils, consultaNovoService) {
            $scope.salvar = function (formularioValido) {
                if (formularioValido)
                    consultaNovoService.saveConsulta($scope.consulta);
            };

            $scope.$on('CONSULTA_SAVE_SUCCESS', function () {
                operacaoSucesso();
            });

            $scope.$on('PACIENTES_FETCHED_SUCCESS', function (e, data) {
                $scope.pacientes = data;
            });

            $scope.$on('MEDICOS_FETCHED_SUCCESS', function (e, data) {
                $scope.medicos = data;

                if ($scope.medicos.length == 0)
                    $scope.consulta.idMedico = undefined;
            });

            $scope.$on('HORARIOS_DISPONIVEIS_SUCCESS', function (e, data) {
                $scope.consulta.idHorarioConsulta = '';
                $scope.horarios = data;
            });

            $scope.$watch('especialidade', function (novoValor) {
                if (novoValor)
                    consultaNovoService.getMedicosPorEspecialidade(novoValor);
                else
                    $scope.consulta.idMedico = '';
            });

            $scope.$watch('consulta.idMedico', function (novoValor) {
                if (novoValor && $scope.consulta.dataConsulta) {
                    consultaNovoService.getHorariosDisponiveisDoMedico($scope.consulta.idMedico, novoValor);
                } else {
                    $scope.consulta.dataConsulta = '';
                    $scope.consulta.idHorarioConsulta = '';
                }
            });

            $scope.$watch('consulta.dataConsulta', function (novoValor) {
                if (novoValor)
                    consultaNovoService.getHorariosDisponiveisDoMedico($scope.consulta.idMedico, novoValor);
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
                $scope.consulta = {};
                $scope.especialidades = constants.ENUM.ESPECIALIDADE_MEDICA;
                consultaNovoService.getPacientes();
            }

            inicializar();
        }
    ]
);