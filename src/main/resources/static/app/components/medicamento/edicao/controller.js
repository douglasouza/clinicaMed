var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('medicamentoEdicaoController',
    ['$scope', '$state', '$stateParams', 'jQuery', 'constants', 'medicamentoEdicaoService', function ($scope, $state, $stateParams, $, constants, medicamentoEdicaoService) {

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
            $scope.acaoFinalizada = true;
        }

        function inicializar() {
            $scope.acao = $state.current.name === 'medicamento.novo' ? 'NOVO' : 'EDICAO';
            if ($scope.acao === 'EDICAO') {
                medicamentoEdicaoService.getMedicamento($stateParams.id).$promise.then(
                    function (data) {
                        $scope.medicamento = data;
                    }
                );
            }
        }

        $scope.salvar = function (formularioValido) {
            if (!formularioValido)
                return;

            if ($scope.acao === 'NOVO') {
                medicamentoEdicaoService.saveMedicamento($scope.medicamento);
            } else {
                medicamentoEdicaoService.updateMedicamento($stateParams.id, $scope.medicamento);
            }
        };

        $scope.$on('MEDICAMENTO_SAVE_SUCCESS', function () {
            operacaoSucesso();
        });

        $scope.$on('MEDICAMENTO_UPDATE_SUCCESS', function () {
            operacaoSucesso();
        });

        inicializar();
    }]
);