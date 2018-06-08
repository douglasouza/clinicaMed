var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

        $stateProvider.state('prescricao.novo', {
            url: '/novo',
            templateUrl: './app/components/prescricao/edicao/view.html',
            controller: 'prescricaoEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: ['ADMINISTRADOR', 'MEDICO']
            }
        });

        $stateProvider.state('prescricao.edicao', {
            url: '/editar/:id',
            templateUrl: './app/components/prescricao/edicao/view.html',
            controller: 'prescricaoEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: ['ADMINISTRADOR', 'MEDICO']
            }
        });

    }
);