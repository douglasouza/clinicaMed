var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('usuarioListagemController',
    ['$scope', 'usuarioListagemService', function ($scope, usuarioListagemService) {

        $scope.excluirUsuario = function (idUsuario) {
            usuarioListagemService.deleteUsuario(idUsuario);
        };

        $scope.$on('USUARIOS_FETCHED_SUCCESS', function (e, data) {
            $scope.usuarios = data;
        });

        $scope.$on('USUARIOS_FETCHED_ERROR', function () {
            operacaoErro();
        });

        $scope.$on('USUARIO_DELETE_SUCCESS', function () {
            usuarioListagemService.fetchAll();
            operacaoSucesso();
        });

        $scope.$on('USUARIO_DELETE_ERROR', function () {
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

        function initizialize() {
            usuarioListagemService.fetchAll();
        }

        initizialize();
    }]
);