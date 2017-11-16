var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('medicamentoListagemController',
    ['$scope', '$state', 'constants', 'medicamentoListagemService', function ($scope, $state, constants, medicamentoListagemService) {
        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
        }

        function inicializarDadosTabelaListagem() {
            $scope.paginaAtual = 1;
            $scope.colunas = [
                {
                    caminhoNoObjeto: 'nomeGenerico',
                    classeCol: 'col-md-3',
                    nome: 'Nome Genérico'
                },
                {
                    caminhoNoObjeto: 'nomeFabrica',
                    classeCol: 'col-md-4',
                    nome: 'Nome Fábrica'
                },
                {
                    caminhoNoObjeto: 'fabricante',
                    classeCol: 'col-md-3',
                    nome: 'Fabricante'
                }
            ];
        }

        function inicializar() {
            inicializarDadosTabelaListagem();
            $scope.filtro = '';
            medicamentoListagemService.fetchAll($scope.filtro);
        }

        $scope.pesquisar = function () {
            if ($scope.filtro) {
                medicamentoListagemService.fetchAll($scope.filtro);
                $scope.pesquisaRealizada = true;
            }
        };

        $scope.limparPesquisa = function () {
            $scope.filtro = '';
            medicamentoListagemService.fetchAll($scope.filtro);
            $scope.pesquisaRealizada = false;
        };

        $scope.editarMedicamento = function (id) {
            $state.go('medicamento.edicao', {id: id});
        };

        $scope.excluirMedicamento = function (id) {
            $scope.idMedicamento = id;
        };

        $scope.confirmarExcluirMedicamento = function () {
            medicamentoListagemService.deleteMedicamento($scope.idMedicamento);
        };

        $scope.$on('MEDICAMENTOS_FETCHED_SUCCESS', function (e, data) {
            $scope.medicamentos = data;
        });

        $scope.$on('MEDICAMENTO_DELETE_SUCCESS', function () {
            medicamentoListagemService.fetchAll();
            operacaoSucesso();
        });

        inicializar();
    }]
);