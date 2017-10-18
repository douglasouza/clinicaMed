var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('usuario.listagem', {
        url: '/listagem',
        templateUrl: './app/components/usuario/listagem/view.html',
        controller: 'usuarioListagemController'
    });

});