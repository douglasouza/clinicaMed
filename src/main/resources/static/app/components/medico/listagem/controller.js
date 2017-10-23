var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('medicoListagemController',
    ['$scope', '$state', 'constants', 'medicoListagemService', function ($scope, $state, constants, medicoListagemService) {

        $scope.pesquisar = function () {
            if ($scope.filtro.nomeCrmLogin || $scope.filtro.especialidade) {
                medicoListagemService.fetchAll($scope.filtro);
                $scope.pesquisaRealizada = true;
            }
        };

        $scope.limparPesquisa = function () {
            $scope.filtro = {nomeCrmLogin: '', especialidade: ''};
            medicoListagemService.fetchAll($scope.filtro);
            $scope.pesquisaRealizada = false;
        };

        $scope.editarMedico = function (id) {
            $state.go('medico.edicao', {id: id});
        };

        $scope.excluirMedico = function (id) {
            $scope.idMedico = id;
        };

        $scope.confirmarExcluirMedico = function () {
            medicoListagemService.deleteMedico($scope.idMedico);
        };

        $scope.$on('MEDICOS_FETCHED_SUCCESS', function (e, data) {
            $scope.medicos = data;
        });

        $scope.$on('MEDICO_DELETE_SUCCESS', function () {
            medicoListagemService.fetchAll();
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
            $scope.especialidades = constants().ENUM.ESPECIALIDADE_MEDICA;
            $scope.filtro = {nomeCrmLogin: '', especialidade: ''};
            medicoListagemService.fetchAll($scope.filtro);
        }

        initizialize();
    }]
);