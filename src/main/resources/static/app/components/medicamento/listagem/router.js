var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('medicamento.listagem', {
        url: '/listagem',
        templateUrl: './app/components/medicamento/listagem/view.html',
        controller: 'medicamentoListagemController',
        acesso: {
            loginRequerido: true,
            usuariosAutorizados: ['ADMINISTRADOR', 'RECEPCIONISTA']
        }
    });

});