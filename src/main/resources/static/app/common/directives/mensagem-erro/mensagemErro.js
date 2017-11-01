var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('mensagemErro', function () {
    return {
        restrict: 'E',
        templateUrl: './app/common/directives/mensagem-erro/template.html',
        link: function (scope) {
            scope.$on('RESPONSE_SUCCESS', function () {
                scope.mostrarAlerta = false;
            });

            scope.$on('RESPONSE_ERROR', function (event, data) {
                if (data && data.data && data.data.message)
                    scope.mensagem = data.data.message;
                scope.mostrarAlerta = true;
            });
        }
    }
});
