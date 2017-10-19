var clinicaMed = angular.module('clinicaMed');

clinicaMed.service('medicoListagemService', function ($rootScope, $resource) {

    function resource() {
        return $resource('/medico/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'delete': {method: 'DELETE'}
        });
    }

    this.fetchAll = function () {
        resource().query().$promise.then(
            function (data) {
                $rootScope.$broadcast('MEDICOS_FETCHED_SUCCESS', data);
            },
            function () {
                $rootScope.$broadcast('MEDICOS_FETCHED_ERROR');
            }
        );
    };

    this.deleteMedico = function (id) {
        return resource().delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('MEDICO_DELETE_SUCCESS');
            },
            function () {
                $rootScope.$broadcast('MEDICO_DELETE_ERROR');
            }
        );
    };

});