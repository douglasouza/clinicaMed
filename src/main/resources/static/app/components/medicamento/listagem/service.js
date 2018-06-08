var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('medicamentoListagemService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/medicamento/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'delete': {method: 'DELETE'}
        });
    }

    this.fetchAll = function (filtro) {
        resource().query({filtro: filtro}).$promise.then(
            function (data) {
                $rootScope.$broadcast('MEDICAMENTOS_FETCHED_SUCCESS', data);
            }
        );
    };

    this.deleteMedicamento = function (id) {
        return resource().delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('MEDICAMENTO_DELETE_SUCCESS');
            }
        );
    };

});