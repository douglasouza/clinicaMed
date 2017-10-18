var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

        $stateProvider.state('usuario.novo', {
            url: '/novo',
            templateUrl: './app/components/usuario/edicao/view.html',
            controller: 'usuarioEdicaoController'
        });

        $stateProvider.state('usuario.edicao', {
            url: '/editar/:id',
            templateUrl: './app/components/usuario/edicao/view.html',
            controller: 'usuarioEdicaoController'
        });

    }
);