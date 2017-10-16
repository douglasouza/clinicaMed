var myApp = angular.module('myApp');

myApp.controller('usuarioEdicaoController',
    ['$scope', '$state', '$stateParams', 'usuarioEdicaoService', function ($scope, $state, $stateParams, usuarioEdicaoService) {

        function operacaoSucesso() {
            $scope.showAlertSuccess = true;
            $scope.showAlertError = false;
        }

        function operacaoErro() {
            $scope.showAlertSuccess = false;
            $scope.showAlertError = true;
        }

        function initialize() {
            $scope.showAlertSuccess = false;
            $scope.showAlertError = false;
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

        initialize();

        $scope.salvar = function () {
            if ($scope.acao === 'NOVO') {
                usuarioEdicaoService.saveUsuario($scope.usuario);
            } else {
                usuarioEdicaoService.updateUsuario($stateParams.id, $scope.usuario);
            }
        };

        $scope.$on('USUARIO_SAVE_SUCCESS', function (data) {
            operacaoSucesso();
            $state.go('usuario.editar', {id: data.id});
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

    }]
);