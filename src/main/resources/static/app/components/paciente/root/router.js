var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('paciente', {
        url: '/paciente',
        abstract: true,
        template: '<ui-view/>'
    });

});