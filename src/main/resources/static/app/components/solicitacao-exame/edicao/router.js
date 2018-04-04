var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

        $stateProvider.state('solicitacaoExame.novo', {
            url: '/novo',
            templateUrl: './app/components/solicitacao-exame/edicao/view.html',
            controller: 'solicitacaoExameEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: ['ADMINISTRADOR', 'MEDICO']
            }
        });

        $stateProvider.state('solicitacaoExame.edicao', {
            url: '/editar/:id',
            templateUrl: './app/components/solicitacao-exame/edicao/view.html',
            controller: 'solicitacaoExameEdicaoController',
            acesso: {
                loginRequerido: true,
                usuariosAutorizados: ['ADMINISTRADOR', 'MEDICO']
            },
            params: {
                msgSucesso: null
            }
        });

    }
);