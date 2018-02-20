var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('solicitacaoExameListagemController',
    ['$scope', '$state', 'constants', 'solicitacaoExameListagemService', function ($scope, $state, constants, solicitacaoExameListagemService) {

        $scope.pesquisar = function () {
            if ($scope.filtro) {
                solicitacaoExameListagemService.fetchAll($scope.filtro);
                $scope.pesquisaRealizada = true;
            }
        };

        $scope.limparPesquisa = function () {
            $scope.filtro = '';
            solicitacaoExameListagemService.fetchAll($scope.filtro);
            $scope.pesquisaRealizada = false;
        };

        $scope.editarSolicitacaoExame = function (id) {
            $state.go('solicitacaoExame.edicao', {id: id});
        };

        $scope.excluirSolicitacaoExame = function (id) {
            $scope.idSolicitacaoExame = id;
        };

        $scope.confirmarExcluirSolicitacaoExame = function () {
            solicitacaoExameListagemService.deleteSolicitacaoExame($scope.idSolicitacaoExame);
        };

        $scope.$on('SOLICITACOES_EXAME_FETCHED_SUCCESS', function (e, data) {
            $scope.solicitacaoExames = data;
        });

        $scope.$on('SOLICITACAO_EXAME_DELETE_SUCCESS', function () {
            solicitacaoExameListagemService.fetchAll();
            operacaoSucesso();
        });

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
        }

        function inicializarDadosTabelaListagem() {
            $scope.paginaAtual = 1;
            $scope.colunas = [
                {
                    caminhoNoObjeto: 'nomePaciente',
                    classeCol: 'col-md-4',
                    nome: 'Nome Paciente'
                },
                {
                    caminhoNoObjeto: 'nomeMedico',
                    classeCol: 'col-md-4',
                    nome: 'Nome do Médico Solicitante'
                },
                {
                    caminhoNoObjeto: 'resultadoEntregue',
                    classeCol: 'col-md-2',
                    nome: 'Resultado Entregue'
                }
            ];
        }

        function initizialize() {
            inicializarDadosTabelaListagem();
            $scope.filtro = '';
            solicitacaoExameListagemService.fetchAll($scope.filtro);
        }

        initizialize();
    }]
);