var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('recepcionista', {
        url: '/recepcionista',
        abstract: true,
        template: '<ui-view/>'
    });

});