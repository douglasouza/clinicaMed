(function (angular) {

    var clinicaMed = angular.module("clinicaMed", ["ngResource", 'ui.router']);

    clinicaMed.config(function ($urlRouterProvider) {
        $urlRouterProvider.when('/medico', '/medico/listagem');
        $urlRouterProvider.when('/medico/', '/medico/listagem');
        $urlRouterProvider.otherwise('home');
    });

    clinicaMed.constant('jQuery', window.jQuery);

}(angular));