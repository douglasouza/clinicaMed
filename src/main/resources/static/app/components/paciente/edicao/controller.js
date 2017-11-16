var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('pacienteEdicaoController',
    ['$scope', '$state', '$stateParams', 'constants', 'pacienteEdicaoService', function ($scope, $state, $stateParams, constants, pacienteEdicaoService) {

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
            $scope.acaoFinalizada = true;
        }

        function inicializar() {
            $scope.sexos = constants.ENUM.SEXO;
            $scope.acao = $state.current.name === 'paciente.novo' ? 'NOVO' : 'EDICAO';
            if ($scope.acao === 'EDICAO') {
                pacienteEdicaoService.getPaciente($stateParams.id).$promise.then(
                    function (data) {
                        $scope.paciente = data;
                    }
                );
            }
        }

        $scope.salvar = function (formularioValido) {
            if (!formularioValido)
                return;

            if ($scope.acao === 'NOVO') {
                pacienteEdicaoService.savePaciente($scope.paciente);
            } else {
                pacienteEdicaoService.updatePaciente($stateParams.id, $scope.paciente);
            }
        };

        $scope.$on('PACIENTE_SAVE_SUCCESS', function () {
            operacaoSucesso();
        });

        $scope.$on('PACIENTE_UPDATE_SUCCESS', function () {
            operacaoSucesso();
        });

        inicializar();
    }]
);