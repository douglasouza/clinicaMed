var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('medicoEdicaoController',
    ['$scope', '$state', '$stateParams', 'medicoEdicaoService', function ($scope, $state, $stateParams, medicoEdicaoService) {
        $scope.salvar = function () {
            if ($scope.acao === 'NOVO') {
                medicoEdicaoService.saveUsuario($scope.medico);
            } else {
                medicoEdicaoService.updateUsuario($stateParams.id, $scope.medico);
            }
        };

        $scope.$on('MEDICO_SAVE_SUCCESS', function () {
            operacaoSucesso();
        });

        $scope.$on('MEDICO_SAVE_ERROR', function () {
            operacaoErro();
        });

        $scope.$on('MEDICO_UPDATE_SUCCESS', function () {
            operacaoSucesso();
        });

        $scope.$on('MEDICO_UPDATE_ERROR', function () {
            operacaoErro();
        });

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
            $scope.mostrarAlertaErro = false;
            $scope.acaoFinalizada = true;
        }

        function operacaoErro() {
            $scope.mostrarAlertaSucesso = false;
            $scope.mostrarAlertaErro = true;
        }

        function inicializar() {
            $scope.medico = {tipoUsuario: 'MEDICO'};
            $scope.acao = $state.current.name === 'medico.novo' ? 'NOVO' : 'EDICAO';
            if ($scope.acao === 'EDICAO') {
                medicoEdicaoService.getUsuario($stateParams.id).$promise.then(
                    function (data) {
                        $scope.medico = data;
                    }
                );
            }
        }

        inicializar();
    }]
);