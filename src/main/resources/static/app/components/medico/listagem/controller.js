var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('medicoListagemController',
    ['$scope', 'medicoListagemService', function ($scope, medicoListagemService) {

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

        $scope.excluirMedico = function (idMedico) {
            $scope.idMedico = idMedico;
        };

        $scope.confirmarExcluirMedico = function () {
            medicoListagemService.deleteMedico($scope.idMedico);
        };

        $scope.$on('MEDICOS_FETCHED_SUCCESS', function (e, data) {
            $scope.medicos = data;
        });

        $scope.$on('MEDICOS_FETCHED_ERROR', function () {
            operacaoErro();
        });

        $scope.$on('MEDICO_DELETE_SUCCESS', function () {
            medicoListagemService.fetchAll();
            operacaoSucesso();
        });

        $scope.$on('MEDICO_DELETE_ERROR', function () {
            operacaoErro();
        });

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
            $scope.mostrarAlertaErro = false;
        }

        function operacaoErro() {
            $scope.mostrarAlertaSucesso = false;
            $scope.mostrarAlertaErro = true;
        }

        function initizialize() {
            $scope.filtro = {nomeCrmLogin: '', especialidade: ''};
            medicoListagemService.fetchAll($scope.filtro);
        }

        initizialize();
    }]
);