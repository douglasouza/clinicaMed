var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('medicamentoEdicaoService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/medicamento/:id', {}, {
            'get': {method: 'GET'},
            'save': {method: 'POST'},
            'update': {method: 'PUT'}
        });
    }

    this.getMedicamento = function (id) {
        return resource().get({id: id});
    };

    this.saveMedicamento = function (medicamento) {
        return resource().save(medicamento).$promise.then(
            function () {
                $rootScope.$broadcast('MEDICAMENTO_SAVE_SUCCESS');
            }
        );
    };

    this.updateMedicamento = function (id, medicamento) {
        return resource().update({id: id}, medicamento).$promise.then(
            function () {
                $rootScope.$broadcast('MEDICAMENTO_UPDATE_SUCCESS');
            }
        );
    };

});