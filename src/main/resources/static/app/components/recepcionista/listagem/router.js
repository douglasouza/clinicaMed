var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('recepcionista.listagem', {
        url: '/listagem',
        templateUrl: './app/components/recepcionista/listagem/view.html',
        controller: 'recepcionistaListagemController'
    });

});