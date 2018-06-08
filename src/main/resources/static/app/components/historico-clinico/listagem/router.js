var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('historicoPaciente.listagem', {
        url: '/historicoPaciente',
        templateUrl: './app/components/historico-clinico/listagem/view.html',
        controller: 'historicoPacienteListagemController',
        acesso: {
            loginRequerido: true,
            usuariosAutorizados: ['ADMINISTRADOR', 'RECEPCIONISTA', 'MEDICO']
        }
    });

});