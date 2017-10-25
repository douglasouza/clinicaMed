var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('home', {
        url: '/home',
        templateUrl: './app/components/home/view.html'
    });

    $stateProvider.state('login', {
        url: '/login',
        templateUrl: './login.html'
    });

});