var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('mensagemErro', function () {
    return {
        restrict: 'E',
        template: '<div ng-if="mostrarAlerta" class="alert alert-danger" role="alert">' +
                      '<button type="button" class="close" data-dismiss="alert" aria-label="Fechar">' +
                          '<span aria-hidden="true">&times;</span>' +
                      '</button>' +
                      '<strong>Erro!</strong> {{mensagem}}' +
                  '</div>',
        link: function (scope, element, attrs) {
            scope.$on('RESPONSE_SUCCESS', function (event, data) {
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
