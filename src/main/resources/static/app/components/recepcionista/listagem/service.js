var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('recepcionistaListagemService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/recepcionista/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'delete': {method: 'DELETE'}
        });
    }

    this.fetchAll = function (recepcionista) {
        resource().query(recepcionista).$promise.then(
            function (data) {
                $rootScope.$broadcast('RECEPCIONISTAS_FETCHED_SUCCESS', data);
            }
        );
    };

    this.deleteRecepcionista = function (id) {
        return resource().delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('RECEPCIONISTA_DELETE_SUCCESS');
            }
        );
    };

});