var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('pacienteListagemController',
    ['$scope', '$state', 'pacienteListagemService', function ($scope, $state, pacienteListagemService) {

        $scope.pesquisar = function () {
            if ($scope.filtro.nomeLogin || $scope.filtro.especialidade) {
                pacienteListagemService.fetchAll($scope.filtro);
                $scope.pesquisaRealizada = true;
            }
        };

        $scope.limparPesquisa = function () {
            $scope.filtro = {nomeLogin: '', especialidade: ''};
            pacienteListagemService.fetchAll($scope.filtro);
            $scope.pesquisaRealizada = false;
        };

        $scope.editarPaciente = function (id) {
            $state.go('paciente.edicao', {id: id});
        };

        $scope.excluirPaciente = function (id) {
            $scope.idPaciente = id;
        };

        $scope.confirmarExcluirPaciente = function () {
            pacienteListagemService.deletePaciente($scope.idPaciente);
        };

        $scope.$on('PACIENTES_FETCHED_SUCCESS', function (e, data) {
            $scope.pacientes = data;
        });

        $scope.$on('PACIENTE_DELETE_SUCCESS', function () {
            pacienteListagemService.fetchAll();
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
            pacienteListagemService.fetchAll($scope.filtro);
        }

        initizialize();
    }]
);