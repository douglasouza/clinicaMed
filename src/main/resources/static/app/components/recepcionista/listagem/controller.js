var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('recepcionistaListagemController',
    ['$scope', 'recepcionistaListagemService', function ($scope, recepcionistaListagemService) {

        $scope.pesquisar = function () {
            if ($scope.filtro.nomeLogin || $scope.filtro.especialidade) {
                recepcionistaListagemService.fetchAll($scope.filtro);
                $scope.pesquisaRealizada = true;
            }
        };

        $scope.limparPesquisa = function () {
            $scope.filtro = {nomeLogin: '', especialidade: ''};
            recepcionistaListagemService.fetchAll($scope.filtro);
            $scope.pesquisaRealizada = false;
        };

        $scope.excluirRecepcionista = function (idRecepcionista) {
            $scope.idRecepcionista = idRecepcionista;
        };

        $scope.confirmarExcluirRecepcionista = function () {
            recepcionistaListagemService.deleteRecepcionista($scope.idRecepcionista);
        };

        $scope.$on('RECEPCIONISTAS_FETCHED_SUCCESS', function (e, data) {
            $scope.recepcionistas = data;
            getRegistrosPaginaAtual();
        });

        $scope.$on('RECEPCIONISTA_DELETE_SUCCESS', function () {
            recepcionistaListagemService.fetchAll();
            operacaoSucesso();
        });

        $scope.$watch('paginaAtual', function (novoValor) {
            $scope.paginaAtual = novoValor;
            getRegistrosPaginaAtual();
        });

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
        }

        function getRegistrosPaginaAtual() {
            var inicio = 1 + (($scope.paginaAtual - 1) * 10);
            var fim = $scope.recepcionistas.length > 10 ? 10 + (($scope.paginaAtual - 1) * 10) : $scope.recepcionistas.length;
            $scope.registrosPagAtual = $scope.recepcionistas.slice(inicio - 1, fim);
        }

        function initizialize() {
            $scope.paginaAtual = 1;
            $scope.filtro = {nomeLogin: ''};
            recepcionistaListagemService.fetchAll($scope.filtro);
        }

        initizialize();
    }]
);