var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('paciente.listagem', {
        url: '/listagem',
        templateUrl: './app/components/paciente/listagem/view.html',
        controller: 'pacienteListagemController'
    });

});