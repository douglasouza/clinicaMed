var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('acessoNegado', {
        url: '/acessoNegado',
        templateUrl: './app/security/acesso-negado/view.html',
        controller: 'acessoNegadoController',
        acesso: {
            loginRequerido: false
        }
    });
});