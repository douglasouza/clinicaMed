(function (angular) {

    var clinicaMed = angular.module("clinicaMed", ["ngResource", 'ui.router']);

    clinicaMed.constant('jQuery', window.jQuery);

    clinicaMed.factory('httpInterceptor', ['$rootScope', function ($rootScope) {
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
            }
        };
    }]);

    clinicaMed.config(['$httpProvider', '$urlRouterProvider', function ($httpProvider, $urlRouterProvider) {
        $httpProvider.interceptors.push('httpInterceptor');

        $urlRouterProvider.when('/medico', '/medico/listagem');
        $urlRouterProvider.when('/medico/', '/medico/listagem');
        $urlRouterProvider.when('/recepcionista', '/recepcionista/listagem');
        $urlRouterProvider.when('/recepcionista/', '/recepcionista/listagem');
        $urlRouterProvider.otherwise('home');
    }]);

}(angular));