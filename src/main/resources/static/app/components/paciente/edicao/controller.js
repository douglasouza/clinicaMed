var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('pacienteEdicaoController',
    ['$scope', '$state', '$stateParams', 'pacienteEdicaoService', function ($scope, $state, $stateParams, pacienteEdicaoService) {
        $scope.salvar = function () {
            if ($scope.acao === 'NOVO') {
                pacienteEdicaoService.savePaciente($scope.paciente);
            } else {
                pacienteEdicaoService.updatePaciente($stateParams.id, $scope.paciente);
            }
        };

        $scope.mudarInputType = function () {
            $scope.mostrarSenha = !$scope.mostrarSenha;
        };

        $scope.$on('PACIENTE_SAVE_SUCCESS', function () {
            operacaoSucesso();
        });

        $scope.$on('PACIENTE_UPDATE_SUCCESS', function () {
            operacaoSucesso();
        });

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
            $scope.acaoFinalizada = true;
        }

        function inicializar() {
            $scope.mostrarSenha = false;
            $scope.paciente = {tipoUsuario: 'PACIENTE'};
            $scope.acao = $state.current.name === 'paciente.novo' ? 'NOVO' : 'EDICAO';
            if ($scope.acao === 'EDICAO') {
                pacienteEdicaoService.getPaciente($stateParams.id).$promise.then(
                    function (data) {
                        $scope.paciente = data;
                    }
                );
            }
        }

        inicializar();
    }]
);