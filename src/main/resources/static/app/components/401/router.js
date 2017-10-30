var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('acessoNegado', {
        url: '/acessoNegado',
        templateUrl: './app/components/401/view.html',
        controller: 'acessoNegadoController',
        acesso: {
            loginRequerido: false
        }
    });
});