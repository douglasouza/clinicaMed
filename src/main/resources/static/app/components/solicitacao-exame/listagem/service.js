var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('solicitacaoExameListagemService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/solicitacaoExame/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'delete': {method: 'DELETE'}
        });
    }

    this.fetchAll = function (filtro) {
        resource().query({filtro: filtro}).$promise.then(
            function (data) {
                $rootScope.$broadcast('SOLICITACOES_EXAME_FETCHED_SUCCESS', data);
            }
        );
    };

    this.deleteSolicitacaoExame = function (id) {
        return resource().delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('SOLICITACAO_EXAME_DELETE_SUCCESS');
            }
        );
    };

});