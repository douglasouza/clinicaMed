var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

        $stateProvider.state('medico.novo', {
            url: '/novo',
            templateUrl: './app/components/medico/edicao/view.html',
            controller: 'medicoEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: 'ADMINISTRADOR'
            }
        });

        $stateProvider.state('medico.edicao', {
            url: '/editar/:id',
            templateUrl: './app/components/medico/edicao/view.html',
            controller: 'medicoEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: 'ADMINISTRADOR'
            }
        });

    }
);