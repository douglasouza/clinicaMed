(function (angular) {

    var clinicaMed = angular.module("clinicaMed", ["ngResource", 'ui.router']);

    clinicaMed.constant('jQuery', window.jQuery);

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
                $rootScope.$broadcast('RESPONSE_ERROR', response);
                return $q.reject(response);
            }
        };
    }]);

    clinicaMed.config(['$httpProvider', '$urlRouterProvider', function ($httpProvider, $urlRouterProvider) {
        $httpProvider.interceptors.push('httpInterceptor');

        $urlRouterProvider.when('/medico', '/medico/listagem');
        $urlRouterProvider.when('/medico/', '/medico/listagem');
        $urlRouterProvider.when('/recepcionista', '/recepcionista/listagem');
        $urlRouterProvider.when('/recepcionista/', '/recepcionista/listagem');
        $urlRouterProvider.otherwise('/home');
    }]);

    clinicaMed.run(['$rootScope', '$state', '$window', 'loginService', function ($rootScope, $state, $window, loginService) {
        $rootScope.$on('$stateChangeStart', function (event, toState) {
            if (!toState.acesso.loginRequerido) {
                return;
            } else if (toState.url === "/login" && loginService.usuarioEstaLogado()) {
                event.preventDefault();
            } else if (toState.acesso && toState.acesso.loginRequerido && !loginService.usuarioEstaLogado()) {
                event.preventDefault();
                $rootScope.$broadcast('AUTENTICACAO_REQUERIDA');
            } else if (toState.acesso && !loginService.usuarioTemPermissao(toState.acesso.usuariosAutorizados)) {
                event.preventDefault();
                $rootScope.$broadcast("event:auth-forbidden", {});
            }
        });

        $rootScope.$on('AUTENTICACAO_REQUERIDA', function () {
            $rootScope.autenticado = false;
            $state.go('login');
        });

        $rootScope.$on('AUTENTICACAO_REALIZADA', function () {
            $state.go('home');
        });

    }]);

}(angular));