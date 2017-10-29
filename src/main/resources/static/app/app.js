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
                if (response.headers()['usuario-logado'] === 'false')
                    $rootScope.$broadcast('AUTENTICACAO_REQUERIDA', response);

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

    clinicaMed.run(['$rootScope', '$state', '$http', 'loginService', function ($rootScope, $state, $http, loginService) {
        if (!$rootScope.usuarioLogado) {
            var request = new XMLHttpRequest();
            request.open('GET', '/seguranca/usuarioLogado', false);
            console.clear();
            request.send(null);

            if (request.status === 200) {
                $rootScope.usuarioLogado = JSON.parse(request.responseText);
            }
        }

        $rootScope.$on('$stateChangeStart', function (event, toState) {
            if (toState.acesso && !loginService.usuarioTemPermissao(toState.acesso.usuariosAutorizados)) {
                event.preventDefault();
                $rootScope.$broadcast("event:auth-forbidden", {});
                console.log('ACESSO NEGADO');
            }
        });

        $rootScope.$on('AUTENTICACAO_REQUERIDA', function () {
            $state.go('login');
        });
    }]);

}(angular));