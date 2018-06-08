var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('pacienteEdicaoService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/paciente/:id', {}, {
            'get': {method: 'GET'},
            'save': {method: 'POST'},
            'update': {method: 'PUT'}
        });
    }

    this.getPaciente = function (id) {
        return resource().get({id: id});
    };

    this.savePaciente = function (paciente) {
        return resource().save(paciente).$promise.then(
            function () {
                $rootScope.$broadcast('PACIENTE_SAVE_SUCCESS');
            }
        );
    };

    this.updatePaciente = function (id, paciente) {
        return resource().update({id: id}, paciente).$promise.then(
            function () {
                $rootScope.$broadcast('PACIENTE_UPDATE_SUCCESS');
            }
        );
    };

});