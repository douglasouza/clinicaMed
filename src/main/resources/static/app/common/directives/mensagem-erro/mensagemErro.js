var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('mensagemErro', function ($timeout, jQuery) {
    return {
        restrict: 'E',
        templateUrl: './app/common/directives/mensagem-erro/template.html',
        link: function (scope) {
            scope.$on('RESPONSE_SUCCESS', function () {
                scope.mostrarAlerta = false;
            });

            scope.$on('RESPONSE_ERROR', function (event, data) {
                if (jQuery('div.alert').length > 0)
                    return;

                if (data && data.data && data.data.message)
                    scope.mensagem = data.data.message;
                scope.mostrarAlerta = true;

                $timeout(function () {
                    scope.fecharAlerta();
                }, 5000);
            });

            scope.fecharAlerta = function () {
                scope.mostrarAlerta = false;
            };
        }
    }
});
