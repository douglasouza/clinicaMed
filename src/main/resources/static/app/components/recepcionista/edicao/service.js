var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('recepcionistaEdicaoService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/recepcionista/:id', {}, {
            'get': {method: 'GET'},
            'save': {method: 'POST'},
            'update': {method: 'PUT'}
        });
    }

    this.getRecepcionista = function (id) {
        return resource().get({id: id});
    };

    this.saveRecepcionista = function (recepcionista) {
        return resource().save(recepcionista).$promise.then(
            function () {
                $rootScope.$broadcast('RECEPCIONISTA_SAVE_SUCCESS');
            }
        );
    };

    this.updateRecepcionista = function (id, recepcionista) {
        return resource().update({id: id}, recepcionista).$promise.then(
            function () {
                $rootScope.$broadcast('RECEPCIONISTA_UPDATE_SUCCESS');
            }
        );
    };

});