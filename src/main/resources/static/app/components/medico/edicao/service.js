var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('medicoEdicaoService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/medico/:id', {}, {
            'get': {method: 'GET'},
            'save': {method: 'POST'},
            'update': {method: 'PUT'}
        });
    }

    this.getMedico = function (id) {
        return resource().get({id: id});
    };

    this.saveMedico = function (medico) {
        return resource().save(medico).$promise.then(
            function () {
                $rootScope.$broadcast('MEDICO_SAVE_SUCCESS');
            }
        );
    };

    this.updateMedico = function (id, medico) {
        return resource().update({id: id}, medico).$promise.then(
            function () {
                $rootScope.$broadcast('MEDICO_UPDATE_SUCCESS');
            }
        );
    };

});