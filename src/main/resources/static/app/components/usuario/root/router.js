var myApp = angular.module('myApp');

myApp.config(function ($stateProvider) {

    $stateProvider.state('usuario', {
        url: '/usuario',
        abstract: true,
        template: '<ui-view/>'
    });

});