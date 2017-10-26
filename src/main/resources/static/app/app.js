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

    clinicaMed.run(['$rootScope', '$state', function ($rootScope, $state) {
        $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){
            if (toState.url === "/login" && $rootScope.authenticated) {
                event.preventDefault();
            } else if (toState.acesso && toState.acesso.loginRequerido && !$rootScope.authenticated) {
                event.preventDefault();
                $rootScope.$broadcast("event:auth-loginRequired", {});
            }// } else if (toState.acesso){//   } && !AuthSharedService.isAuthorized(toState.access.authorizedRoles)) {
            //     event.preventDefault();
            //     $rootScope.$broadcast("event:auth-forbidden", {});
            // }
        });

        $rootScope.$on('event:auth-loginRequired', function (event, data) {
            $rootScope.authenticated = false;
            $state.go('login');
        });

    }]);

}(angular));