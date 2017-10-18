(function (angular) {

    var clinicaMed = angular.module("clinicaMed", ["ngResource", 'ui.router']);

    clinicaMed.config(function ($urlRouterProvider) {
        $urlRouterProvider.when('/usuario', '/usuario/listagem');
        $urlRouterProvider.when('/usuario/', '/usuario/listagem');
        $urlRouterProvider.otherwise('home');
    });

}(angular));