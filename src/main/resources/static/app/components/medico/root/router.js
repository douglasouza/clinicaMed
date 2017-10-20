var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('medico', {
        url: '/medico',
        abstract: true,
        template: '<ui-view/>'
    });

});