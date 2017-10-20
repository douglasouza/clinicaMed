var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('medicoEdicaoService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/medico/:id', {}, {
            'get': {method: 'GET'},
            'save': {method: 'POST'},
            'update': {method: 'PUT'}
        });
    }

    this.getUsuario = function (id) {
        return resource().get({id: id});
    };

    this.saveUsuario = function (medico) {
        return resource().save(medico).$promise.then(
            function () {
                $rootScope.$broadcast('MEDICO_SAVE_SUCCESS');
            }
        );
    };

    this.updateUsuario = function (id, medico) {
        return resource().update({id: id}, medico).$promise.then(
            function () {
                $rootScope.$broadcast('MEDICO_UPDATE_SUCCESS');
            }
        );
    };

});