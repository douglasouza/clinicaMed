var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('consultaListagemController',
    ['$scope', '$state', 'constants', 'consultaListagemService', function ($scope, $state, constants, consultaListagemService) {

        $scope.pesquisar = function () {
            if ($scope.filtro.nomeCrmLogin || $scope.filtro.especialidade) {
                consultaListagemService.fetchAll($scope.filtro);
                $scope.pesquisaRealizada = true;
            }
        };

        $scope.limparPesquisa = function () {
            $scope.filtro = {nomeCrmLogin: '', especialidade: ''};
            consultaListagemService.fetchAll($scope.filtro);
            $scope.pesquisaRealizada = false;
        };

        $scope.editarConsulta = function (id) {
            $state.go('consulta.edicao', {id: id});
        };

        $scope.excluirConsulta = function (id) {
            $scope.idConsulta = id;
        };

        $scope.confirmarExcluirConsulta = function () {
            consultaListagemService.deleteConsulta($scope.idConsulta);
        };

        $scope.$on('CONSULTAS_FETCHED_SUCCESS', function (e, data) {
            $scope.consultas = data;
        });

        $scope.$on('CONSULTA_DELETE_SUCCESS', function () {
            consultaListagemService.fetchAll();
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
                    caminhoNoObjeto: 'especialidade',
                    classeCol: 'col-md-2',
                    filter: 'enumEspecialidadeMedica',
                    nome: 'Especialidade'
                },
                {
                    caminhoNoObjeto: 'crm',
                    classeCol: 'col-md-1',
                    nome: 'CRM'
                },
                {
                    caminhoNoObjeto: 'login',
                    classeCol: 'col-md-2',
                    nome: 'Login'
                }
            ];
        }

        function initizialize() {
            inicializarDadosTabelaListagem();
            $scope.especialidades = constants.ENUM.ESPECIALIDADE_MEDICA;
            $scope.filtro = {nomeCrmLogin: '', especialidade: ''};
            consultaListagemService.fetchAll($scope.filtro);
        }

        initizialize();
    }]
);