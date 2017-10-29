var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('home', {
        url: '/home',
        templateUrl: './app/components/home/view.html',
        controller: 'homeController',
        acesso: {
            loginRequerido: true,
            usuariosAutorizados: ''
        }
    });
});