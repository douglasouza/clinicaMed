var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('consultaListagemService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/consulta/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'delete': {method: 'DELETE'}
        });
    }

    this.fetchAll = function (consulta) {
        var filtro = {};
        filtro.nomeMedicoPaciente = consulta.nomeMedicoPaciente;
        if (consulta.dataInicial)
            filtro.dataInicial = consulta.dataInicial;
        if (consulta.dataFinal)
            filtro.dataFinal = consulta.dataFinal;

        resource().query(filtro).$promise.then(
            function (data) {
                $rootScope.$broadcast('CONSULTAS_FETCHED_SUCCESS', data);
            }
        );
    };

    this.deleteConsulta = function (id) {
        return resource().delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('CONSULTA_DELETE_SUCCESS');
            }
        );
    };

});