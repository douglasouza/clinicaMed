var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('solicitacaoExame.listagem', {
        url: '/listagem',
        templateUrl: './app/components/solicitacao-exame/listagem/view.html',
        controller: 'solicitacaoExameListagemController',
        acesso: {
            loginRequerido: true,
            usuariosAutorizados: ['ADMINISTRADOR', 'MEDICO']
        }
    });

});