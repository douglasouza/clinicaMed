var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('consultaEdicaoController',
    ['$scope', '$state', '$stateParams', '$filter', 'jQuery', 'datePickerUtils', 'consultaEdicaoService',
        function ($scope, $state, $stateParams, $filter, $, datePickerUtils, consultaEdicaoService) {
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

            function operacaoSucesso() {
                $scope.mostrarAlertaSucesso = true;
                $scope.acaoFinalizada = true;
            }

            function configurarDatePicker() {
                var options = {
                    elemento: $('#dataHoraConsulta'),
                    model: 'consulta.dataHoraConsulta',
                    dataMinima: new Date(),
                    fdsDisabled: true
                };

                datePickerUtils.config(options, $scope, $filter);
            }

            function inicializar() {
                configurarDatePicker();
                $scope.consulta = {};
                $scope.acao = $state.current.name === 'consulta.novo' ? 'NOVO' : 'EDICAO';
                if ($scope.acao === 'EDICAO') {
                    consultaEdicaoService.getConsulta($stateParams.id).$promise.then(
                        function (data) {
                            $scope.consulta = data;
                        }
                    );
                }
            }

            inicializar();
        }
    ]
);