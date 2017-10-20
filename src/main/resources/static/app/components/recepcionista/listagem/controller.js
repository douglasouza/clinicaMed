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
        });

        $scope.$on('RECEPCIONISTA_DELETE_SUCCESS', function () {
            recepcionistaListagemService.fetchAll();
            operacaoSucesso();
        });

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
        }

        function initizialize() {
            $scope.filtro = {nomeLogin: ''};
            recepcionistaListagemService.fetchAll($scope.filtro);
        }

        initizialize();
    }]
);