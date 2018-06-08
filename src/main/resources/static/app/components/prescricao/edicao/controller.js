var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('prescricaoEdicaoController',
    ['$scope', '$state', '$stateParams', 'prescricaoEdicaoService', 'pacienteListagemService', 'medicamentoListagemService',
        function ($scope, $state, $stateParams, prescricaoEdicaoService, pacienteListagemService, medicamentoListagemService) {
            $scope.salvar = function (formularioValido) {
                if (!formularioValido)
                    return;

                if ($scope.acao === 'NOVO') {
                    prescricaoEdicaoService.savePrescricao($scope.prescricao);
                } else {
                    prescricaoEdicaoService.updatePrescricao($stateParams.id, $scope.prescricao);
                }
            };

            $scope.$on('PRESCRICAO_SAVE_SUCCESS', function () {
                operacaoSucesso();
            });

            $scope.$on('PRESCRICAO_UPDATE_SUCCESS', function () {
                operacaoSucesso();
            });

            $scope.$on('PACIENTES_FETCHED_SUCCESS', function (e, data) {
                $scope.pacientes = data;
            });

            $scope.$on('MEDICAMENTOS_FETCHED_SUCCESS', function (e, data) {
                $scope.medicamentos = data;
            });

            function operacaoSucesso() {
                $scope.mostrarAlertaSucesso = true;
                $scope.acaoFinalizada = true;
            }

            function inicializar() {
                $scope.acao = $state.current.name === 'prescricao.novo' ? 'NOVO' : 'EDICAO';
                if ($scope.acao === 'EDICAO') {
                    prescricaoEdicaoService.getPrescricao($stateParams.id).$promise.then(
                        function (data) {
                            $scope.prescricao = data;
                            $scope.acaoFinalizada = $scope.prescricao.entregue;
                        }
                    );
                }

                pacienteListagemService.fetchAll();
                medicamentoListagemService.fetchAll();
            }

            inicializar();
        }
    ]
);