var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

        $stateProvider.state('medicamento.novo', {
            url: '/novo',
            templateUrl: './app/components/medicamento/edicao/view.html',
            controller: 'medicamentoEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: ['ADMINISTRADOR', 'RECEPCIONISTA']
            }
        });

        $stateProvider.state('medicamento.edicao', {
            url: '/editar/:id',
            templateUrl: './app/components/medicamento/edicao/view.html',
            controller: 'medicamentoEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: ['ADMINISTRADOR', 'RECEPCIONISTA']
            }
        });

    }
);