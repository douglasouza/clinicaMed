var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('medicoListagemController',
    ['$scope', 'medicoListagemService', function ($scope, medicoListagemService) {

        $scope.excluirMedico = function (idMedico) {
            medicoListagemService.deleteMedico(idMedico);
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
            medicoListagemService.fetchAll();
        }

        initizialize();
    }]
);