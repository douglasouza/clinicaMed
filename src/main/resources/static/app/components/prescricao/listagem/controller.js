var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('prescricaoListagemController',
    ['$scope', '$state', 'constants', 'prescricaoListagemService', function ($scope, $state, constants, prescricaoListagemService) {

        $scope.pesquisar = function () {
            if ($scope.filtro) {
                prescricaoListagemService.fetchAll($scope.filtro);
                $scope.pesquisaRealizada = true;
            }
        };

        $scope.limparPesquisa = function () {
            $scope.filtro = '';
            prescricaoListagemService.fetchAll($scope.filtro);
            $scope.pesquisaRealizada = false;
        };

        $scope.editarPrescricao = function (id) {
            $state.go('prescricao.edicao', {id: id});
        };

        $scope.excluirPrescricao = function (id) {
            $scope.idPrescricao = id;
        };

        $scope.confirmarExcluirPrescricao = function () {
            prescricaoListagemService.deletePrescricao($scope.idPrescricao);
        };

        $scope.$on('PRESCRICOES_FETCHED_SUCCESS', function (e, data) {
            $scope.prescricaos = data;
        });

        $scope.$on('PRESCRICAO_DELETE_SUCCESS', function () {
            prescricaoListagemService.fetchAll();
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
                    caminhoNoObjeto: 'nomeGenericoMedicamento',
                    classeCol: 'col-md-4',
                    nome: 'Nome Genérico Medicamento'
                },
                {
                    caminhoNoObjeto: 'entregue',
                    classeCol: 'col-md-2',
                    nome: 'Entrege ao Paciente'
                }
            ];
        }

        function initizialize() {
            inicializarDadosTabelaListagem();
            $scope.filtro = '';
            prescricaoListagemService.fetchAll($scope.filtro);
        }

        initizialize();
    }]
);