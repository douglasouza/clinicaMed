(function (angular) {

    var clinicaMed = angular.module("clinicaMed", ["ngResource", 'ui.router']);

    clinicaMed.constant('jQuery', window.jQuery);

    clinicaMed.constant('CryptoJS', CryptoJS);

    clinicaMed.factory('httpInterceptor', ['$rootScope', '$q', function ($rootScope, $q) {
        return {
            request: function (config) {
                $rootScope.$broadcast('REQUEST_SENT');
                return config;
            },
            response: function (response) {
                $rootScope.$broadcast('RESPONSE_SUCCESS');
                return response;
            },
            responseError: function (response) {
                if (response.status === 401) {
                    $rootScope.usuarioLogado = undefined;
                    $rootScope.$broadcast('AUTENTICACAO_REQUERIDA');
                }

                $rootScope.$broadcast('RESPONSE_ERROR', response);
                return $q.reject(response);
            }
        };
    }]);

    clinicaMed.config(['$httpProvider', '$urlRouterProvider', function ($httpProvider, $urlRouterProvider) {
        $httpProvider.interceptors.push('httpInterceptor');

        $urlRouterProvider.when('/medico', '/medico/listagem');
        $urlRouterProvider.when('/medico/', '/medico/listagem');
        $urlRouterProvider.when('/paciente', '/paciente/listagem');
        $urlRouterProvider.when('/paciente/', '/paciente/listagem');
        $urlRouterProvider.when('/recepcionista', '/recepcionista/listagem');
        $urlRouterProvider.when('/recepcionista/', '/recepcionista/listagem');
        $urlRouterProvider.otherwise('/home');
    }]);

    clinicaMed.run(['$rootScope', '$state', '$http', 'loginService', function ($rootScope, $state, $http, loginService) {
        if (!$rootScope.usuarioLogado) {
            var request = new XMLHttpRequest();
            request.open('GET', '/usuario/usuarioLogado', false);
            console.clear();
            request.send(null);

            if (request.status === 200 && request.responseText)
                $rootScope.usuarioLogado = JSON.parse(request.responseText);
        }

        $rootScope.$on('$stateChangeStart', function (event, toState) {
            if (toState.name !== 'ativarUsuario' && $rootScope.usuarioLogado && !$rootScope.usuarioLogado.ativado) {
                event.preventDefault();
                $state.go('ativarUsuario');
            } else {
                if ((toState.url === '/login' || toState.url === '/ativarUsuario') && $rootScope.usuarioLogado && $rootScope.usuarioLogado.ativado) {
                    event.preventDefault();
                    $state.go('home');
                } else if (toState.acesso && !loginService.usuarioEstaLogado(toState.acesso.loginRequerido)) {
                    event.preventDefault();
                    $state.go('login');
                } else if (toState.acesso && !loginService.usuarioTemPermissao(toState.acesso.usuariosAutorizados)) {
                    event.preventDefault();
                    $state.go('acessoNegado');
                }
            }
        });

        $rootScope.$on('AUTENTICACAO_REQUERIDA', function () {
            $state.go('login');
        });

        $rootScope.$on('LOGOUT_SUCCESS', function () {
            $state.go('login');
        });
    }]);

}(angular));