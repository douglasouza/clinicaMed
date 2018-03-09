var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('solicitacaoExameEdicaoService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/solicitacaoExame/:id', {}, {
            'get': {method: 'GET'},
            'save': {method: 'POST'},
            'update': {method: 'PUT'}
        });
    }

    function uploadResource() {
        return $resource('/solicitacaoExame/:id/uploadResultadoExame', {}, {
            'upload': {method: 'PUT'}
        });
    }

    function removerArquivoResource() {
        return $resource('/solicitacaoExame/:id/removerResultadoExame', {}, {
            'delete': {method: 'PUT'}
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

    this.saveSolicitacaoExame = function (solicitacaoExame) {
        return resource().save(solicitacaoExame).$promise.then(
            function (data) {
                $rootScope.$broadcast('SOLICITACAO_EXAME_SAVE_SUCCESS', data);
            }
        );
    };

    this.updateSolicitacaoExame = function (id, solicitacaoExame) {
        return resource().update({id: id}, solicitacaoExame).$promise.then(
            function (data) {
                $rootScope.$broadcast('SOLICITACAO_EXAME_UPDATE_SUCCESS', data);
            }
        );
    };

    this.uploadArquivo = function (id, nomeArquivo, mimeType, arquivo) {
        return uploadResource().upload({id: id, nomeArquivo: nomeArquivo, mimeType: mimeType}, arquivo).$promise.then(
            function () {
                $rootScope.$broadcast('SOLICITACAO_EXAME_UPLOAD_SUCCESS');
            }
        );
    };

    this.removerArquivo = function (id) {
        return removerArquivoResource().delete({id: id}, undefined).$promise.then(
            function () {
                $rootScope.$broadcast('SOLICITACAO_EXAME_REMOVE_UPLOAD_SUCCESS');
            }
        );
    };

});