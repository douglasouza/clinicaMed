var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('medicoEdicaoController',
    ['$scope', '$state', '$stateParams', 'jQuery', 'constants', 'medicoEdicaoService', function ($scope, $state, $stateParams, $, constants, medicoEdicaoService) {
        $scope.salvar = function () {
            if ($scope.acao === 'NOVO') {
                medicoEdicaoService.saveUsuario($scope.medico);
            } else {
                medicoEdicaoService.updateUsuario($stateParams.id, $scope.medico);
            }
        };

        $scope.mudarInputType = function () {
            $scope.mostrarSenha = !$scope.mostrarSenha;
        };

        $scope.$on('MEDICO_SAVE_SUCCESS', function () {
            operacaoSucesso();
        });

        $scope.$on('MEDICO_UPDATE_SUCCESS', function () {
            operacaoSucesso();
        });

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
            $scope.acaoFinalizada = true;
        }

        function inicializar() {
            $scope.mostrarSenha = false;
            $scope.especialidades = constants().ENUM.ESPECIALIDADE_MEDICA;
            $scope.medico = {tipoUsuario: 'MEDICO'};
            $scope.acao = $state.current.name === 'medico.novo' ? 'NOVO' : 'EDICAO';
            if ($scope.acao === 'EDICAO') {
                medicoEdicaoService.getUsuario($stateParams.id).$promise.then(
                    function (data) {
                        $scope.medico = data;
                    }
                );
            }
        }

        (function avoidDefaultPasteEvent() {
            $('#crmMedico').bind('paste', function (e) {
                e.preventDefault();
                var previousInputData = $('#crmMedico').val();
                var pasteData = e.originalEvent.clipboardData.getData('Text');
                $('#crmMedico').val(previousInputData + pasteData.replace(/[^0-9]/g, ''));
            });
        })();

        inicializar();
    }]
);