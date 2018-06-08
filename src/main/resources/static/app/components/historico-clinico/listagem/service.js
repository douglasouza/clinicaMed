var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('historicoPacienteListagemService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/paciente/:id', {}, {
            'query': {method: 'GET', isArray: true}
        });
    }

    function resourceHistorico() {
        return $resource('/paciente/:id/historico', {}, {
            'query': {method: 'GET', isArray: true}
        });
    }

    this.fetchAllPacientes = function () {
        return resource().query().$promise;
    };

    this.fetchHistoricoPaciente = function (id) {
        return resourceHistorico().query({id: id}).$promise.then(
            function (data) {
                $rootScope.$broadcast('HISTORICO_PACIENTE_FETCHED_SUCCESS', data);
            }
        );
    };

});