var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('prescricao', {
        url: '/prescricao',
        abstract: true,
        template: '<ui-view/>',
        acesso: {
            loginRequerido: true,
            usuariosAutorizados: ['ADMINISTRADOR', 'MEDICO']
        }
    });

});