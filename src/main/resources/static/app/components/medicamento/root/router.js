var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('medicamento', {
        url: '/medicamento',
        abstract: true,
        template: '<ui-view/>',
        acesso: {
            loginRequerido: true,
            usuariosAutorizados: ['ADMINISTRADOR', 'RECEPCIONISTA']
        }
    });

});