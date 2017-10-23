var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('recepcionistaListagemController',
    ['$scope', '$state', 'recepcionistaListagemService', function ($scope, $state, recepcionistaListagemService) {

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

        $scope.editarRecepcionista = function (id) {
            $state.go('recepcionista.edicao', {id: id});
        };

        $scope.excluirRecepcionista = function (id) {
            $scope.idRecepcionista = id;
        };

        $scope.confirmarExcluirRecepcionista = function () {
            recepcionistaListagemService.deleteRecepcionista($scope.idRecepcionista);
        };

        $scope.$on('RECEPCIONISTAS_FETCHED_SUCCESS', function (e, data) {
            $scope.recepcionistas = data;
        });

        $scope.$on('RECEPCIONISTA_DELETE_SUCCESS', function () {
            recepcionistaListagemService.fetchAll();
            operacaoSucesso();
        });

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
        }

        function inicializarDadosTabelaListagem() {
            $scope.paginaAtual = 1;
            $scope.colunas = [
                {
                    caminhoNoObjeto: 'nome',
                    classeCol: 'col-md-5',
                    nome: 'Nome'
                },
                {
                    caminhoNoObjeto: 'login',
                    classeCol: 'col-md-5',
                    nome: 'Login'
                }
            ];
        }

        function initizialize() {
            inicializarDadosTabelaListagem();
            $scope.filtro = {nomeLogin: ''};
            recepcionistaListagemService.fetchAll($scope.filtro);
        }

        initizialize();
    }]
);