var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('mensagemSucesso', function ($timeout) {
    return {
        restrict: 'E',
        scope: {
            mostrarAlertaSucesso: '='
        },
        templateUrl: './app/common/directives/mensagem-sucesso/template.html',
        link: function (scope) {
            scope.$watch('mostrarAlertaSucesso', function (novoValor) {
                if (novoValor) {
                    $timeout(function () {
                        scope.fecharAlerta();
                    }, 5000);
                }
            });

            scope.fecharAlerta = function () {
                scope.mostrarAlertaSucesso = false;
            };
        }
    };
});
