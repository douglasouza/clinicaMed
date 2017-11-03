var clinicaMed = angular.module('clinicaMed');

clinicaMed.directive('barraNavegacao',
    ['$rootScope', 'loginService', function ($rootScope, loginService) {
        return {
            restrict: 'E',
            scope: {},
            templateUrl: './app/common/directives/barra-navegacao/template.html',
            link: function (scope) {
                scope.administrador = false;
                scope.medico = false;
                scope.recepcionista = false;

                function inicializar() {
                    if (!$rootScope.usuarioLogado)
                        return;

                    scope.login = $rootScope.usuarioLogado.login;

                    if ($rootScope.usuarioLogado.tipoUsuario === 'ADMINISTRADOR')
                        scope.administrador = true;
                    else if ($rootScope.usuarioLogado.tipoUsuario === 'MEDICO')
                        scope.medico = true;
                    else if ($rootScope.usuarioLogado.tipoUsuario === 'RECEPCIONISTA')
                        scope.recepcionista = true;
                }

                scope.logout = function () {
                    loginService.logout();
                };

                inicializar();
            }
        }
    }]
);
