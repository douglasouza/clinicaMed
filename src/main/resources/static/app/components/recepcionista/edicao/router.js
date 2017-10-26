var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

        $stateProvider.state('recepcionista.novo', {
            url: '/novo',
            templateUrl: './app/components/recepcionista/edicao/view.html',
            controller: 'recepcionistaEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: 'ADMINISTRADOR'
            }
        });

        $stateProvider.state('recepcionista.edicao', {
            url: '/editar/:id',
            templateUrl: './app/components/recepcionista/edicao/view.html',
            controller: 'recepcionistaEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: 'ADMINISTRADOR'
            }
        });

    }
);