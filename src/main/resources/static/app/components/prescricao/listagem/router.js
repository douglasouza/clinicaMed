var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('prescricao.listagem', {
        url: '/listagem',
        templateUrl: './app/components/prescricao/listagem/view.html',
        controller: 'prescricaoListagemController',
        acesso: {
            loginRequerido: true,
            usuariosAutorizados: ['ADMINISTRADOR', 'MEDICO']
        }
    });

});