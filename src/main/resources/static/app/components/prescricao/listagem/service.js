var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('prescricaoListagemService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/prescricao/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'delete': {method: 'DELETE'}
        });
    }

    this.fetchAll = function (filtro) {
        resource().query({filtro: filtro}).$promise.then(
            function (data) {
                $rootScope.$broadcast('PRESCRICOES_FETCHED_SUCCESS', data);
            }
        );
    };

    this.deletePrescricao = function (id) {
        return resource().delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('PRESCRICAO_DELETE_SUCCESS');
            }
        );
    };

});