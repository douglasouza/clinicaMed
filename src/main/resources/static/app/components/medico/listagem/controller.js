var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('medicoListagemController',
    ['$scope', 'constants', 'medicoListagemService', function ($scope, constants, medicoListagemService) {

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

        $scope.$on('MEDICO_DELETE_SUCCESS', function () {
            medicoListagemService.fetchAll();
            operacaoSucesso();
        });

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
        }

        function initizialize() {
            $scope.especialidades = constants().ENUM.ESPECIALIDADE_MEDICA;
            $scope.filtro = {nomeCrmLogin: '', especialidade: ''};
            medicoListagemService.fetchAll($scope.filtro);
        }

        initizialize();
    }]
);