var clinicaMed = angular.module('clinicaMed');

clinicaMed.controller('recepcionistaEdicaoController',
    ['$scope', '$state', '$stateParams', 'recepcionistaEdicaoService', function ($scope, $state, $stateParams, recepcionistaEdicaoService) {
        $scope.salvar = function (formularioValido) {
            if (!formularioValido)
                return;

            if ($scope.acao === 'NOVO') {
                recepcionistaEdicaoService.saveRecepcionista($scope.recepcionista);
            } else {
                recepcionistaEdicaoService.updateRecepcionista($stateParams.id, $scope.recepcionista);
            }
        };

        $scope.$on('RECEPCIONISTA_SAVE_SUCCESS', function () {
            operacaoSucesso();
        });

        $scope.$on('RECEPCIONISTA_UPDATE_SUCCESS', function () {
            operacaoSucesso();
        });

        function operacaoSucesso() {
            $scope.mostrarAlertaSucesso = true;
            $scope.acaoFinalizada = true;
        }

        function inicializar() {
            $scope.recepcionista = {usuario: {tipoUsuario: 'RECEPCIONISTA'}};
            $scope.acao = $state.current.name === 'recepcionista.novo' ? 'NOVO' : 'EDICAO';
            if ($scope.acao === 'EDICAO') {
                recepcionistaEdicaoService.getRecepcionista($stateParams.id).$promise.then(
                    function (data) {
                        $scope.recepcionista = data;
                    }
                );
            }
        }

        inicializar();
    }]
);