var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('prescricaoEdicaoService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/prescricao/:id', {}, {
            'get': {method: 'GET'},
            'save': {method: 'POST'},
            'update': {method: 'PUT'}
        });
    }

    this.getPrescricao = function (id) {
        return resource().get({id: id});
    };

    this.savePrescricao = function (prescricao) {
        return resource().save(prescricao).$promise.then(
            function () {
                $rootScope.$broadcast('PRESCRICAO_SAVE_SUCCESS');
            }
        );
    };

    this.updatePrescricao = function (id, prescricao) {
        return resource().update({id: id}, prescricao).$promise.then(
            function () {
                $rootScope.$broadcast('PRESCRICAO_UPDATE_SUCCESS');
            }
        );
    };

});