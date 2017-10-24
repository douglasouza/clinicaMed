var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('pacienteListagemService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/paciente/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'delete': {method: 'DELETE'}
        });
    }

    this.fetchAll = function (paciente) {
        resource().query(paciente).$promise.then(
            function (data) {
                $rootScope.$broadcast('PACIENTES_FETCHED_SUCCESS', data);
            }
        );
    };

    this.deletePaciente = function (id) {
        return resource().delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('PACIENTE_DELETE_SUCCESS');
            }
        );
    };

});