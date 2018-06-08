var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('consulta.listagem', {
        url: '/listagem',
        templateUrl: './app/components/consulta/listagem/view.html',
        controller: 'consultaListagemController',
        acesso: {
            loginRequerido: true,
            usuariosAutorizados: ['ADMINISTRADOR', 'RECEPCIONISTA']
        }
    });

});