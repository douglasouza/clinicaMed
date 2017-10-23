var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('mensagemSucesso', function () {
    return {
        restrict: 'E',
        scope: {
            mostrarAlertaSucesso: '='
        },
        template: '<div ng-if="mostrarAlertaSucesso" class="alert alert-success" role="alert">' +
                      '<button type="button" class="close" data-dismiss="alert" aria-label="Fechar">' +
                          '<span aria-hidden="true">&times;</span>' +
                      '</button>' +
                      'Operação realizada com sucesso!' +
                  '</div>'
    }
});
