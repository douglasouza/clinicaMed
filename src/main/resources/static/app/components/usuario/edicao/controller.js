var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('usuarioEdicaoController',
    ['$scope', '$state', '$stateParams', 'usuarioEdicaoService', function ($scope, $state, $stateParams, usuarioEdicaoService) {
        $scope.salvar = function () {
            if ($scope.acao === 'NOVO') {
                usuarioEdicaoService.saveUsuario($scope.usuario);
            } else {
                usuarioEdicaoService.updateUsuario($stateParams.id, $scope.usuario);
            }
        };

        $scope.$on('USUARIO_SAVE_SUCCESS', function () {
            operacaoSucesso();
        });

        $scope.$on('USUARIO_SAVE_ERROR', function () {
            operacaoErro();
        });

        $scope.$on('USUARIO_UPDATE_SUCCESS', function () {
            operacaoSucesso();
        });

        $scope.$on('USUARIO_UPDATE_ERROR', function () {
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
            $scope.mostrarAlertaSucesso = false;
            $scope.mostrarAlertaErro = false;
            $scope.acao = $state.current.name === 'usuario.novo' ? 'NOVO' : 'EDICAO';

            if ($scope.acao === 'NOVO') {
                $scope.usuario = {tipoUsuario: 'MEDICO'};
            } else {
                usuarioEdicaoService.getUsuario($stateParams.id).$promise.then(
                    function (data) {
                        $scope.usuario = data;
                    }
                );
            }
        }

        inicializar();
    }]
);