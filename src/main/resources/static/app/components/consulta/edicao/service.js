var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('consultaEdicaoService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/consulta/:id', {}, {
            'get': {method: 'GET'},
            'save': {method: 'POST'},
            'update': {method: 'PUT'}
        });
    }

    this.getConsulta = function (id) {
        return resource().get({id: id});
    };

    this.saveConsulta = function (consulta) {
        return resource().save(consulta).$promise.then(
            function () {
                $rootScope.$broadcast('CONSULTA_SAVE_SUCCESS');
            }
        );
    };

    this.updateConsulta = function (id, consulta) {
        return resource().update({id: id}, consulta).$promise.then(
            function () {
                $rootScope.$broadcast('CONSULTA_UPDATE_SUCCESS');
            }
        );
    };

});