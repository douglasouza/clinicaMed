var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('solicitacaoExameEdicaoService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/solicitacaoExame/:id', {}, {
            'get': {method: 'GET'},
            'save': {method: 'POST'},
            'update': {method: 'PUT'}
        });
    }

    function exameResource() {
        return $resource('/exame/', {}, {
            'get': {method: 'GET', isArray: true}
        });
    }

    function examesSolicitacaoResource() {
        return $resource('/solicitacaoExame/:id/exames', {}, {
            'get': {method: 'GET', isArray: true}
        });
    }

    this.fetchAllExames = function () {
        exameResource().get().$promise.then(
            function (data) {
                $rootScope.$broadcast('EXAMES_FETCHED_SUCCESS', data);
            }
        );
    };

    this.getSolicitacaoExame = function (id) {
        return resource().get({id: id});
    };

    this.getExamesSolicitacao = function (id) {
        return examesSolicitacaoResource().get({id: id});
    };

    this.saveSolicitacaoExame = function (solicitacaoExame) {
        return resource().save(solicitacaoExame).$promise.then(
            function () {
                $rootScope.$broadcast('SOLICITACAO_EXAME_SAVE_SUCCESS');
            }
        );
    };

    this.updateSolicitacaoExame = function (id, solicitacaoExame) {
        return resource().update({id: id}, solicitacaoExame).$promise.then(
            function () {
                $rootScope.$broadcast('SOLICITACAO_EXAME_UPDATE_SUCCESS');
            }
        );
    };

});