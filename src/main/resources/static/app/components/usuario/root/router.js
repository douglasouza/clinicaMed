var clinicaMed = angular.module('clinicaMed');

clinicaMed.config(function ($stateProvider) {

    $stateProvider.state('usuario', {
        url: '/usuario',
        abstract: true,
        template: '<ui-view/>'
    });

});