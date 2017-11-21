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
            filtro.dataInicial = converterDataParaPadraoAnoMesDia(consulta.dataInicial);
        if (consulta.dataFinal)
            filtro.dataFinal = converterDataParaPadraoAnoMesDia(consulta.dataFinal);

        resource().query(filtro).$promise.then(
            function (data) {
                $rootScope.$broadcast('CONSULTAS_FETCHED_SUCCESS', data);
            }
        );
    };

    function converterDataParaPadraoAnoMesDia(data) {
        var dataArray = data.split('/');
        return new Date(dataArray[2] + '/' + dataArray[1] + '/' + dataArray[0]).toUTCString();
    }

    this.deleteConsulta = function (id) {
        return resource().delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('CONSULTA_DELETE_SUCCESS');
            }
        );
    };

});