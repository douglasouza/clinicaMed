(function (angular) {

    var myApp = angular.module("myApp", ["ngResource", 'ui.router']);

    myApp.config(function ($urlRouterProvider) {
        $urlRouterProvider.otherwise('home');
    });

}(angular));