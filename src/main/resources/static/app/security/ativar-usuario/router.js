var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('ativarUsuario', {
        url: '/ativarUsuario',
        templateUrl: './app/security/ativar-usuario/view.html',
        controller: 'ativarUsuarioController',
        acesso: {
            loginRequerido: false
        }
    });
});