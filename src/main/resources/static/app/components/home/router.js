var myApp = angular.module('myApp');

myApp.config(function ($stateProvider) {

    $stateProvider.state('home', {
        url: '/home',
        templateUrl: './app/components/home/view.html'
    });

});