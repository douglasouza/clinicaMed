var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

        $stateProvider.state('consulta.novo', {
            url: '/novo',
            templateUrl: './app/components/consulta/novo/view.html',
            controller: 'consultaNovoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: ['ADMINISTRADOR', 'RECEPCIONISTA']
            }
        });

    }
);