var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('medico.listagem', {
        url: '/listagem',
        templateUrl: './app/components/medico/listagem/view.html',
        controller: 'medicoListagemController',
        acesso: {
            loginRequerido: true,
            usuariosAutorizados: ['ADMINISTRADOR']
        }
    });

});