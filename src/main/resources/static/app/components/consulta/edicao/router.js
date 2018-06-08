var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

        $stateProvider.state('consulta.edicao', {
            url: '/editar/:id',
            templateUrl: './app/components/consulta/edicao/view.html',
            controller: 'consultaEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: ['ADMINISTRADOR', 'RECEPCIONISTA']
            }
        });

    }
);