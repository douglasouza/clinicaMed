var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('solicitacaoExame', {
        url: '/solicitacaoExame',
        abstract: true,
        template: '<ui-view/>',
        acesso: {
            loginRequerido: true,
            usuariosAutorizados: ['ADMINISTRADOR', 'MEDICO']
        }
    });

});