var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('mensagemSucesso', function () {
    return {
        restrict: 'E',
        scope: {
            mostrarAlertaSucesso: '='
        },
        templateUrl: './app/common/directives/mensagem-sucesso/template.html',
        link: function (scope) {
            scope.fecharModal = function () {
                scope.mostrarAlertaSucesso = false;
            };
        }
    }
});
