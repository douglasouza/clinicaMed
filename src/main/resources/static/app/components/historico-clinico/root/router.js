var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('historicoPaciente', {
        url: '/historicoPaciente',
        abstract: true,
        template: '<ui-view/>',
        acesso: {
            loginRequerido: true,
            usuariosAutorizados: ['ADMINISTRADOR', 'RECEPCIONISTA', 'MEDICO']
        }
    });

});